package fi.vm.kapa.rova.engine.model;

import java.util.List;

public class OrganizationalRole {

    Organization organization;
    private List<RoleType> roles;
    
    public OrganizationalRole() {
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

}
