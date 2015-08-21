package fi.vm.kapa.rova.engine.model;

import java.util.List;

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

    public void setReasons(List<OrganizationalRole> organizationalRoles) {
        this.organizationalRoles = organizationalRoles;
    }
    
    public void addOrganizationalRole(Organization organization, String role) {
        OrganizationalRole or = new OrganizationalRole();
        or.setOrganization(organization);
        or.addRole(role);
        this.organizationalRoles.add(or);
    }
}
