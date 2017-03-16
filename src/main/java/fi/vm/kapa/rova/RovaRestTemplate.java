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
package fi.vm.kapa.rova;

import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor.HeaderTrust;
import fi.vm.kapa.rova.rest.validation.ValidationRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RovaRestTemplate extends RestTemplate {

    public RovaRestTemplate(String endUser, String apiKey, int requestAliveSeconds, HeaderTrust trustHeader) {
        super();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ValidationRequestInterceptor(apiKey, requestAliveSeconds));
        interceptors.add(new RequestIdentificationInterceptor(null, endUser, trustHeader));
        setInterceptors(interceptors);
    }

    public RovaRestTemplate(String endUser, String apiKey, int requestAliveSeconds, HeaderTrust trustHeader,
            ResponseErrorHandler errorHandler) {
        this(endUser, apiKey, requestAliveSeconds, trustHeader);
        setErrorHandler(errorHandler);
    }

    public RovaRestTemplate(String apiKey, int requestAliveSeconds, HeaderTrust trustHeader,
                            ResponseErrorHandler errorHandler) {
        this(null, apiKey, requestAliveSeconds, trustHeader, errorHandler);
    }

    public RovaRestTemplate(String apiKey, int requestAliveSeconds, HeaderTrust trustHeader) {
        this(apiKey, requestAliveSeconds, trustHeader, null);
    }
}
