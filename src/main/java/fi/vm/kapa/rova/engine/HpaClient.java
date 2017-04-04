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

import fi.vm.kapa.rova.engine.model.hpa.AuthorizationInternal;
import fi.vm.kapa.rova.engine.model.hpa.HpaDelegate;
import org.springframework.web.client.RestClientException;

import java.util.Set;

/**
 * Created by mtom on 13/03/2017.
 */
public interface HpaClient {

    final static String CLIENT_NAME = "roles-auths-engine-hpa";

    HpaDelegate getDelegate(String serviceIdType, String personId, String service) throws RestClientException;
    AuthorizationInternal getAuthorization(String serviceIdType, String service, String delegateId, String principalId, Set<String> issues)
            throws RestClientException;
}
