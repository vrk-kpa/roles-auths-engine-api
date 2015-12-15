package fi.vm.kapa.rova.engine.model.hpa;

import java.util.ArrayList;
import java.util.List;

import fi.vm.kapa.rova.external.model.AuthorizationType;

public class Delegate implements HpaDelegate {

    private String delegateId;
    private boolean delegateIdValid;
    private List<Principal> principal;
    private boolean edunvalvonta;
    private boolean hetuValid;
    private boolean deceased;
    private AuthorizationType authorizationType;

    private List<DecisionReason> reasons;

    public Delegate() {
        List<Principal> l = new ArrayList<Principal>();
        List<DecisionReason> r = new ArrayList<DecisionReason>();

        this.setPrincipal(l);
        this.setReasons(r);
    }

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

    public List<DecisionReason> getReasons() {
        return reasons;
    }

    public void setReasons(List<DecisionReason> reasons) {
        this.reasons = reasons;
    }

}
