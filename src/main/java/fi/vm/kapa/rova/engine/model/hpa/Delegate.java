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
package fi.vm.kapa.rova.engine.model.hpa;

import fi.vm.kapa.rova.external.model.AuthorizationType;

import java.util.ArrayList;
import java.util.List;

public class Delegate implements HpaDelegate {

    private String delegateId;
    private boolean delegateIdValid;
    private List<Principal> principal;
    private boolean edunvalvonta;
    private boolean hetuValid;
    private boolean deceased;
    private AuthorizationType authorizationType;
    private String serviceUuid;

    private List<DecisionReason> reasons;

    public Delegate() {
        List<Principal> l = new ArrayList<Principal>();
        List<DecisionReason> r = new ArrayList<DecisionReason>();

        this.setPrincipal(l);
        this.setReasons(r);
    }

    @Override
    public String getDelegateId() {
        return delegateId;
    }

    public void setDelegateId(String delegateId) {
        this.delegateId = delegateId;
    }

    public boolean getDelegateIdValid() {
        return delegateIdValid;
    }

    public void setDelegateIdValid(boolean delegateIdValid) {
        this.delegateIdValid = delegateIdValid;
    }

    @Override
    public List<Principal> getPrincipal() {
        return principal;
    }

    public void setPrincipal(List<Principal> principal) {
        this.principal = principal;
    }

    public boolean isEdunvalvonta() {
        return edunvalvonta;
    }

    public void setEdunvalvonta(boolean edunvalvonta) {
        this.edunvalvonta = true;
    }

    public boolean isHetuValid() {
        return hetuValid;
    }

    public void setHetuValid(boolean hetuValid) {
        this.hetuValid = hetuValid;
    }

    public boolean isDeceased() {
        return deceased;
    }

    public void setDeceased(boolean deceased) {
        this.deceased = deceased;
    }

    @Override
    public AuthorizationType getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(AuthorizationType authorizationType) {
        this.authorizationType = authorizationType;
    }

    public void addPrincipal(String personId, String name) {
        Principal p = new Principal();
        p.setName(name);
        p.setPersonId(personId);
        this.principal.add(p);
    }

    @Override
    public List<DecisionReason> getReasons() {
        return reasons;
    }

    public void setReasons(List<DecisionReason> reasons) {
        this.reasons = reasons;
    }

    @Override
    public String getServiceUuid() {
        return serviceUuid;
    }

    public void setServiceUuid(String serviceUuid) {
        this.serviceUuid = serviceUuid;
    }

}
