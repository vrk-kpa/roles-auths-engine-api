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
import fi.vm.kapa.rova.RestTemplateFactory;
import fi.vm.kapa.rova.external.model.ytj.CompanyDTO;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.ptv.model.PtvService;
import fi.vm.kapa.rova.search.model.CompanySearchResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RibbonClient(name = Search.CLIENT)
@Conditional(SearchClientCondition.class)
public class SearchClient implements Search {

    private static final Logger LOG = Logger.getLogger(SearchClient.class);

    private static final String ENDPOINT_URL = "http://" + CLIENT;

    private String apiKey;

    private int requestAliveSeconds;

    @Autowired
    @Qualifier("searchRestTemplate")
    private RestTemplate restTemplate;

    @Bean(name = "searchRestTemplate")
    @LoadBalanced
    public RestTemplate searchRestTemplate() {
        return RestTemplateFactory.forBackendService(apiKey, requestAliveSeconds);
    }

    public SearchClient(@Value("${search_service_search_key}") String apiKey,
            @Value("${request_alive_seconds}") int requestAliveSeconds) {
        super();
        this.apiKey = apiKey;
        this.requestAliveSeconds = requestAliveSeconds;
    }

    public List<CompanyDTO> getNamesForCompanies(List<String> companyIds) {
        if (companyIds == null || companyIds.isEmpty()) {
            return Collections.emptyList();
        }

        String url = ENDPOINT_URL + NAMES_FOR_COMPANIES;
        ResponseEntity<List<CompanyDTO>> response = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<>(companyIds), new ParameterizedTypeReference<List<CompanyDTO>>() {});
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            String errorMsq = "Got not OK for POST: " + url + " companyIds=" + companyIds;
            LOG.error(errorMsq);
            throw new ClientException(errorMsq);
        }
    }

    public List<CompanyDTO> getCompaniesForName(String nameQuery) {
        if (StringUtils.isBlank(nameQuery)) {
            return Collections.emptyList();
        }

        ResponseEntity<CompanySearchResult> result = restTemplate.getForEntity(ENDPOINT_URL + COMPANIES_FOR_NAME + "/" + nameQuery,
                CompanySearchResult.class, Collections.emptyMap());

        return result.getBody().getCompanies();
    }

    public List<PtvService> getServicesForIssue(String issueUri) {
        if (isBlank(issueUri)) {
            return Collections.emptyList();
        }

        try {
            ResponseEntity<PtvService[]> response = restTemplate.getForEntity(ENDPOINT_URL + SERVICES_FOR_ISSUE + "/" + URLEncoder.encode(issueUri, "UTF-8"),
                    PtvService[].class, Collections.emptyMap());
            if (response != null && response.getBody() != null) {
                return Arrays.asList(response.getBody());
            } else {
                LOG.error(ENDPOINT_URL + SERVICES_FOR_ISSUE + "/" + URLEncoder.encode(issueUri, "UTF-8") +" got null/empty response.");
                return Collections.emptyList();
            }
        } catch (UnsupportedEncodingException e) {
            LOG.error("Exception while calling "+ ENDPOINT_URL + SERVICES_FOR_ISSUE + "/" + issueUri +": "+ e.getMessage());
            return Collections.emptyList();
        }
    }

}
