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
package fi.vm.kapa.rova.karva.model;

import java.util.List;

public class RolesForCompany {

    private static final String FINNISH_BUSINESSID_PREFIX = "1.2.246.10";

    private String companyOid;
    private String finnishBusinessId;  // business identity code (Y-tunnus in Finland)
    private List<String> roles;
    private boolean mainOrganization;

    @SuppressWarnings("unused")
    public RolesForCompany() {
    }

    public RolesForCompany(String companyOid, List<String> roles) {
        this.companyOid = companyOid;
        if (companyOid.startsWith(FINNISH_BUSINESSID_PREFIX)) {
            String str = companyOid.split("\\.")[4];
            if (str.length() == 8) {
                finnishBusinessId = str.substring(0, 7) + "-" + str.substring(7, 8);
            } else if (str.length() == 7) {
                finnishBusinessId = "0" + str.substring(0, 6) + "-" + str.substring(6, 7);
            } else {
                throw new IllegalArgumentException("Business Id part length is incorrect");
            }

            mainOrganization = companyOid.endsWith(".0");
        }
        this.roles = roles;
    }

    public boolean isMainOrganization() {
        return mainOrganization;
    }

    public String getCompanyOid() {
        return companyOid;
    }

    public String getFinnishBusinessId() {
        return finnishBusinessId;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(250);
        sb.append("companyOid=").append(companyOid).append(", finnishBusinessId=").append(finnishBusinessId).append(", roles=[");
        for (int i = 0; i < roles.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(roles.get(i));
        }
        sb.append("]");
        return sb.toString();
    }
}
