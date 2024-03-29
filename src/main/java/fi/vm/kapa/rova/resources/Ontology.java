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
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by mtom on 17/03/2017.
 */
public interface Ontology extends Resources {
    String GET_CONCEPT = "/rest/ontology/concept";
    String GET_CONCEPTS = "/rest/ontology/concepts";
    String GET_ALL_CONCEPTS = "/rest/ontology/concept/all";

    // actually the passed parameter is principal id (person id or company id), not the type
    String GET_CONCEPTS_BY_PRINCIPAL_TYPE = "/rest/ontology/concept/principalType";

    String IS_BROADER_CONCEPT = "/rest/ontology/concept/is-broader-concept";
    String GET_NARROWER_CONCEPTS = "/rest/ontology/concept/narrower";

    ResponseEntity<Concept> getConceptResponse(String uri);
    ResponseEntity<List<Concept>> getConceptsResponse(List<String> uris);
    ResponseEntity<List<Concept>> getConceptsResponse();
    ResponseEntity<List<Concept>> getConceptsResponse(String principalId);
    ResponseEntity<Boolean> isBroaderConceptResponse(String broaderUri, String narrowerUri);
    ResponseEntity<Set<Concept>> getNarrowerConceptsResponse(String uri);
}
