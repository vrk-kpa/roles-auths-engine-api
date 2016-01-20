
package fi.vm.kapa.rova.external.model.virre;

public class CompanyRoleType {

    private RoleNameType type;

    public RoleNameType getType() {
        return type;
    }

    public void setType(RoleNameType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CompanyRoleType [type=" + type + "]";
    }

}
