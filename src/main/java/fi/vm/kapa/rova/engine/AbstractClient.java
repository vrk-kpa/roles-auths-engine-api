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

import fi.vm.kapa.rova.ClientException;
import fi.vm.kapa.rova.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by mtom on 13/03/2017.
 */
public abstract class AbstractClient {

    private static final Logger LOG = Logger.getLogger(AbstractClient.class);

    @Value("${engine_api_key}")
    protected String apiKey;

    @Value("${request_alive_seconds}")
    protected int requestAliveSeconds;

    protected abstract RestTemplate getRestTemplate();

    protected void checkStatus(String uri, ResponseEntity<?> entityResponse, HttpStatus ... allowed) {
        List<HttpStatus> statuses = new ArrayList<>(Arrays.asList(new HttpStatus [] { HttpStatus.OK } ));
        if (allowed.length != 0) {
            statuses = new ArrayList<>(Arrays.asList(allowed));
        }
        if (!statuses.contains(entityResponse.getStatusCode())) {
            String errorMessage = "Error status: " + entityResponse.getStatusCode() + " received from " + uri;
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }
    }

}
