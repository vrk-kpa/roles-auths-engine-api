package fi.vm.kapa.rova.engine.model;

import java.util.ArrayList;
import java.util.List;

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
}
