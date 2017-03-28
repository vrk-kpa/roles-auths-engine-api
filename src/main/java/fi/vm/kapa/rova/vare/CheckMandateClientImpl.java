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
import org.springframework.beans.factory.annotation.Autowired;
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

@RibbonClient(name = "checkMandateClient")
@Conditional(CheckMandateClientCondition.class)
public class CheckMandateClientImpl extends AbstractMandateClient implements CheckMandateClient {

    private static final Logger LOG = Logger.getLogger(CheckMandateClientImpl.class);

    @Autowired
    public CheckMandateClientImpl(@Value("${mandate_api_key}") String apiKey,
                              @Value("${request_alive_seconds}") int requestAliveSeconds,
                              @Value("${mandate_url}") String endpointUrl) {
        super(apiKey, requestAliveSeconds, endpointUrl);
    }

    @Override
    public MandateResponse checkMandate(String delegateId, String principalId, String subject, List<String> issues) {
        UriComponentsBuilder builder = getUriComponentsBuilder(CHECK_MANDATE);
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
    public MandateResponse checkMandate(String delegateId, String principalId, List<String> issues) {
        UriComponentsBuilder builder = getUriComponentsBuilder(MANDATE_EXISTS);
        for (String issue : issues) {
            builder.queryParam("issues", issue);
        }
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        params.put("principalId", principalId);
        return handleSimple(builder, params, MandateResponse.class);
    }

    @Override
    public List<String> getCompanyPrincipals(String delegateId) {
        UriComponentsBuilder builder = getUriComponentsBuilder(COMPANY_PRINCIPALS);
        Map<String, String> params = new HashMap<>();
        params.put("delegateId", delegateId);
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        ResponseEntity<List<String>> response = getRestTemplate().exchange(expandedUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {
                });
        return handleResponse(response, expandedUrl);
    }

    private <T> T handleSimple(UriComponentsBuilder builder, Map<String, String> params, Class<T> returnType) {
        String expandedUrl = builder.buildAndExpand(params).encode().toUriString();
        ResponseEntity<T> response = getRestTemplate().getForEntity(expandedUrl, returnType);
        return handleResponse(response, expandedUrl);
    }
}
