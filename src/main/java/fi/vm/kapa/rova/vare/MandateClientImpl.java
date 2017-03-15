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
package fi.vm.kapa.rova.vare;

import fi.vm.kapa.rova.ClientException;
import fi.vm.kapa.rova.RovaRestTemplate;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import fi.vm.kapa.rova.vare.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RibbonClient(name = "mandateClient")
@Conditional(CheckMandateClientCondition.class)
public class MandateClientImpl implements MandateResource, CheckMandateClient {

    private static final Logger LOG = Logger.getLogger(MandateClientImpl.class);

    @Value("${mandate_api_key}")
    private String apiKey;

    @Value("${request_alive_seconds}")
    private int requestAliveSeconds;

    @Value("${mandate_url}")
    private String endpointUrl;

    @Override
    public MandateResponse checkMandate(String delegateId, String principalId, String subject, List<String> issues) {
        String url = endpointUrl + CHECK_MANDATE;
        RestTemplate restTemplate = getRestTemplate();
        // {delegateId}/{principalId}/{subject}

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        params.put("principalId", principalId);
        params.put("subject", subject);
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        ResponseEntity<MandateResponse> response = restTemplate.getForEntity(expandedUrl, MandateResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            String errorMessage = "Vare connection error: " + response.getStatusCode() + " from URL " + expandedUrl;
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }
    }

    @Override
    public MandateResponse checkMandate(String delegateId, String principalId, List<String> issues) {
        String url = endpointUrl + MANDATE_EXISTS;
        RestTemplate restTemplate = getRestTemplate();
        // {delegateId}/{principalId}
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        params.put("principalId", principalId);
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        ResponseEntity<MandateResponse> response = restTemplate.getForEntity(expandedUrl, MandateResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            String errorMessage = "Vare connection error: " + response.getStatusCode() + " from URL " + expandedUrl;
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }
    }

    @Override
    public List<String> getCompanyPrincipals(String delegateId) {
        String url = endpointUrl + COMPANY_PRINCIPALS;
        RestTemplate restTemplate = getRestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        ResponseEntity<List<String>> response = restTemplate.exchange(expandedUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {
                });
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            String errorMessage = "Vare connection error: " + response.getStatusCode() + " from URL " + expandedUrl;
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }
    }

    @Override
    public MandateDTO getMandate(String uuid) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<MandateDTO> getMandates(String status, String includeIdsInResponse) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public ResponseEntity<List<MandateDTO>> getMandates(Set<String> uuids) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public ResponseEntity<String> invalidateMandate(String assertion, String uuid, String partyId) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public ResponseEntity<String> deleteMandates(String assertion, Set<String> uuids) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<MandateResult> createMandates(String assertion, List<MandateDTO> mandateDTOs) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<MandateResult> mandatesCanBeCreated(String assertion, List<MandateDTO> mandateDTOs) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<MandateDTO> updateMandates(String assertion, List<MandateDTO> mandateDTOs) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<MandateResult> confirmMandates(String assertion, boolean create, List<MandateDTO> mandates) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public LegalSubjectsDTO validateNameAndId(LegalSubjectsDTO legalSubjects) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public PartiesDTO getDelegates(SearchTypeEnum type, String principalId, int limit, int offset, boolean ascending) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public SimplifiedMandatesDTO getConfirmedMandates(String principalId, String delegateId, String lang, int limit,
            int offset, SortTypeEnum sortBy, boolean ascending) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public SimplifiedMandatesDTO getConfirmedPastMandates(String representedParty, String otherParty, String lang,
            int limit, int offset, SortTypeEnum sortBy, boolean ascending) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public SimplifiedMandatesDTO getMandateRequests(String representedParty, String otherParty, String lang, int limit,
            int offset, SortTypeEnum sortBy, boolean ascending) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public PartiesDTO getPrincipals(SearchTypeEnum type, String delegateId, int limit, int offset, boolean ascending) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public PartiesDTO getPastMandateParties(String partyId, SearchTypeEnum type, int limit, int offset,
            boolean ascending) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public PartiesDTO getMandateRequestParties(String partyId, SearchTypeEnum type, int limit, int offset,
            boolean ascending) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public PartyDTO getMandateParty(String partyId) {
        // TODO Auto-generated method stub
        return null;
    }

    private RestTemplate getRestTemplate() {
        return new RovaRestTemplate(apiKey, requestAliveSeconds,
                RequestIdentificationInterceptor.HeaderTrust.TRUST_REQUEST_HEADERS);
    }

}
