
package fi.vm.kapa.rova.engine.model.ypa;

import fi.vm.kapa.rova.engine.evaluation.Evaluable;

public class YpaPerson implements Evaluable {

    private String hetu;
    private String businessId;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
