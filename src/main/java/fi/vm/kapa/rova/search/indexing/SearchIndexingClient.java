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
package fi.vm.kapa.rova.search.indexing;

import fi.vm.kapa.rova.ErrorHandlerBuilder;
import fi.vm.kapa.rova.RestTemplateFactory;
import fi.vm.kapa.rova.external.model.ytj.CompanyDTO;
import fi.vm.kapa.rova.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RibbonClient(name = SearchIndexing.CLIENT)
@Conditional(SearchIndexingClientCondition.class)
public class SearchIndexingClient implements SearchIndexing {

    private static final Logger LOG = Logger.getLogger(SearchIndexingClient.class);
    protected static final String RIBBON_ENDPOINT = "http://" + SearchIndexing.CLIENT;

    private String apiKey;
    private int requestAliveSeconds;

    @Autowired
    @Qualifier("indexClientRestTemplate")
    private RestTemplate indexClientRestTemplate;

    @Bean("indexClientRestTemplate")
    @LoadBalanced
    public RestTemplate indexClientRestTemplate() {
        RestTemplate template = RestTemplateFactory.forBackendService(apiKey, requestAliveSeconds);
        template.setErrorHandler(ErrorHandlerBuilder.allErrors());
        return template;
    }

    public SearchIndexingClient(@Value("${search_service_indexing_api_key}") String apiKey,
                                @Value("${request_alive_seconds}") int requestAliveSeconds) {
        this.apiKey = apiKey;
        this.requestAliveSeconds = requestAliveSeconds;
    }

    public boolean companyIndexExists() throws SearchServiceException {

        String resourceUrl = RIBBON_ENDPOINT + "/index/companies";
        ResponseEntity<String> response = indexClientRestTemplate.exchange(resourceUrl, HttpMethod.HEAD, null, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return false;
        } else {
            LOG.error("Failed to check if company is indexed (HEAD for " + resourceUrl
                    + " returned HTTP status " + response.getStatusCode().value() + ").");
            throw new SearchServiceException("Failed to check if company can be found from index.");
        }
    }

    public void index(List<CompanyDTO> companies) throws SearchServiceException {

        String resourceUrl = RIBBON_ENDPOINT + "/index/companies";

        ResponseEntity<String> response = indexClientRestTemplate.postForEntity(resourceUrl, companies, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            LOG.error("Failed to index company data (POST for " + resourceUrl
                    + " returned HTTP status " + response.getStatusCode().value() + ").");
            throw new SearchServiceException("Failed to index companies.");
        }
    }

}
