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
package fi.vm.kapa.rova.resources;

import fi.vm.kapa.rova.ontology.Concept;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by mtom on 17/03/2017.
 */
@RibbonClient(name = "roles-auths-resources-ontology")
@Conditional(OntologyClientCondition.class)
public class OntologyClientImpl extends AbstractClient implements Ontology, OntologyClient {

    public Concept getConcept(String uri) {
        return getConceptResponse(uri).getBody();
    }

    public boolean isBroaderConcept(String broaderUri, String narrowerUri) {
        return isBroaderConceptResponse(broaderUri, narrowerUri).getBody();
    }

    public Set<Concept> getNarrowerConcepts(String uri) {
        return getNarrowerConceptsResponse(uri).getBody();
    }

    @Override
    public List<Concept> getConcepts() {
        return getConceptsResponse().getBody();
    }

    @Override
    public List<Concept> getConcepts(List<String> uriList) {
        return getConceptsResponse(uriList).getBody();
    }

    public ResponseEntity<Concept> getConceptResponse(String uri) {
        RestTemplate restTemplate = getRestTemplate();
        String requestUrl = serviceUrl + GET_CONCEPT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl);
        builder.queryParam("uri", uri);

        ResponseEntity<Concept> entityResponse = restTemplate.getForEntity(builder.toUriString(), Concept.class);
        checkStatus(requestUrl, entityResponse);
        return entityResponse;
    }

    public ResponseEntity<List<Concept>> getConceptsResponse(List<String> uris) {
        RestTemplate restTemplate = getRestTemplate();
        String requestUrl = serviceUrl + GET_CONCEPTS;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl);
        builder.queryParam("uris", uris.toArray());

        ResponseEntity<List<Concept>> entityResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Concept>>() {});
        checkStatus(requestUrl, entityResponse);
        return entityResponse;
    }

    public ResponseEntity<List<Concept>> getConceptsResponse() {
        RestTemplate restTemplate = getRestTemplate();
        String requestUrl = serviceUrl + GET_ALL_CONCEPTS;

        ResponseEntity<List<Concept>> entityResponse = restTemplate.exchange(requestUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Concept>>() {});
        checkStatus(requestUrl, entityResponse);
        return entityResponse;
    }

    public ResponseEntity<Boolean> isBroaderConceptResponse(String broaderUri, String narrowerUri) {
        RestTemplate restTemplate = getRestTemplate();
        String requestUrl = serviceUrl + IS_BROADER_CONCEPT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl);
        builder.queryParam("broader-uri", broaderUri);
        builder.queryParam("narrower-uri", narrowerUri);

        ResponseEntity<Boolean> entityResponse = restTemplate.getForEntity(builder.toUriString(), Boolean.class);
        checkStatus(requestUrl, entityResponse);
        return entityResponse;
    }

    public ResponseEntity<Set<Concept>> getNarrowerConceptsResponse(String uri) {
        RestTemplate restTemplate = getRestTemplate();
        String requestUrl = serviceUrl + GET_NARROWER_CONCEPTS;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl);
        builder.queryParam("uri", uri);

        ResponseEntity<Set<Concept>> entityResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<Concept>>() {});
        checkStatus(requestUrl, entityResponse);
        return entityResponse;
    }

}
