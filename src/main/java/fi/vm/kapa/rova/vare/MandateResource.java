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

/**
 * Created by jkorkala on 09/03/2017.
 */
public interface MandateResource {

    String CHECK_MANDATE = "/rest/vare/checkmandate/{delegateId}/{principalId}/{subject}";
    String MANDATE_EXISTS = "/rest/vare/checkmandate/{delegateId}/{principalId}/";
    String BUSINESS_IDS = "/rest/vare/businessids/{delegateId}/";
    String GET_MANDATE = "/rest/vare/mandate/{uuid}";
    String MANDATE_STATUS = "/rest/vare/mandates/{status}";
    String MANDATES = "/rest/vare/mandates";
    String INVALIDATE_MANDATE = "/rest/vare/mandate/invalidate/{uuid}/{partyId}";
    String DELETE_MANDATES = "/rest/vare/mandates/delete";
    String MANDATE = "/rest/vare/mandate/";
    String CAN_CREATE_MANDATE = "/rest/vare/mandate/creatable";
    String SIGN_MANDATES = "/rest/vare/mandates/";
    String VALIDATE_NAME_AND_ID = "/rest/vare/mandate/validate/nameid";
    String GET_DELEGATES = "/rest/vare/delegates/{type}/principal/{principal}";
    String CONFIRMED_MANDATES = "/rest/vare/mandates/confirmed/principal/{principal}/delegate/{delegate}";
    String CONFIRMED_MANDATES_PAST = "/rest/vare/mandates/confirmed/past/representedParty/{representedParty}/otherParty/{otherParty}";
    String MANDATE_REQUESTS = "/rest/vare/mandates/requests/representedParty/{representedParty}/otherParty/{otherParty}";
    String PRINCIPALS = "/rest/vare/principals/{type}/delegate/{delegate}";
    String MANDATE_PARTIES_PAST = "/rest/vare/mandateparties/past/party/{party}/{type}";
    String MANDATE_REQUEST_PARTIES = "/rest/vare/mandateparties/requests/party/{party}/{type}";
    String MANDATE_PARTY = "/rest/vare/mandateparty/{partyId}";

}
