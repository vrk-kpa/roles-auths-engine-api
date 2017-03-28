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
package fi.vm.kapa.rova.search;

import fi.vm.kapa.rova.ClientException;
import fi.vm.kapa.rova.RovaRestTemplate;
import fi.vm.kapa.rova.external.model.ytj.CompanyDTO;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor.HeaderTrust;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RibbonClient(name = "searchClient")
@Conditional(SearchClientCondition.class)
public class SearchClient {

    private static final Logger LOG = Logger.getLogger(SearchClient.class);

    private String apiKey;

    private int requestAliveSeconds;

    private String endpointUrl;

    public SearchClient(@Value("${search_service_search_key}") String apiKey,
            @Value("${request_alive_seconds}") int requestAliveSeconds,
            @Value("${search_service_url}") String endpointUrl) {
        super();
        this.apiKey = apiKey;
        this.requestAliveSeconds = requestAliveSeconds;
        this.endpointUrl = endpointUrl;
    }

    public List<CompanyDTO> getNamesForCompanies(List<String> companyIds) {
        if (companyIds == null || companyIds.isEmpty()) {
            return Collections.emptyList();
        }
        RestTemplate restTemplate = getRestTemplate();

        String url = endpointUrl + "/search/companies/byId";
        ResponseEntity<List<CompanyDTO>> response = restTemplate.exchange(url, HttpMethod.POST,
                null, new ParameterizedTypeReference<List<CompanyDTO>>() {
        }, companyIds);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            String errorMsq = "Got not OK for POST: " + url + " companyIds=" + companyIds;
            LOG.error(errorMsq);
            throw new ClientException(errorMsq);
        }
    }

    private RestTemplate getRestTemplate() {
        return new RovaRestTemplate(apiKey, requestAliveSeconds, HeaderTrust.TRUST_REQUEST_HEADERS);
    }

}
