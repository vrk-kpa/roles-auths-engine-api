package fi.vm.kapa.rova.engine.model;

import java.util.List;
import java.util.Map;

public class OrganizationalPerson {
    private String personIdentifier;
    private String name;
    private List<OrganizationalRole> organizationalRoles;
    
    public String getPersonIdentifier() {
        return personIdentifier;
    }
    public void setPersonIdentifier(String personIdentifier) {
        this.personIdentifier = personIdentifier;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public List<OrganizationalRole> getOrganizationalRoles() {
        return organizationalRoles;
    }
    public void setOrganizationRoles(List<OrganizationalRole> organizationalRoles) {
        this.organizationalRoles = organizationalRoles;
    }
    
}
