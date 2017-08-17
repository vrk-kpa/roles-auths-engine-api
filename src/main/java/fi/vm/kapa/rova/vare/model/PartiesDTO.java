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

import java.util.List;

public class PartiesDTO {

    private List<PartyDTO> parties;
    private int limit;
    private int offset;
    private long totalParties;
    private long totalMandates;
    private long totalReceivedRequests;

    public List<PartyDTO> getParties() {
        return parties;
    }

    public void setParties(List<PartyDTO> parties) {
        this.parties = parties;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public long getTotalParties() {
        return totalParties;
    }

    public void setTotalParties(long totalParties) {
        this.totalParties = totalParties;
    }

    public long getTotalMandates() {
        return totalMandates;
    }

    public void setTotalMandates(long totalMandates) {
        this.totalMandates = totalMandates;
    }

	public long getTotalReceivedRequests() {
		return totalReceivedRequests;
	}

	public void setTotalReceivedRequests(long totalReceivedRequests) {
		this.totalReceivedRequests = totalReceivedRequests;
	}
    
}
