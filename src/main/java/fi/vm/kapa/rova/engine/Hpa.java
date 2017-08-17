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
import fi.vm.kapa.rova.engine.model.hpa.AuthorizationListInternal;
import fi.vm.kapa.rova.engine.model.hpa.HpaDelegate;
import org.springframework.http.ResponseEntity;

import java.util.Set;

/**
 * Created by mtom on 14/03/2017.
 */
public interface Hpa extends Engine {
    String ACTION_AUTHORIZATION = "authorization";
    String ACTION_DELEGATE = "delegate";
    String ACTION_PRINCIPAL_CHOICE = "principalFromChoice";

    String GET_DELEGATE = "/rest/hpa/delegate/{serviceIdType}/{service}/{personId}";
    String GET_AUTHORIZATION = "/rest/hpa/authorization/{serviceIdType}/{service}/{delegateId}/{principalId}";
    String GET_AUTHORIZATION_LIST = "/rest/hpa/authorization/list/{serviceIdType}/{service}/{delegateId}/{principalId}";

    ResponseEntity<HpaDelegate> getDelegateResponse(String serviceIdType, String personId, String service);
    ResponseEntity<AuthorizationInternal> getAuthorizationResponse(String serviceIdType, String service, String delegateId, String principalId, Set<String> issues);
    ResponseEntity<AuthorizationListInternal> getAuthorizationListResponse(String serviceIdType, String service, String delegateId, String principalId);

}
