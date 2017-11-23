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

import fi.vm.kapa.rova.engine.model.hpa.Principal;
import fi.vm.kapa.rova.external.model.virre.Company;
import fi.vm.kapa.rova.vare.model.MandateResponse;

import java.util.List;

/**
 * Interface for the proxy mandate case.
 * Proxy mandate case requires two valid mandates: MANDATE and PROXY.
 *
 * Person is the end user which requires authorization.
 * Company is the company which has appointed person to act as delegate.
 * Principal is the party that has authorized the Company that has delegated the Person.
 */

public interface CheckProxyMandateClient {

    String CHECK_MANDATE = "/rest/vare/checkmandate/proxy/{personId}/{delegateId}/{principalId}/{subject}";
    String MANDATE_EXISTS = "/rest/vare/checkmandate/proxy/{personId}/{delegateId}/{principalId}";
    String PROXY_COMPANIES = "/rest/vare/businessids/proxy/{personId}";
    String COMPANY_PRINCIPALS = "/rest/vare/businessids/proxy/{personId}/{delegateId}";
    String MANDATE_PRINCIPALS = "/rest/vare/principals/proxy/{personId}/{delegateId}";

    /**
     * Resource for checking if there is a valid mandate and proxy mandate for the given parameters.
     *
     * @param personId
     * @param companyId
     * @param principalId
     * @param subject
     * @param issues
     * @param principalType
     * @return
     */
    MandateResponse checkProxyMandate(String personId, String companyId, String principalId, String subject, List<String> issues, PrincipalType principalType);

    /**
     * Resource for checking if there is a valid mandate and proxy mandate for the given parameters.
     *
     * @param personId
     * @param companyId
     * @param principalId
     * @param issues
     * @param principalType
     * @return
     */
    MandateResponse checkProxyMandate(String personId, String companyId, String principalId, List<String> issues, PrincipalType principalType);

    /**
     * Resource for fetching businessIds of all companies which have authorized the person to act as company proxy with given issues.
     *
     * @param personId id of the proxy delegate (person representing a company)
     * @param issues issue uris
     * @return companies which have given proxy mandate for the delegate
     */
    List<Company> getProxyCompanies(String personId, List<String> issues);

    /**
     * Resource for fetching businessIds of all companies which have authorized the person with the given companyId and
     * person must have proxy mandate in same issue from the company.
     *
     * @param personId id of the proxy delegate (person representing a company)
     * @param companyId id of the representation company
     * @param issues issue uris
     * @return companies (principals of the mandates given to the representation company)
     */
    List<Company> getMandateProxyPrincipalCompanies(String personId, String companyId, List<String> issues);

    /**
     * Resource for fetching all principals which have given a mandate (of MANDATE type) to the given companyId and
     * person must have proxy mandate in same issue from the company.
     *
     * @param personId id of the proxy delegate (person representing a company)
     * @param companyId id of the representation company
     * @param issues issue uris
     * @return persons (principals of the mandates given to the representation company)
     */
    List<Principal> getMandateProxyPrincipalPersons(String personId, String companyId, List<String> issues);

}
