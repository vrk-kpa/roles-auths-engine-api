
package fi.vm.kapa.rova.engine.model;

import fi.vm.kapa.rova.engine.evaluation.Evaluable;

public class CompanyRole implements Evaluable {

    private String hetu;
    private String businessId;

    public CompanyRole() {
    }

    public String getHetu() {
        return hetu;
    }

    public void setHetu(String hetu) {
        this.hetu = hetu;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    
}
