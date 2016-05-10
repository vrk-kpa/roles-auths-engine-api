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

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.vm.kapa.rova.engine.evaluation.Evaluable;

import java.util.List;

public class Company implements Evaluable {
    public static final String TYPE = Company.class.getName();

    private String businessId;
    private String companyName;
    private String state;
    private List<PhaseNameType> phases;
    private List<CompanyRoleType> roles;

    public Company() {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<PhaseNameType> getPhases() {
        return phases;
    }

    public void setPhases(List<PhaseNameType> phases) {
        this.phases = phases;
    }

    public List<CompanyRoleType> getRoles() {
        return roles;
    }

    public void setRoles(List<CompanyRoleType> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Company [businessId=" + businessId + ", companyName="
                + companyName + ", state=" + state + ", phases=" + phases
                + ", roles=" + roles + "]";
    }

}
