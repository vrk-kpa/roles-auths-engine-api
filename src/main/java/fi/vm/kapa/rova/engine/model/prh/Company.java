
package fi.vm.kapa.rova.engine.model.prh;

import java.util.List;

public class Company {

    private String businessId;
    private String companyName;
    private List<CompanyRole> roles;

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

    public List<CompanyRole> getRoles() {
        return roles;
    }

    public void setRoles(List<CompanyRole> roles) {
        this.roles = roles;
    }

}
