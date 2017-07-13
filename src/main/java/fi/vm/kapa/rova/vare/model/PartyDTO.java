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
	private long receivedRequests;
    
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
    
    public long getReceivedRequests() {
		return receivedRequests;
	}

	public void setReceivedRequests(long receivedRequests) {
		this.receivedRequests = receivedRequests;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartyDTO other = (PartyDTO) obj;
		if (company != other.company)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (firstNames == null) {
			if (other.firstNames != null)
				return false;
		} else if (!firstNames.equals(other.firstNames))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (numberOfMandates != other.numberOfMandates)
			return false;
		if (receivedRequests != other.receivedRequests)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (company ? 1231 : 1237);
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((firstNames == null) ? 0 : firstNames.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) (numberOfMandates ^ (numberOfMandates >>> 32));
		result = prime * result + (int) (receivedRequests ^ (receivedRequests >>> 32));
		return result;
	}
	
}
