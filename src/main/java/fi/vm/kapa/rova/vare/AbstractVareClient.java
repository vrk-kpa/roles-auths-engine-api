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
package fi.vm.kapa.rova.vare;

import fi.vm.kapa.rova.ClientException;
import fi.vm.kapa.rova.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class AbstractVareClient {

    private static final Logger LOG = Logger.getLogger(AbstractVareClient.class);

    private String endpointUrl;

    public AbstractVareClient(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    protected UriComponentsBuilder getUriComponentsBuilder(String path) {
        String url = endpointUrl + path;
        return UriComponentsBuilder.fromUriString(url);
    }

    protected UriComponentsBuilder getPagedUriComponentsBuilder(String path, int limit, int offset, boolean ascending) {
        return this.getUriComponentsBuilder(path)
                .queryParam("limit", limit).queryParam("offset", offset).queryParam("ascending", ascending);
    }

    protected <T> T handleResponse(ResponseEntity<T> response, String expandedUrl) {
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            String errorMessage = "Vare connection error: " + response.getStatusCode() + " from URL " + expandedUrl;
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }
    }

}
