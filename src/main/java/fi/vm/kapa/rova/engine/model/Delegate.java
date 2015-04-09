package fi.vm.kapa.rova.engine.model;

import java.util.ArrayList;
import java.util.List;

public class Delegate extends BaseBean {

	private String delegateId;
	private boolean delegateIdValid;
	private List<Principal> principal;
	private boolean guardianship;
	private List<DecisionReason> reasons;
	
	public Delegate() {
		List<Principal> l=new ArrayList<Principal>();
		List<DecisionReason> r = new ArrayList<DecisionReason>();
		
		this.setPrincipal(l);
		this.setReasons(r);
	}
	
	public String getDelegateId() {
		return delegateId;
	}
	public void setDelegateId(String delegateId) {
		this.delegateId = delegateId;
	}
	
	public boolean getDelegateIdValid() {
		return delegateIdValid;
	}
	public void setDelegateIdValid(boolean delegateIdValid) {
		this.delegateIdValid = delegateIdValid;
	}

	public List<Principal> getPrincipal() {
		return principal;
	}
	public void setPrincipal(List<Principal> principal) {
		this.principal = principal;
	}
		
	public boolean isGuardianship() {
		return guardianship;
	}
	public void setGuardianship(boolean guardianship) {
		this.guardianship=true;
	}
	
	public void addPrincipal(String personId, String name, String industry, String service, String issue) {
		Principal p=new Principal();
		p.setName(name);
		p.setPersonId(personId);
		p.setIndustry(industry);
		p.setService(service);
		p.setIssue(issue);
		this.principal.add(p);
	}

	public List<DecisionReason> getReasons() {
		return reasons;
	}

	public void setReasons(List<DecisionReason> reasons) {
		this.reasons = reasons;
	}
}
