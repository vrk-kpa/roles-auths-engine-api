
package fi.vm.kapa.rova.external.model.virre;

public class CompanyPerson {
   
    private String firstName;
    private String lastName;
    private String socialSec;
    private CompanyRoleType companyRole; 

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

    public CompanyRoleType getCompanyRole() {
        return companyRole;
    }

    public void setCompanyRole(CompanyRoleType companyRole) {
        this.companyRole = companyRole;
    }
   
}
