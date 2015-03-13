package fi.vm.kapa.rova.engine.model;

import java.util.List;

public class Delegate extends BaseBean {

	private String delegateId;
	private List<Principal> principal;
	
	public String getDelegateId() {
		return delegateId;
	}
	public void setDelegateId(String delegateId) {
		this.delegateId = delegateId;
	}
	public List<Principal> getPrincipal() {
		return principal;
	}
	public void setPrincipal(List<Principal> principal) {
		this.principal = principal;
	}

}
