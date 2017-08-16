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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Type that describes which kind of mandates end user is allowed to confirm.
 */
public class ConfirmationRights {

    private List<MandateType> confirmationRights = new ArrayList<>();
    /**
     * Allowed issues for mandates when confirmation right is
     * {@link MandateType#MANDATE_RESTRICTED}.
     */
    private List<String> allowedMandateIssues = new ArrayList<>();
    /**
     * Allowed issues for proxy mandates when confirmation right is
     * {@link MandateType#PROXY_RESTRICTED}.
     */
    private List<String> allowedProxyMandateIssues = new ArrayList<>();

    public List<MandateType> getConfirmationRights() {
        return confirmationRights;
    }

    public void setConfirmationRights(List<MandateType> confirmationRights) {
        this.confirmationRights = confirmationRights;
    }

    public List<String> getAllowedMandateIssues() {
        return allowedMandateIssues;
    }

    public void setAllowedMandateIssues(List<String> allowedMandateIssues) {
        this.allowedMandateIssues = allowedMandateIssues;
    }

    public List<String> getAllowedProxyMandateIssues() {
        return allowedProxyMandateIssues;
    }

    public void setAllowedProxyMandateIssues(List<String> allowedProxyMandateIssues) {
        this.allowedProxyMandateIssues = allowedProxyMandateIssues;
    }

    public List<String> replaceRestrictedTypes() {
        Set<String> allowedIssues = new HashSet<>();
        boolean hasRestricted = false;

        if (confirmationRights.contains(MandateType.MANDATE_RESTRICTED)) {
            hasRestricted = true;
            confirmationRights.remove(MandateType.MANDATE_RESTRICTED);
            confirmationRights.add(MandateType.MANDATE);
            allowedIssues.addAll(allowedMandateIssues);
        }

        if (confirmationRights.contains(MandateType.PROXY_RESTRICTED)) {
            hasRestricted = true;
            confirmationRights.remove(MandateType.PROXY_RESTRICTED);
            confirmationRights.add(MandateType.PROXY);
            allowedIssues.addAll(allowedProxyMandateIssues);
        }

        if (hasRestricted) {
            return new ArrayList<>(allowedIssues);
        } else {
            return null;
        }
    }

    public List<String> replaceRestrictedMandate() {
        if (confirmationRights.contains(MandateType.MANDATE_RESTRICTED)) {
            confirmationRights.remove(MandateType.MANDATE_RESTRICTED);
            confirmationRights.add(MandateType.MANDATE);
            return allowedMandateIssues;
        } else {
            return null;
        }
    }

    public List<String> replaceRestrictedProxy() {
        if (confirmationRights.contains(MandateType.PROXY_RESTRICTED)) {
            confirmationRights.remove(MandateType.PROXY_RESTRICTED);
            confirmationRights.add(MandateType.PROXY);
            return allowedProxyMandateIssues;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "ConfirmationRights [confirmationRights=" + confirmationRights + ", allowedMandateIssues="
                + allowedMandateIssues + ", allowedProxyMandateIssues=" + allowedProxyMandateIssues + "]";
    }

}
