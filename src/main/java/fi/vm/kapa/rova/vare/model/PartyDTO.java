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
package fi.vm.kapa.rova.vare.model;

import java.util.Set;

public class PartyDTO {

    private String id;
    private String firstNames;
    private String lastName;
    private boolean company;
    private String companyName;
    private String displayName;
    private long numberOfMandates;
    private long receivedRequests;
    private long newGiven;
    private long newReceived;
    private boolean restricted;
    private Set<MandateType> mandateTypes;

    public PartyDTO() {
        this.company = false;
    }

    public PartyDTO(String id, String firstNames, String lastName, boolean company, String companyName, String displayName, long numberOfMandates, Set<MandateType> mandateTypes) {
        super();
        this.id = id;
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.company = company;
        this.companyName = companyName;
        this.displayName = displayName;
        this.numberOfMandates = numberOfMandates;
        this.mandateTypes = mandateTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isCompany() {
        return company;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getNumberOfMandates() {
        return numberOfMandates;
    }

    public void setNumberOfMandates(long numberOfMandates) {
        this.numberOfMandates = numberOfMandates;
    }
    
    public long getReceivedRequests() {
        return receivedRequests;
    }

    public void setReceivedRequests(long receivedRequests) {
        this.receivedRequests = receivedRequests;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public Set<MandateType> getMandateTypes() {
        return mandateTypes;
    }

    public void setMandateTypes(Set<MandateType> mandateTypes) {
        this.mandateTypes = mandateTypes;
    }

    public long getNewGiven() {
        return newGiven;
    }

    public void setNewGiven(long newGiven) {
        this.newGiven = newGiven;
    }

    public long getNewReceived() {
        return newReceived;
    }

    public void setNewReceived(long newReceived) {
        this.newReceived = newReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartyDTO partyDTO = (PartyDTO) o;

        if (company != partyDTO.company) return false;
        if (numberOfMandates != partyDTO.numberOfMandates) return false;
        if (receivedRequests != partyDTO.receivedRequests) return false;
        if (newGiven != partyDTO.newGiven) return false;
        if (newReceived != partyDTO.newReceived) return false;
        if (restricted != partyDTO.restricted) return false;
        if (id != null ? !id.equals(partyDTO.id) : partyDTO.id != null) return false;
        if (firstNames != null ? !firstNames.equals(partyDTO.firstNames) : partyDTO.firstNames != null) return false;
        if (lastName != null ? !lastName.equals(partyDTO.lastName) : partyDTO.lastName != null) return false;
        if (companyName != null ? !companyName.equals(partyDTO.companyName) : partyDTO.companyName != null)
            return false;
        if (displayName != null ? !displayName.equals(partyDTO.displayName) : partyDTO.displayName != null)
            return false;
        return mandateTypes != null ? mandateTypes.equals(partyDTO.mandateTypes) : partyDTO.mandateTypes == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstNames != null ? firstNames.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (company ? 1 : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (int) (numberOfMandates ^ (numberOfMandates >>> 32));
        result = 31 * result + (int) (receivedRequests ^ (receivedRequests >>> 32));
        result = 31 * result + (int) (newGiven ^ (newGiven >>> 32));
        result = 31 * result + (int) (newReceived ^ (newReceived >>> 32));
        result = 31 * result + (restricted ? 1 : 0);
        result = 31 * result + (mandateTypes != null ? mandateTypes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PartyDTO [id=" + id + ", firstNames=" + firstNames + ", lastName=" + lastName + ", company=" + company
                + ", companyName=" + companyName + ", displayName=" + displayName + ", numberOfMandates="
                + numberOfMandates + ", receivedRequests=" + receivedRequests + ", newGiven=" + newGiven
                + ", newReceived=" + newReceived + ", restricted=" + restricted + ", mandateTypes=" + mandateTypes + "]";
    }

}
