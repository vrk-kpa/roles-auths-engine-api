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
package fi.vm.kapa.rova.vtj;

import fi.vm.kapa.rova.client.ClientException;
import fi.vm.kapa.rova.external.model.vtj.VTJResponse;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import fi.vm.kapa.rova.rest.validation.ValidationRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkorkala on 08/03/2017.
 */
@RibbonClient(name = "vtjClient")
@Conditional(VtjClientCondition.class)
public class VTJClient implements VTJ {

    private static final Logger LOG = Logger.getLogger(VTJClient.class);

    @Value("${vtj_client_api_key}")
    private String apiKey;

    @Value("${request_alive_seconds}")
    private int requestAliveSeconds;

    @Value("${vtj_client_url}")
    private String vtjEndpointUrl;


    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ValidationRequestInterceptor(apiKey, requestAliveSeconds));
        interceptors.add(new RequestIdentificationInterceptor(
                RequestIdentificationInterceptor.HeaderTrust.TRUST_REQUEST_HEADERS));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    public VTJResponse getPerson(String hetu, String schema) throws ClientException {
        String requestUrl = vtjEndpointUrl + VTJ_PERSON;
        requestUrl.replace("{schema}", schema);
        requestUrl.replace("{hetu}", hetu);
        RestTemplate restTemplate = getRestTemplate();
        VTJResponse response = restTemplate.getForObject(requestUrl, VTJResponse.class);
        ResponseEntity<VTJResponse> entityResponse = restTemplate.getForEntity(requestUrl, VTJResponse.class);
        if (entityResponse.getStatusCode() == HttpStatus.OK) {
            return response;
        } else {
            String errorMessage = "Vtj connection error: " + entityResponse.getStatusCode() + " from URL " + requestUrl;
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }
    }

}
