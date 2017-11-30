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

import fi.vm.kapa.rova.RestTemplateFactory;
import fi.vm.kapa.rova.engine.model.Company;
import fi.vm.kapa.rova.engine.model.hpa.Principal;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.vare.model.MandateDTO;
import fi.vm.kapa.rova.vare.model.MandateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RibbonClient(name = CheckMandateClient.CHECK_MANDATE_CLIENT)
@Conditional(CheckMandateClientCondition.class)
public class CheckMandateClientImpl extends AbstractVareClient implements CheckMandateClient, CheckProxyMandateClient {

    private static final Logger LOG = Logger.getLogger(CheckMandateClientImpl.class);

    @Autowired
    @Qualifier("vareCheckMandateRestTemplate")
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean("vareCheckMandateRestTemplate")
    public RestTemplate getVareCheckMandateRestTemplate(@Value("${mandate_api_key}") String apiKey,
            @Value("${request_alive_seconds}") int requestAliveSeconds) {
        return RestTemplateFactory.forBackendService(apiKey, requestAliveSeconds);
    }

    @Autowired
    public CheckMandateClientImpl() {
        super("http://" + CheckMandateClient.CHECK_MANDATE_CLIENT);
    }

    @Override
    public MandateResponse checkMandate(String delegateId, String principalId, String subject, List<String> issues) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CheckMandateClient.CHECK_MANDATE);
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        params.put("principalId", principalId);
        params.put("subject", subject);
        return handleSimple(builder, params, MandateResponse.class);
    }

    @Override
    public MandateResponse checkProxyMandate(String personId, String companyId, String principalId, String subject, List<String> issues, PrincipalType principalType) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CheckProxyMandateClient.CHECK_MANDATE);
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        builder.queryParam("principalType", principalType);
        
        Map<String, String> params = new HashMap<>();
        params.put("personId", personId);
        params.put("delegateId", companyId);
        params.put("principalId", principalId);
        params.put("subject", subject);
//        params.put("principalType", principalType.toString());  // TODO path or query parameter?
        
        return handleSimple(builder, params, MandateResponse.class);
    }

    @Override
    public MandateResponse checkMandate(String delegateId, String principalId, List<String> issues) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CheckMandateClient.MANDATE_EXISTS);
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        params.put("principalId", principalId);
        return handleSimple(builder, params, MandateResponse.class);
    }
    
    @Override
    public MandateResponse checkProxyMandate(String personId, String companyId, String principalId, List<String> issues, PrincipalType principalType) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CheckProxyMandateClient.MANDATE_EXISTS);
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        builder.queryParam("principalType", principalType);
        
        Map<String, String> params = new HashMap<>();
        params.put("personId", personId);
        params.put("delegateId", companyId);
        params.put("principalId", principalId);
//        params.put("principalType", principalType.toString());  // TODO path or query parameter?
        
        return handleSimple(builder, params, MandateResponse.class);
    }

    @Override
    public List<String> getCompanyPrincipals(String delegateId) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CheckMandateClient.COMPANY_PRINCIPALS);
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        ResponseEntity<List<String>> response = restTemplate.exchange(expandedUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {
                });
        return handleResponse(response, expandedUrl);
    }

    @Override
    public List<MandateDTO> getAssignmentMandates(String delegateId, Optional<String> principalId) {
        String path = (principalId.isPresent()) ? COMPANY_ASSIGNMENT_MANDATES : ASSIGNMENT_MANDATES;
        UriComponentsBuilder builder = getUriComponentsBuilder(path);
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        if (principalId.isPresent()) {
            params.put("principalId", principalId.get());
        }
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        ResponseEntity<List<MandateDTO>> response = restTemplate.exchange(expandedUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MandateDTO>>() {
                });
        return handleResponse(response, expandedUrl);
    }
    
    @Override
    public List<Company> getProxyCompanies(String personId, List<String> issues) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CheckProxyMandateClient.PROXY_COMPANIES);
        
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        
        Map<String, String> params = new HashMap<>();
        params.put("personId", personId);
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        
        ResponseEntity<List<Company>> response = restTemplate.exchange(expandedUrl, HttpMethod.POST,
                new HttpEntity<>(issues), new ParameterizedTypeReference<List<Company>>() {
                });
        
        return handleResponse(response, expandedUrl);
    }
    
    @Override
    public List<Principal> getMandatePrincipals(String delegateId, List<String> issues) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CheckMandateClient.MANDATE_PRINCIPALS);
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        ResponseEntity<List<Principal>> response = restTemplate.exchange(expandedUrl, HttpMethod.POST,
                new HttpEntity<>(issues), new ParameterizedTypeReference<List<Principal>>() {
                });
        return handleResponse(response, expandedUrl);
    }
    
    @Override
    public List<Company> getMandateProxyPrincipalCompanies(String personId, String companyId, List<String> issues) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CheckProxyMandateClient.COMPANY_PRINCIPALS);
        
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        
        Map<String, String> params = new HashMap<>();
        params.put("personId", personId);
        params.put("delegateId", companyId);
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        
        ResponseEntity<List<Company>> response = restTemplate.exchange(expandedUrl, HttpMethod.POST,
                new HttpEntity<>(issues), new ParameterizedTypeReference<List<Company>>() {});
        
        return handleResponse(response, expandedUrl);
    }
    
    @Override
    public List<Principal> getMandateProxyPrincipalPersons(String personId, String companyId, List<String> issues) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CheckProxyMandateClient.MANDATE_PRINCIPALS);
        
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        
        Map<String, String> params = new HashMap<>();
        params.put("personId", personId);
        params.put("delegateId", companyId);
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        
        ResponseEntity<List<Principal>> response = restTemplate.exchange(expandedUrl, HttpMethod.POST,
                new HttpEntity<>(issues), new ParameterizedTypeReference<List<Principal>>() {});
        
        return handleResponse(response, expandedUrl);
    }

    private <T> T handleSimple(UriComponentsBuilder builder, Map<String, String> params, Class<T> returnType) {
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        ResponseEntity<T> response = restTemplate.getForEntity(expandedUrl, returnType);
        return handleResponse(response, expandedUrl);
    }

}
