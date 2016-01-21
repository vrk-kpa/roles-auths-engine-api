
package fi.vm.kapa.rova.external.model.virre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class Company {
    public static final String TYPE = Company.class.getName();

    private String businessId;
    private String companyName;
    @JsonIgnore
    private String state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<CompanyRoleType> getRoles() {
        return roles;
    }

    public void setRoles(List<CompanyRoleType> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Company [businessId=" + businessId + ", companyName="
                + companyName + ", state=" + state + ", roles=" + roles + "]";
    }

}
