package fi.vm.kapa.rova.external.model.virre;

import java.util.List;

public class CompanyRepresentations {

    private String businessId;
    private String companyName;
    private String companyFormCode;
    private List<PhaseNameType> phases;
    private List<CompanyPerson> companyPersons;

    public CompanyRepresentations() {
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

    public String getCompanyFormCode() {
        return companyFormCode;
    }

    public void setCompanyFormCode(String companyFormCode) {
        this.companyFormCode = companyFormCode;
    }

    public List<PhaseNameType> getPhases() {
        return phases;
    }

    public void setPhases(List<PhaseNameType> phases) {
        this.phases = phases;
    }

    public List<CompanyPerson> getCompanyPersons() {
        return companyPersons;
    }

    public void setCompanyPersons(List<CompanyPerson> companyPerson) {
        this.companyPersons = companyPerson;
    }
    
}
