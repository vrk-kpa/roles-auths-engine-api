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

import fi.vm.kapa.rova.external.model.vtj.VTJResponse;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import fi.vm.kapa.rova.rest.validation.ValidationRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkorkala on 08/03/2017.
 */

//@RibbonClient(name="vtjClient")
public class VTJClient implements VTJ {

    @Autowired
    RestTemplate restTemplate;

    private String apiKey;
    private int requestAliveSeconds;
    private String pathPrefix;

    public VTJClient(String apiKey, int requestAliveSeconds, String pathPrefix) {
        this.apiKey = apiKey;
        this.requestAliveSeconds = requestAliveSeconds;
        this.pathPrefix = pathPrefix;
    }

    @PostConstruct
    public void init() {
        List interceptors = new ArrayList();
        interceptors.add(new ValidationRequestInterceptor(apiKey, requestAliveSeconds, pathPrefix));
        interceptors.add(new RequestIdentificationInterceptor("reqID", "user", RequestIdentificationInterceptor.HeaderTrust.TRUST_REQUEST_HEADERS));
        this.restTemplate.setInterceptors(interceptors);

    }

    public VTJResponse getPerson(String hetu, String schema) throws Exception {
        String requestUrl = VTJ_PERSON;
        requestUrl.replace("{schema}", schema);
        requestUrl.replace("{hetu}", hetu);
        VTJResponse response = this.restTemplate.getForObject(requestUrl, VTJResponse.class);
        return response;
    }

}
