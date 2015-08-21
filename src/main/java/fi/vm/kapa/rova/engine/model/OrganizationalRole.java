package fi.vm.kapa.rova.engine.model;

import java.util.Date;
import java.util.Objects;

public class OrganizationalRole {

    Organization organization;
    private RoleNameType roleName;
    private BodyType bodyType;
    private Date startDate;
    private Date expirationDate;

    public OrganizationalRole() {
    }

    public OrganizationalRole(Organization organization, RoleNameType roleName, BodyType bodyType) {
        this.organization = organization;
        this.roleName = roleName;
        this.bodyType = bodyType;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public RoleNameType getRolename() {
        return roleName;
    }

    public void setRolename(RoleNameType rolename) {
        this.roleName = rolename;
    }

    public BodyType getBodytype() {
        return bodyType;
    }

    public void setBodytype(BodyType bodytype) {
        this.bodyType = bodytype;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.organization);
        hash = 67 * hash + Objects.hashCode(this.roleName);
        hash = 67 * hash + Objects.hashCode(this.bodyType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrganizationalRole other = (OrganizationalRole) obj;
        return true;
    }

}
