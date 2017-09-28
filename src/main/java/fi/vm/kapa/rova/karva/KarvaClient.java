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
package fi.vm.kapa.rova.karva;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import fi.vm.kapa.rova.ClientException;
import fi.vm.kapa.rova.RestTemplateFactory;
import fi.vm.kapa.rova.karva.model.KarvaResponse;
import fi.vm.kapa.rova.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@RibbonClient(name = "roles-auths-karva-client")
@Conditional(KarvaClientCondition.class)
public class KarvaClient implements Karva {

    private static final Logger LOG = Logger.getLogger(KarvaClient.class);
    private static final String KARVA_ENDPOINT_URL = "http://" + Karva.CLIENT;

    private String apiKey;

    private int requestAliveSeconds;

    @Autowired
    @Qualifier("karvaRestTemplate")
    private RestTemplate karvaRestTemplate;

    public KarvaClient(@Value("${karva_client_api_key}") String apiKey,
                       @Value("${request_alive_seconds}") int requestAliveSeconds) {
        super();
        this.apiKey = apiKey;
        this.requestAliveSeconds = requestAliveSeconds;
    }

    @Bean(name = "karvaRestTemplate")
    @LoadBalanced
    public RestTemplate karvaRestTemplate() {
        return RestTemplateFactory.forBackendService(apiKey, requestAliveSeconds);
    }

    @HystrixCommand(commandKey = "KarvaClientGetRoles")
    public KarvaResponse getRoles(String entityId, String personId, boolean includeForeignCompanies, boolean includeSubOrganizations) {
        String requestUrl = KARVA_ENDPOINT_URL + KARVA_ROLES_PATH;

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("personId", personId);
        
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("entityId", entityId);
        queryParams.add("includeForeignCompanies", includeForeignCompanies ? "true" : "false");
        queryParams.add("includeSubOrganizations", includeSubOrganizations ? "true" : "false");
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl).queryParams(queryParams);
        URI uri = builder.buildAndExpand(pathParams).toUri();

        ResponseEntity<KarvaResponse> entityResponse;
        try {
            entityResponse = karvaRestTemplate.getForEntity(uri.toString(), KarvaResponse.class);
        } catch (RestClientException e) {
            String errorMessage = "REST client error from URI " + uri.toString() + ": " + e.getMessage();
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }

        if (entityResponse.getStatusCode() == HttpStatus.OK) {
            return entityResponse.getBody();
        }

        if (entityResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
            return new KarvaResponse(entityId, personId);
        }

        String statusCode = entityResponse.getStatusCode().toString();
        String errorMessage = "Karva error " + statusCode + " for personId " + personId + " from URI " + uri.toString();
        LOG.error(errorMessage);
        throw new ClientException(errorMessage);
    }
}
