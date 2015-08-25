package fi.vm.kapa.rova.virre.model;

//TODO: Real VIRRE-model
import fi.vm.kapa.rova.engine.model.OrganizationalPerson;

public class VIRREResponse {
    private OrganizationalPerson organizationalPerson;
       
    public OrganizationalPerson getOrganizationalPerson() {
        return organizationalPerson;
    }
    public void setOrganizationalPerson(OrganizationalPerson organizationalPerson) {
        this.organizationalPerson = organizationalPerson;
    }
}
