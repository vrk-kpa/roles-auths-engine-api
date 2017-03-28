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

import fi.vm.kapa.rova.vare.model.MandateResponse;

import java.util.List;

public interface CheckMandateClient {


    String CHECK_MANDATE = "/rest/vare/checkmandate/{delegateId}/{principalId}/{subject}";
    String MANDATE_EXISTS = "/rest/vare/checkmandate/{delegateId}/{principalId}";
    String COMPANY_PRINCIPALS = "/rest/vare/businessids/{delegateId}";

    /**
     * Resource for checking if there is a mandate for the given parameters.
     */
    MandateResponse checkMandate(String delegateId, String principalId, String subject,
                                 List<String> issues);

    /**
     * Resource for checking if there is a mandate for the given parameters.
     */
    MandateResponse checkMandate(String delegateId, String principalId,
                                 List<String> issues);

    /**
     * Resource for fetching businessIds of all companies which have authorized the person with the given delegateId
     */
    List<String> getCompanyPrincipals(String delegateId);

}
