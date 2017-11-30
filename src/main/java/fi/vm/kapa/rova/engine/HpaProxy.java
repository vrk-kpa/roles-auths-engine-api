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

public interface HpaProxy extends Engine {

    String ACTION_PROXY_DELEGATE = "proxyDelegate";
    String ACTION_PROXY_AUTHORIZATION = "proxyAuthorization";
    String ACTION_PROXY_PRINCIPAL_CHOICE = "proxyPrincipalFromChoice";

    String GET_PROXY_DELEGATE = "/rest/hpa/proxy/delegate/{serviceIdType}/{apiType}/{serviceId}/{userId}";
    String GET_PROXY_AUTHORIZATION = "/rest/hpa/proxy/authorization/{serviceIdType}/{apiType}/{serviceId}/{delegateId}/{principalId}/{companyId}";
    String GET_PROXY_AUTHORIZATION_LIST = "/rest/hpa/proxy/authorization/list/{serviceIdType}/{apiType}/{serviceId}/{delegateId}/{principalId}/{companyId}";

    /**
     * This endpoint is used e.g. in the WebAPI HPA flow. Before getting to this point in the flow, the user has chosen that he wants to represent a
     * company, and he has already chosen a company to represent. This endpoint returns the persons one whose behalf the user could act as a
     * representative of the chosen company.
     * @param serviceIdType The ID type for the service, @see fi.vm.kapa.rova.external.model.SerServiceIdType
     * @param service The identifier for the service, using the given serviceIdType
     * @param userId Person id for the user
     * @param companyId Company if for the company that the user will represent
     * @return An object containing e.g. a list of the persons that the user is allowed to represent as a representative of the given company.
     */
    ResponseEntity<HpaDelegate> getProxyDelegateResponse(String serviceIdType, ApiSessionType apiType, String service, String userId, String companyId);

    /**
     * Answers the question "Is the delegate allowed to act on behalf of the principal, through proxy mandates from the given company, on the given
     * issues?"
     */
    ResponseEntity<AuthorizationInternal> getProxyAuthorizationResponse(String serviceIdType, ApiSessionType apiType, String service, String userId, String companyId,
                                                                        String principalId, Set<String> issues);

    /**
     * Answers the question "On which issues is the delegate allowed to act on behalf of the principal, through proxy mandates from the given company?"
     */
    ResponseEntity<AuthorizationListInternal> getProxyAuthorizationListResponse(String serviceIdType, ApiSessionType apiType, String service, String userId, String companyId,
                                                                                String principalId);
}
