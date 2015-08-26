package fi.vm.kapa.rova.virre.model;

import java.util.List;

public class OrganizationalRole {
    private String organizationId;
    private String organizationName;
    private List<OrganizationalRoleInfo> roleInfos;
    private List<OrganizationalRepresentation> representations;
    
    public OrganizationalRole() {}
    
    public String getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
        
    public String getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public List<OrganizationalRoleInfo> getRoleInfos() {
        return roleInfos;
    }
    public void setRoleInfos(List<OrganizationalRoleInfo> roleInfo) {
        this.roleInfos = roleInfo;
    }

    public List<OrganizationalRepresentation> getRepresentations() {
        return representations;
    }
    public void setRepresentations(List<OrganizationalRepresentation> representations) {
        this.representations = representations;
    }

}
