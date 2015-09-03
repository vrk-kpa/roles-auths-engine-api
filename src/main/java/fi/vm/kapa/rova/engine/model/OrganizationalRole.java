package fi.vm.kapa.rova.engine.model;

import java.util.List;

import fi.vm.kapa.rova.engine.evaluation.Evaluable;

//@JsonSerialize()
//@JsonIgnoreProperties(ignoreUnknown=true)
public class OrganizationalRole implements Evaluable {
    private String personIdentifier;
    private Organization organization;
    private List<RoleType> roles;
    
    public OrganizationalRole() {
    }
    
    public String getPersonIdentifier() {
        return personIdentifier;
    }
    public void setPersonIdentifier(String personIdentifier) {
        this.personIdentifier = personIdentifier;
    }
    
    public List<RoleType> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleType> roles) {
        this.roles = roles;
    }

    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "["+ personIdentifier +" "+ organization.getIdentifier() +" "+ organization.getName() +" "+ roles +"]";
    }

}
