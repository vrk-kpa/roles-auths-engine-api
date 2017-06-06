package fi.vm.kapa.rova.engine.model.hpa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkorkala on 06/06/2017.
 */
public abstract class AbstractAuthorization {

    private List<DecisionReason> reasons = new ArrayList<DecisionReason>();

    public List<DecisionReason> getReasons() {
        return reasons;
    }

    public void setReasons(List<DecisionReason> reasons) {
        this.reasons = reasons;
    }
}
