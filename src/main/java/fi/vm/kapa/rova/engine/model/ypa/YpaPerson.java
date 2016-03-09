package fi.vm.kapa.rova.engine.model.ypa;

import fi.vm.kapa.rova.engine.evaluation.Evaluable;
import fi.vm.kapa.rova.external.model.virre.Company;

public class YpaPerson implements Evaluable {

    private String hetu;
    private String businessId;
    private Company company;
    private String status;

    public String getHetu() {
        return hetu;
    }

    public void setHetu(String hetu) {
        this.hetu = hetu;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getBusinessId() {
        if (company != null) {
            return company.getBusinessId();
        }
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
