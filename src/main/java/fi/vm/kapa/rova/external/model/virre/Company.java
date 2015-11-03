
package fi.vm.kapa.rova.external.model.virre;

import java.util.List;

public class Company {

    private String businessId;
    private String companyName;
    private List<CompanyRoleType> roles;

    public Company() {
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<CompanyRoleType> getRoles() {
        return roles;
    }

    public void setRoles(List<CompanyRoleType> roles) {
        this.roles = roles;
    }

}
