
package fi.vm.kapa.rova.engine.model.prh;

public class CompanyPerson {
   
    private String firstName;
    private String lastName;
    private String socialSec;
    private CompanyRole companyRole; 

    public CompanyPerson() {
    }

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

    public CompanyRole getCompanyRole() {
        return companyRole;
    }

    public void setCompanyRole(CompanyRole companyRole) {
        this.companyRole = companyRole;
    }
   
}
