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
package fi.vm.kapa.rova.external.model.virre;

import java.util.List;

public class CompanyRepresentations {

    private String businessId;
    private String companyName;
    private String companyFormCode;
    private List<PhaseNameType> phases;
    private List<CompanyPerson> companyPersons;

    public CompanyRepresentations() {
        // NOP
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyFormCode() {
        return companyFormCode;
    }

    public void setCompanyFormCode(String companyFormCode) {
        this.companyFormCode = companyFormCode;
    }

    public List<PhaseNameType> getPhases() {
        return phases;
    }

    public void setPhases(List<PhaseNameType> phases) {
        this.phases = phases;
    }

    public List<CompanyPerson> getCompanyPersons() {
        return companyPersons;
    }

    public void setCompanyPersons(List<CompanyPerson> companyPerson) {
        this.companyPersons = companyPerson;
    }
    
}
