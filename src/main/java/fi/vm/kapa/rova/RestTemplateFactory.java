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

import fi.vm.kapa.rova.rest.exception.ClientExceptionInterceptor;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor.HeaderTrust;
import fi.vm.kapa.rova.rest.validation.ValidationRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public final class RestTemplateFactory {

    /**
     * RestTemplate for web front services.
     */
    public static RestTemplate forWebFront(String apiKey, int requestAliveSeconds) {
        return restTemplate(apiKey, requestAliveSeconds, HeaderTrust.DONT_TRUST_REQUEST_HEADERS,
                ErrorHandlerBuilder.clientErrorsOnly());
    }

    /**
     * RestTemplate for internal backend services.
     */
    public static RestTemplate forBackendService(String apiKey, int requestAliveSeconds) {
        return restTemplate(apiKey, requestAliveSeconds, HeaderTrust.TRUST_REQUEST_HEADERS,
                ErrorHandlerBuilder.clientErrorsOnly());
    }

    public static RestTemplate restTemplate(String apiKey, int requestAliveSeconds, HeaderTrust trustHeader,
            ResponseErrorHandler errorHandler) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ValidationRequestInterceptor(apiKey, requestAliveSeconds));
        interceptors.add(new RequestIdentificationInterceptor(trustHeader));
        interceptors.add(new ClientExceptionInterceptor());
        restTemplate.setInterceptors(interceptors);
        restTemplate.setErrorHandler(errorHandler);
        return restTemplate;
    }

}
