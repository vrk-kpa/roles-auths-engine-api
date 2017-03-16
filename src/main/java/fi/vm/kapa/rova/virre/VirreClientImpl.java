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
import fi.vm.kapa.rova.RovaRestTemplate;
import fi.vm.kapa.rova.external.model.virre.CompanyPerson;
import fi.vm.kapa.rova.external.model.virre.CompanyRepresentations;
import fi.vm.kapa.rova.external.model.virre.RepresentationRight;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import fi.vm.kapa.rova.utils.EncodingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RibbonClient(name = "virreClient")
@Conditional(VirreClientCondition.class)
public class VirreClientImpl implements Virre, VirreClient {

    public static final String NO_DATA_ERROR_MSG = "No data from Virre client.";
    public static final String CONNECTION_ERROR_MSG = "Virre connection error: ";
    private static final Logger LOG = Logger.getLogger(VirreClientImpl.class);

    private String apiKey;

    private int requestAliveSeconds;

    private String endpointUrl;

    public VirreClientImpl(@Value("${virre_client_api_key}") String apiKey,
            @Value("${request_alive_seconds}") int requestAliveSeconds,
            @Value("${virre_client_url}") String endpointUrl) {
        super();
        this.apiKey = apiKey;
        this.requestAliveSeconds = requestAliveSeconds;
        this.endpointUrl = endpointUrl;
    }

    @Override
    public CompanyPerson getCompanyPerson(String socialsec) {
        String url = endpointUrl + GET_COMPANY_PERSON_PATH;
        RestTemplate restTemplate = getRestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("socialsec", EncodingUtils.encodePathParam(socialsec));
        CompanyPerson person = null;
        ResponseEntity<CompanyPerson> entity = restTemplate.getForEntity(url, CompanyPerson.class, params);
        if (entity.getStatusCode() == HttpStatus.OK) {
            person = entity.getBody();
            if (person == null) {
                LOG.error(NO_DATA_ERROR_MSG);
            }
        } else if (entity.getStatusCode() == HttpStatus.NOT_FOUND) {
            LOG.debug("No data (404) from Virre for " + socialsec);
        } else {
            LOG.error("Got error response from Virre during companies query");
            throw new ClientException(CONNECTION_ERROR_MSG + entity.getStatusCode());
        }
        return person;
    }

    @Override
    public Optional<CompanyPerson> getCompanyPersonOptional(String socialsec) {
        return Optional.ofNullable(getCompanyPerson(socialsec));
    }

    @Override
    public CompanyRepresentations getRepresentations(String businessid) {
        String url = endpointUrl + GET_REPRESENTATIONS_PATH;
        RestTemplate restTemplate = getRestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("businessid", businessid);
        ResponseEntity<CompanyRepresentations> response = restTemplate.getForEntity(url, CompanyRepresentations.class,
                params);
        if (response.getStatusCode() == HttpStatus.OK) {
            CompanyRepresentations reps = response.getBody();
            if (reps == null) {
                LOG.error(NO_DATA_ERROR_MSG);
            }
            return reps;
        } else {
            LOG.error("Got error response from Virre during representations query");
            throw new ClientException(CONNECTION_ERROR_MSG + response.getStatusCode());
        }
    }

    @Override
    public RepresentationRight getRights(String socialSec, String businessId, String rightLevel) {
        String url = endpointUrl + GET_RIGHTS_PATH;
        RestTemplate restTemplate = getRestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("rightlevel", rightLevel);
        params.put("socialsec", EncodingUtils.encodePathParam(socialSec));
        params.put("businessid", businessId);
        ResponseEntity<RepresentationRight> response = restTemplate.getForEntity(url, RepresentationRight.class,
                params);
        RepresentationRight rights = null;
        if (response.getStatusCode() == HttpStatus.OK) {
            rights = response.getBody();
            if (rights == null) {
                LOG.error(NO_DATA_ERROR_MSG);
            }
        } else {
            LOG.error("Got error response from Virre during rights query");
            throw new ClientException(CONNECTION_ERROR_MSG + response.getStatusCode());
        }
        return rights;
    }

    private RestTemplate getRestTemplate() {
        return new RovaRestTemplate(apiKey, requestAliveSeconds,
                RequestIdentificationInterceptor.HeaderTrust.TRUST_REQUEST_HEADERS);
    }

}
