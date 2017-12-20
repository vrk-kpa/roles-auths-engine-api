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
import fi.vm.kapa.rova.client.ApiSessionType;
import fi.vm.kapa.rova.engine.model.Company;
import fi.vm.kapa.rova.engine.model.ypa.YpaResult;
import fi.vm.kapa.rova.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mtom on 13/03/2017.
 */
@RibbonClient(name = YpaClient.CLIENT_NAME)
@Conditional(YpaClientCondition.class)
public class YpaClientImpl extends AbstractClient implements Ypa, YpaClient, Proxy {

    private static String RIBBON_CLIENT_ENGINE_URL = "http://" + YpaClient.CLIENT_NAME;


    @Autowired
    @Qualifier("engine-ypa")
    private RestTemplate ypaRestTemplate;

    @Bean("engine-ypa")
    @LoadBalanced
    public RestTemplate ypaRestTemplate() {
        return getRestTemplate();
    }

    protected static final Logger LOG = Logger.getLogger(YpaClientImpl.class);

    @Override
    @HystrixCommand(commandKey = "YpaClientGetRoles")
    public YpaResult getRoles(String personId, String serviceIdType, String service, List<String> organizationIds)
            throws RestClientException {
        return getRolesResponse(personId, serviceIdType, service, organizationIds).getBody();
    }

    @HystrixCommand(commandKey = "ClientGetProxyCompanies")
    public List<Company> getProxyCompanies(String serviceIdType, ApiSessionType apiType, String service,
                                           String userId) {
        return getProxyCompaniesResponse(serviceIdType, apiType, service, userId).getBody();
    }

    private ResponseEntity<List<Company>> getProxyCompaniesResponse(String serviceIdType, ApiSessionType apiType, String service,
                                                                    String userId) {
        RestTemplate restTemplate = ypaRestTemplate;
        String requestUrl = getRequestUrlBase() + Proxy.GET_PROXY_COMPANIES;

        Map<String, String> params = new HashMap<>();
        params.put("serviceIdType", serviceIdType);
        params.put("apiType", apiType.toString());
        params.put("service", service);
        params.put("userId", userId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl);
        URI urlz = builder.buildAndExpand(params).toUri();

        return restTemplate.exchange(urlz, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Company>>() {
                });
    }

    public ResponseEntity<YpaResult> getRolesResponse(String personId, String serviceIdType, String service, List<String> organizationIds) {
        RestTemplate restTemplate = ypaRestTemplate;
        String requestUrl = getRequestUrlBase() + Ypa.GET_ROLES;

        Map<String, String> params = new HashMap<>();
        params.put("personId", personId);
        params.put("serviceIdType", serviceIdType);
        params.put("service", service);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl);
        if (organizationIds != null && !organizationIds.isEmpty()) {
            MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
            queryParams.put("organizationId", organizationIds);
            builder.queryParams(queryParams);
        }

        return restTemplate.exchange(builder.buildAndExpand(params).toUri(),
                HttpMethod.GET, null, new ParameterizedTypeReference<YpaResult>() {
                });
    }

    @Override
    protected RestTemplate getRestTemplate() {
        return RestTemplateFactory.forBackendService(apiKey, requestAliveSeconds);
    }

    protected String getRequestUrlBase() {
        return RIBBON_CLIENT_ENGINE_URL;
    }
}
