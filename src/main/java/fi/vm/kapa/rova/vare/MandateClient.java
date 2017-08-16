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

import fi.vm.kapa.rova.vare.model.*;

import java.util.List;
import java.util.Set;

public interface MandateClient {

    String MANDATE_CLIENT = "roles-auths-vare-mandate";

    String GET_MANDATE = "/rest/vare/mandate/{uuid}";
    String MANDATE_STATUS = "/rest/vare/mandates/{status}/{partyId}";
    String MANDATES = "/rest/vare/mandates";
    String INVALIDATE_MANDATES = "/rest/vare/mandate/invalidate/{partyId}";
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
    String TOTAL_RECEIVED_REQUESTS = "/rest/vare/mandateparties/requests/totalreceived/{partyId}";
    String MANDATE_PARTY = "/rest/vare/mandateparty/{partyId}";

    MandateDTO getMandate(String uuid);

    List<MandateDTO> getMandates(String status, String partyId, String includeIdsInResponse);

    List<MandateDTO> getMandates(Set<String> uuids);

    void invalidateMandates(String assertion, Set<String> uuids, String partyId);

    void deleteMandates(String assertion, Set<String> uuids);

    List<MandateResult> createMandates(String assertion, List<MandateDTO> mandateDTOs);

    List<MandateResult> mandatesCanBeCreated(String assertion,
                                             List<MandateDTO> mandateDTOs);

    List<MandateDTO> updateMandates(String assertion, List<MandateDTO> mandateDTOs);

    /**
     * Resource for confirming, i.e. signing, a mandate.
     */
    List<MandateResult> confirmMandates(String assertion, boolean create,
                                        List<MandateDTO> mandates);

    LegalSubjectsDTO validateNameAndId(LegalSubjectsDTO legalSubjects);

//    PartiesDTO getDelegates(SearchTypeEnum type, String principalId, List<MandateType> mandateTypes, int limit, int offset,
//                            boolean ascending, PartySortTypeEnum sortBy);

    PartiesDTO getDelegates(SearchTypeEnum type, String principalId, int limit, int offset,
            boolean ascending, PartySortTypeEnum sortBy, ConfirmationRights confirmationRights);

    SimplifiedMandatesDTO getConfirmedMandates(String principalId, String delegateId, List<MandateType> mandateTypes, String lang, int limit,
                                               int offset, SortTypeEnum sortBy, boolean ascending);

    SimplifiedMandatesDTO getConfirmedPastMandates(String representedParty, String otherParty, List<MandateType> mandateTypes, String lang, int limit,
                                                   int offset, SortTypeEnum sortBy, boolean ascending);

    SimplifiedMandatesDTO getMandateRequests(String representedParty, String otherParty, List<MandateType> mandateTypes, String lang, int limit,
                                             int offset, SortTypeEnum sortBy, boolean ascending);

    PartiesDTO getPrincipals(SearchTypeEnum type, String delegateId, List<MandateType> mandateTypes, int limit, int offset,
                             boolean ascending, PartySortTypeEnum sortBy);

    PartiesDTO getPastMandateParties(String partyId, SearchTypeEnum type, List<MandateType> mandateTypes, int limit,
                                     int offset, boolean ascending, PartySortTypeEnum sortBy);

    PartiesDTO getMandateRequestParties(String partyId, SearchTypeEnum type, List<MandateType> mandateTypes, int limit,
                                        int offset, boolean ascending, PartySortTypeEnum sortBy);
    
    /**
     * Number of received mandate requests.
     * 
     * @param partyId Party to whom the requests are sent.
     */
    Long getTotalReceivedRequests(String partyId);

    PartyDTO getMandateParty(String partyId);

}
