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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import fi.vm.kapa.rova.ClientException;
import fi.vm.kapa.rova.RestTemplateFactory;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.vtj.model.VTJResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jkorkala on 08/03/2017.
 */
@RibbonClient(name = "roles-auths-vtj-client")
@Conditional(VtjClientCondition.class)
public class VTJClient implements VTJ {

    private static final Logger LOG = Logger.getLogger(VTJClient.class);
    private static final String VTJ_ENDPOINT_URL = "http://" + VTJ.CLIENT;

    private String apiKey;

    private int requestAliveSeconds;


    @Autowired
    @Qualifier("vtjRestTemplate")
    private RestTemplate vtjRestTemplate;

    @Bean(name = "vtjRestTemplate")
    @LoadBalanced
    public RestTemplate vtjRestTemplate() {
        return RestTemplateFactory.forBackendService(apiKey, requestAliveSeconds);
    }

    public VTJClient(@Value("${vtj_client_api_key}") String apiKey,
                     @Value("${request_alive_seconds}") int requestAliveSeconds) {
        super();
        this.apiKey = apiKey;
        this.requestAliveSeconds = requestAliveSeconds;
    }

    @HystrixCommand(commandKey = "VTJClientGetPerson", fallbackMethod = "getPersonFallback")
    public VTJResponse getPerson(String hetu, String schema) {
        RestTemplate restTemplate = vtjRestTemplate;
        String requestUrl = VTJ_ENDPOINT_URL + VTJ_PERSON_PATH;
        Map<String, String> params = new HashMap<>();
        params.put("schema", schema);
        params.put("hetu", hetu);
        ResponseEntity<VTJResponse> entityResponse = restTemplate.getForEntity(requestUrl, VTJResponse.class, params);

        if (entityResponse.getStatusCode() == HttpStatus.OK) {
            return entityResponse.getBody();
        }

        if (entityResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
            VTJResponse response = new VTJResponse();
            response.setSuccess(true);
            response.setError(null);
            response.setPerson(null);
            return response;
        }

        String errorMessage = "Vtj connection error: " + entityResponse.getStatusCode() + " from URL " + requestUrl
                + ". params=" + params;
        LOG.error(errorMessage);
        throw new ClientException(errorMessage);
    }

    @HystrixCommand(commandKey = "VTJClientGetPersonFallback")
    public VTJResponse getPersonFallback(String hetu, String schema) {
        LOG.error("Falling back to unsuccessful response.");
        VTJResponse response = new VTJResponse();
        response.setSuccess(false);
        return response;
    }

}