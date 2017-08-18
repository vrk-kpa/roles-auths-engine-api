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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConfirmationRightsFactory {

    private static final String ASSIGNMENTPROXY_SUB_DOMAIN = "assignmentproxy.";
    private static final String ASSIGNMENT_SUB_DOMAIN = "assignment.";

    public static ConfirmationRights fromOrganizationRoles(List<String> roles) {
        ConfirmationRights rights = new ConfirmationRights();
        if (roles == null || roles.isEmpty()) {
            rights.setConfirmationRights(Collections.emptyList());

        } else if (roles.contains("TJ") || roles.contains("NIMKO") || roles.contains("ELI")) {
            rights.setConfirmationRights(
                    Arrays.asList(new MandateType[]{MandateType.MANDATE,
                            MandateType.PROXY, MandateType.ASSIGNMENT,
                            MandateType.ASSIGNMENT_PROXY}));
        } else {
            boolean restrictedAssigmentRight = false;
            boolean restrictedAssigmentProxyRight = false;
            for (String role : roles) {
                System.out.println("ZZZZZZZZZZZZ role: "+ role);
                if (role.contains(ASSIGNMENT_SUB_DOMAIN)) {
                    String mandateUri = role.replace(ASSIGNMENT_SUB_DOMAIN, "");
                    rights.getAllowedMandateIssues().add(mandateUri);
                    restrictedAssigmentRight = true;
                } else if (role.contains(ASSIGNMENTPROXY_SUB_DOMAIN)) {
                    String mandateUri = role.replace(ASSIGNMENTPROXY_SUB_DOMAIN, "");
                    rights.getAllowedProxyMandateIssues().add(mandateUri);
                    restrictedAssigmentProxyRight = true;
                }
            }
            if (restrictedAssigmentRight) {
                rights.getConfirmationRights().add(MandateType.MANDATE_RESTRICTED);
            }
            if (restrictedAssigmentProxyRight) {
                rights.getConfirmationRights().add(MandateType.PROXY_RESTRICTED);
            }
        }
        return rights;
    }

    /**
     * Confirmation rights for HPA case.
     */
    public static ConfirmationRights forHpa() {
        ConfirmationRights allowedMandates = new ConfirmationRights();
        allowedMandates.setConfirmationRights(
                Arrays.asList(new MandateType[]{MandateType.MANDATE}));
        return allowedMandates;
    }

}
