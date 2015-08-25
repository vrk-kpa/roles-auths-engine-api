package fi.vm.kapa.rova.virre.model;

//TODO: Real VIRRE-model
import fi.vm.kapa.rova.engine.model.OrganizationalRole;

public class VIRREResponse {
    private OrganizationalRole organizationalRole;
       
    public OrganizationalRole getOrganizationalRole() {
        return organizationalRole;
    }
    public void setOrganizationalRole(OrganizationalRole organizationalRole) {
        this.organizationalRole = organizationalRole;
    }
}
