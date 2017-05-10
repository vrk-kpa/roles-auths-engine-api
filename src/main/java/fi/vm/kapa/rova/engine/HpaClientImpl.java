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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import fi.vm.kapa.rova.RestTemplateFactory;
import fi.vm.kapa.rova.engine.model.hpa.AuthorizationInternal;
import fi.vm.kapa.rova.engine.model.hpa.HpaDelegate;
import fi.vm.kapa.rova.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by mtom on 13/03/2017.
 */
@RibbonClient(name = HpaClient.CLIENT_NAME)
@Conditional(HpaClientCondition.class)
public class HpaClientImpl extends AbstractClient implements Hpa, HpaClient {

    private static final Logger LOG = Logger.getLogger(HpaClientImpl.class);

    private static final String RIBBON_ENGINE_URL = "http://" + HpaClient.CLIENT_NAME;

    @Autowired
    @Qualifier("engine-hpa")
    private RestTemplate hpaRestTemplate;

    @Bean("engine-hpa")
    @LoadBalanced
    public RestTemplate hpaRestTemplate() {
        return getRestTemplate();
    }

    @HystrixCommand(commandKey = "HpaClientGetDelegate")
    public HpaDelegate getDelegate(String serviceIdType, String personId, String service) throws RestClientException {
        return getDelegateResponse(serviceIdType,  personId,  service).getBody();
    }

    @HystrixCommand(commandKey = "HpaClientGetAuthorization")
    public AuthorizationInternal getAuthorization(String serviceIdType, String service, String delegateId,
                                                                  String principalId, Set<String> issues) throws RestClientException {
        return getAuthorizationResponse(serviceIdType, service, delegateId, principalId, issues).getBody();
    }

    public ResponseEntity<HpaDelegate> getDelegateResponse(String serviceIdType, String personId, String service) {
        RestTemplate restTemplate = hpaRestTemplate;
        String requestUrl = RIBBON_ENGINE_URL + Hpa.GET_DELEGATE;

        Map<String, String> params = new HashMap<>();
        params.put("serviceIdType", serviceIdType);
        params.put("personId", personId);
        params.put("service", service);

        return restTemplate.getForEntity(requestUrl, HpaDelegate.class, params);
    }

    public ResponseEntity<AuthorizationInternal> getAuthorizationResponse(String serviceIdType, String service, String delegateId,
                                                          String principalId, Set<String> issues) {
        RestTemplate restTemplate = hpaRestTemplate;
        String requestUrl = RIBBON_ENGINE_URL + Hpa.GET_AUTHORIZATION;

        Map<String, String> params = new HashMap<>();
        params.put("serviceIdType", serviceIdType);
        params.put("service", service);
        params.put("delegateId", delegateId);
        params.put("principalId", principalId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl);
        if (issues != null && !issues.isEmpty()) {
            MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
            queryParams.put("issue", new ArrayList<>(issues));
            builder.queryParams(queryParams);
        }

        return restTemplate.getForEntity(builder.buildAndExpand(params).toUri(), AuthorizationInternal.class);
    }

    @Override
    protected RestTemplate getRestTemplate() {
        return RestTemplateFactory.forBackendService(apiKey, requestAliveSeconds);
    }

}
