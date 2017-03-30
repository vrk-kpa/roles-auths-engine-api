/**
 * The MIT License
 * Copyright (c) 2016 Population Register Centre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fi.vm.kapa.rova.virre;

import fi.vm.kapa.rova.ClientException;
import fi.vm.kapa.rova.RestTemplateFactory;
import fi.vm.kapa.rova.external.model.virre.CompanyPerson;
import fi.vm.kapa.rova.external.model.virre.CompanyRepresentations;
import fi.vm.kapa.rova.external.model.virre.RepresentationRight;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.utils.EncodingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RibbonClient(name = Virre.CLIENT)
@Conditional(VirreClientCondition.class)
public class VirreClientImpl implements Virre, VirreClient {

    public static final String NO_DATA_ERROR_MSG = "No data from Virre client.";
    public static final String CONNECTION_ERROR_MSG = "Virre connection error: ";
    private static final Logger LOG = Logger.getLogger(VirreClientImpl.class);
    private static final String ENDPOINT_URL = "http://" + Virre.CLIENT;

    private String apiKey;

    private int requestAliveSeconds;

    @Autowired
    @Qualifier("virreRestTemplate")
    private RestTemplate virreRestTemplate;

    @Bean(name="virreRestTemplate")
    @LoadBalanced
    public RestTemplate virreRestTemplate() {
        return RestTemplateFactory.forBackendService(apiKey, requestAliveSeconds);
    }

    public VirreClientImpl(@Value("${virre_client_api_key}") String apiKey,
            @Value("${request_alive_seconds}") int requestAliveSeconds) {
        super();
        this.apiKey = apiKey;
        this.requestAliveSeconds = requestAliveSeconds;
    }

    @Override
    public CompanyPerson getCompanyPerson(String socialsec) {
        String url = ENDPOINT_URL + GET_COMPANY_PERSON_PATH;
        Map<String, String> params = new HashMap<>();
        params.put("socialsec", EncodingUtils.encodePathParam(socialsec));
        CompanyPerson person = null;
        ResponseEntity<CompanyPerson> entity = virreRestTemplate.getForEntity(url, CompanyPerson.class, params);
        if (entity.getStatusCode() == HttpStatus.NOT_FOUND) {
            LOG.debug("No data (404) from Virre for " + socialsec);
            return null;
        }

        return handleResponse(entity, "Got error response from Virre during companies query");
    }

    @Override
    public Optional<CompanyPerson> getCompanyPersonOptional(String socialsec) {
        return Optional.ofNullable(getCompanyPerson(socialsec));
    }

    @Override
    public CompanyRepresentations getRepresentations(String businessid) {
        String url = ENDPOINT_URL + GET_REPRESENTATIONS_PATH;
        Map<String, String> params = new HashMap<>();
        params.put("businessid", businessid);

        return handleResponse(virreRestTemplate.getForEntity(url, CompanyRepresentations.class, params),
                "Got error response from Virre during representations query");
    }

    @Override
    public RepresentationRight getRights(String socialSec, String businessId, String rightLevel) {
        String url = ENDPOINT_URL + GET_RIGHTS_PATH;
        Map<String, String> params = new HashMap<>();
        params.put("rightlevel", rightLevel);
        params.put("socialsec", EncodingUtils.encodePathParam(socialSec));
        params.put("businessid", businessId);

        return handleResponse(
                virreRestTemplate.getForEntity(url, RepresentationRight.class, params),
                "Got error response from Virre during rights query");
    }

     private <T> T handleResponse(ResponseEntity<T> response, String errorMsg) {
        if (response.getStatusCode() != HttpStatus.OK) {
            LOG.error(errorMsg);
            throw new ClientException(CONNECTION_ERROR_MSG + response.getStatusCode());
        }
        T val = response.getBody();
        if (val == null) {
            LOG.error(NO_DATA_ERROR_MSG);
        }
        return val;
     }
}
