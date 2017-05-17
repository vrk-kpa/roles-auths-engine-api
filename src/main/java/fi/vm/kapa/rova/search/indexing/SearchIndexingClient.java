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
import fi.vm.kapa.rova.ontology.Concept;
import fi.vm.kapa.rova.ptv.model.PtvService;
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

    public enum Index {
        COMPANY("companies"), CONCEPT("concepts"), PTV_SERVICE("ptvservices");
        private String indexName;

        private Index(String indexName) {
            this.indexName = indexName;
        }

        @Override
        public String toString() {
            return indexName;
        }
    }

    private static final Logger LOG = Logger.getLogger(SearchIndexingClient.class);
    protected static final String RIBBON_ENDPOINT = "http://" + SearchIndexing.CLIENT;

    private String apiKey;

    @Autowired
    @Qualifier("indexClientRestTemplate")
    private RestTemplate indexClientRestTemplate;

    @Bean("indexClientRestTemplate")
    @LoadBalanced
    public RestTemplate indexClientRestTemplate() {
        RestTemplate template = RestTemplateFactory.forBackendService(apiKey, -1);
        template.setErrorHandler(ErrorHandlerBuilder.allErrors());
        return template;
    }

    public SearchIndexingClient(@Value("${search_service_indexing_api_key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean indexExists(Index index) throws SearchServiceException {

        String resourceUrl = RIBBON_ENDPOINT + "/index/" + index;
        ResponseEntity<String> response = indexClientRestTemplate.exchange(resourceUrl, HttpMethod.HEAD, null, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return false;
        } else {
            LOG.error("Failed to check if " + index + " index exists (HEAD for " + resourceUrl
                    + " returned HTTP status " + response.getStatusCode().value() + ").");
            throw new SearchServiceException("Failed to check if" + index + "index exists.");
        }
    }

    public void indexCompanies(List<CompanyDTO> companies) throws SearchServiceException {
        index(companies, Index.COMPANY);
    }

    public void indexConcepts(List<Concept> concepts) throws SearchServiceException {
        index(concepts, Index.CONCEPT);
    }

    public void indexPtvServicesToTmpIndex(List<PtvService> ptvServices) throws SearchServiceException {
        String resourceUrl = RIBBON_ENDPOINT + "/index/ptvservices/tmp/index";
        ResponseEntity<String> response = indexClientRestTemplate.postForEntity(resourceUrl, ptvServices, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            LOG.error("Failed to index PTV services to index. (POST for " + resourceUrl + " returned HTTP status "
                    + response.getStatusCode().value() + ").");
            throw new SearchServiceException("Failed to index to PTV tmp index.");
        }
    }

    public void promotePtvServiceTmpIndex() throws SearchServiceException {
        String resourceUrl = RIBBON_ENDPOINT + "/index/ptvservices/tmp/promote";
        ResponseEntity<String> response = indexClientRestTemplate.postForEntity(resourceUrl, null, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            LOG.error("Failed to promote PTV tmp index. (POST for " + resourceUrl + " returned HTTP status "
                    + response.getStatusCode().value() + ").");
            throw new SearchServiceException("Failed to index promote PTV tmp index.");
        }
    }

    private void index(List<? extends Object> documents, Index index) throws SearchServiceException {
        String resourceUrl = RIBBON_ENDPOINT + "/index/" + index;

        ResponseEntity<String> response = indexClientRestTemplate.postForEntity(resourceUrl, documents, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            LOG.error("Failed to index data to " + index + " index. (POST for " + resourceUrl + " returned HTTP status "
                    + response.getStatusCode().value() + ").");
            throw new SearchServiceException("Failed to index " + index + ".");
        }
    }

}
