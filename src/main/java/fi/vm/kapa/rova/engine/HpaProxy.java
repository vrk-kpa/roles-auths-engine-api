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

import fi.vm.kapa.rova.client.ApiSessionType;
import fi.vm.kapa.rova.engine.model.hpa.AuthorizationInternal;
import fi.vm.kapa.rova.engine.model.hpa.AuthorizationListInternal;
import fi.vm.kapa.rova.engine.model.hpa.HpaDelegate;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface HpaProxy extends Proxy {

    String ACTION_PROXY_DELEGATE = "proxyDelegate";
    String ACTION_PROXY_AUTHORIZATION = "proxyAuthorization";
    String ACTION_PROXY_PRINCIPAL_CHOICE = "proxyPrincipalFromChoice";

    String GET_PROXY_DELEGATE = "/rest/hpa/proxy/delegate/{serviceIdType}/{apiType}/{service}/{userId}";
    String GET_PROXY_AUTHORIZATION = "/rest/hpa/proxy/authorization/{serviceIdType}/{apiType}/{service}/{delegateId}/{principalId}/{companyId}";
    String GET_PROXY_AUTHORIZATION_LIST = "/rest/hpa/proxy/authorization/list/{serviceIdType}/{apiType}/{service}/{delegateId}/{principalId}/{companyId}";

    ResponseEntity<HpaDelegate> getProxyDelegateResponse(String serviceIdType, ApiSessionType apiType, String service, String userId, String companyId);
    ResponseEntity<AuthorizationInternal> getProxyAuthorizationResponse(String serviceIdType, ApiSessionType apiType, String service, String userId, String companyId, String principalId, Set<String> issues);
    ResponseEntity<AuthorizationListInternal> getProxyAuthorizationListResponse(String serviceIdType, ApiSessionType apiType, String service, String userId, String companyId, String principalId);
}
