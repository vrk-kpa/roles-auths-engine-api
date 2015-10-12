
package fi.vm.kapa.rova.engine.model;

import fi.vm.kapa.rova.engine.evaluation.Evaluable;
import java.util.List;

public class CompanyRole implements Evaluable {

    private String hetu;
    private List<String> businessIds;

    public CompanyRole() {
    }

    public String getHetu() {
        return hetu;
    }

    public void setHetu(String hetu) {
        this.hetu = hetu;
    }

    public List<String> getBusinessIds() {
        return businessIds;
    }

    public void setBusinessIds(List<String> businessIds) {
        this.businessIds = businessIds;
    }
    
}
