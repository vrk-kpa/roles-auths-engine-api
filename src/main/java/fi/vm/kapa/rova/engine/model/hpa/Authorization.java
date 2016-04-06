package fi.vm.kapa.rova.engine.model.hpa;

import java.util.ArrayList;
import java.util.List;

import fi.vm.kapa.rova.external.model.AuthorizationType;

public class Authorization {

	private AuthorizationType result;
    private List<DecisionReason> reasons = new ArrayList<DecisionReason>();

    public AuthorizationType getResult() {
        return result;
    }

    public void setResult(AuthorizationType result) {
        this.result = result;
    }

    public List<DecisionReason> getReasons() {
        return reasons;
    }

    public void setReasons(List<DecisionReason> reasons) {
        this.reasons = reasons;
    }

    @Override
	public String toString() {
		return result.toString();
	}
}
