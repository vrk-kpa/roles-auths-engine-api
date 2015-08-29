package fi.vm.kapa.rova.engine.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrganizationResult {
    private String name;
    
    private String identifier;
    
    private Set<ResultRoleType> roles = new HashSet<ResultRoleType>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Set<ResultRoleType> getRoles() {
        return roles;
    }

    public void setRoles(Set<ResultRoleType> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "OrganizationResult [name=" + name + ", identifier="
                + identifier + ", roles=" + roles + "]";
    }

}