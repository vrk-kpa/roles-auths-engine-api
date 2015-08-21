package fi.vm.kapa.rova.engine.model;

import java.util.List;
import java.util.Map;

public class OrganizationalPerson {
    private String personIdentifier;
    private String name;
    private Map<Organization, OrganizationalRole> organizationalRoles;
    
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
    
    public Map<Organization, OrganizationalRole> getOrganizationalRoles() {
        return organizationalRoles;
    }

    public void setOrganizationRoles(Map<Organization, OrganizationalRole> organizationalRoles) {
        this.organizationalRoles = organizationalRoles;
    }

    public void addOrganizationalRole(Organization organization, OrganizationalRole role) {
        this.organizationalRoles.put(organization, role);
    }
}
