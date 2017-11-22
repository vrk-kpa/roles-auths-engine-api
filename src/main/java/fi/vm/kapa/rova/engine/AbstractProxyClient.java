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
import fi.vm.kapa.rova.client.ApiSessionType;
import fi.vm.kapa.rova.engine.model.Company;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractProxyClient extends AbstractClient implements Proxy {


   @Override
   @HystrixCommand(commandKey = "ClientGetProxyCompanies")
   public List<Company> getProxyCompanies(String serviceIdType, ApiSessionType apiType, String service,
                                           String userId) {
        return getProxyCompaniesResponse(serviceIdType, apiType, service, userId).getBody();
    }

    private ResponseEntity<List<Company>> getProxyCompaniesResponse(String serviceIdType, ApiSessionType apiType, String service,
                                           String userId) {
        RestTemplate restTemplate = getRestTemplate();
        String requestUrl = getRequestUrlBase() + Proxy.GET_PROXY_COMPANIES;

        Map<String, String> params = new HashMap<>();
        params.put("serviceIdType", serviceIdType);
        params.put("apiType", apiType.toString());
        params.put("service", service);
        params.put("userId", userId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl);

        return restTemplate.exchange(builder.buildAndExpand(params).toUri(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Company>>() {
                });
    }

    abstract protected String getRequestUrlBase();
}
