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

public class PartyDTO {

    private String id;
    private String firstNames;
    private String lastName;
    private boolean company;
    private String companyName;
    private String displayName;
    private long numberOfMandates;

    public PartyDTO() {
        this.company = false;
    }

    public PartyDTO(String id, String firstNames, String lastName, boolean company, String companyName, String displayName, long numberOfMandates) {
        super();
        this.id = id;
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.company = company;
        this.companyName = companyName;
        this.displayName = displayName;
        this.numberOfMandates = numberOfMandates;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartyDTO partyDTO = (PartyDTO) o;

        if (!getId().equals(partyDTO.getId())) return false;
        if (isCompany() != partyDTO.isCompany()) return false;
        if (getNumberOfMandates() != partyDTO.getNumberOfMandates()) return false;
        if (getFirstNames() != null ? !getFirstNames().equals(partyDTO.getFirstNames()) : partyDTO.getFirstNames() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(partyDTO.getLastName()) : partyDTO.getLastName() != null)
            return false;
        if (getCompanyName() != null ? !getCompanyName().equals(partyDTO.getCompanyName()) : partyDTO.getCompanyName() != null)
            return false;
        return getDisplayName() != null ? getDisplayName().equals(partyDTO.getDisplayName()) : partyDTO.getDisplayName() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getFirstNames() != null ? getFirstNames().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (isCompany() ? 1 : 0);
        result = 31 * result + (getCompanyName() != null ? getCompanyName().hashCode() : 0);
        result = 31 * result + (getDisplayName() != null ? getDisplayName().hashCode() : 0);
        result = 31 * result + (int) (getNumberOfMandates() ^ (getNumberOfMandates() >>> 32));
        return result;
    }
}
