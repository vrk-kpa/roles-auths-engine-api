
package fi.vm.kapa.rova.external.model.virre;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CompanyPerson {
    public static final String TYPE = CompanyPerson.class.getName();
    
    private String firstName;
    private String lastName;
    private String socialSec;
    private String status;
    private CompanyRoleType companyRole; 

    private List<Company> companies;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSec() {
        return socialSec;
    }

    public void setSocialSec(String socialSec) {
        this.socialSec = socialSec;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
    public CompanyRoleType getCompanyRole() {
        return companyRole;
    }

    public void setCompanyRole(CompanyRoleType companyRole) {
        this.companyRole = companyRole;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }


    @Override
    public String toString() {
        return "CompanyPerson [firstName=" + firstName + ", lastName="
                + lastName + ", socialSec=" + socialSec + ", status=" + status
                + ", companyRole=" + companyRole + ", companies="+ companies +"]";
    }

}
