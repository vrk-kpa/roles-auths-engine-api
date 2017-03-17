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
package fi.vm.kapa.rova.engine;

import fi.vm.kapa.rova.engine.model.hpa.Authorization;
import fi.vm.kapa.rova.engine.model.hpa.HpaDelegate;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by mtom on 13/03/2017.
 */
@RibbonClient(name = "hpaClient")
@Conditional(HpaClientCondition.class)
public class HpaClientImpl extends AbstractClient implements HpaClient {

    private static final Logger LOG = Logger.getLogger(HpaClientImpl.class);

    public ResponseEntity<HpaDelegate> getDelegate(String serviceIdType, String personId, String service) {
        RestTemplate restTemplate = getRestTemplate(null);
        String requestUrl = engineUrl + GET_DELEGATE;

        Map<String, String> params = new HashMap<>();
        params.put("serviceIdType", serviceIdType);
        params.put("personId", personId);
        params.put("service", service);

        return restTemplate.getForEntity(requestUrl, HpaDelegate.class, params);
    }

    public ResponseEntity<Authorization> getAuthorization(String serviceIdType, String service, String delegateId,
                                                          String principalId, Set<String> issues) {
        RestTemplate restTemplate = getRestTemplate(null);
        String requestUrl = engineUrl + GET_AUTHORIZATION;

        Map<String, String> params = new HashMap<>();
        params.put("serviceIdType", serviceIdType);
        params.put("service", service);
        params.put("delegateId", delegateId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl);
        if (issues != null && !issues.isEmpty()) {
            MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
            queryParams.put("issue", new ArrayList<>(issues));
            builder.queryParams(queryParams);
        }

        return restTemplate.getForEntity(builder.buildAndExpand(params).toUri(), Authorization.class);
    }

    protected RequestIdentificationInterceptor.HeaderTrust getHeaderTrust() {
        return RequestIdentificationInterceptor.HeaderTrust.TRUST_REQUEST_HEADERS;
    }

}
