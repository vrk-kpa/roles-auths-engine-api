package fi.vm.kapa.rova.engine.model;

import java.util.List;

public class OrganizationalRole {
    private Organization organization;
    private List<String> roles;
    
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    public void addRole(String role) {
        this.roles.add(role);
    }
}
