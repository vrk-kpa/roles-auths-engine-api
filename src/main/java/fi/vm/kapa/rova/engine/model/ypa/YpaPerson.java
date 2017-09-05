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
package fi.vm.kapa.rova.engine.model.ypa;

import fi.vm.kapa.rova.engine.evaluation.Evaluable;
import fi.vm.kapa.rova.external.model.virre.BodyType;
import fi.vm.kapa.rova.external.model.virre.Company;
import fi.vm.kapa.rova.external.model.virre.CompanyRoleType;
import fi.vm.kapa.rova.external.model.virre.RoleNameType;

import java.util.HashMap;
import java.util.Map;

public class YpaPerson implements Evaluable {

    private String hetu;
    private String businessId;
    private Company company;
    private String status;

    public YpaPerson() {
    }

    public YpaPerson(String hetu, String businessId) {
        this.hetu = hetu;
        this.businessId = businessId;
    }

    public String getHetu() {
        return hetu;
    }

    public void setHetu(String hetu) {
        this.hetu = hetu;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getBusinessId() {
        if (company != null) {
            return company.getBusinessId();
        }
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<BodyType, RoleNameType> getRoles() {
        Map<BodyType, RoleNameType> roles = new HashMap<>();
        if (company != null && company.getRoles() != null) {
            for (CompanyRoleType crt : company.getRoles()) {
                roles.put(crt.getBodyType(), crt.getType());
            }
        }
        return roles;
    }

}
