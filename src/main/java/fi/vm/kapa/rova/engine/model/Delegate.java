package fi.vm.kapa.rova.engine.model;

import java.util.ArrayList;
import java.util.List;

public class Delegate extends BaseBean {

	private String delegateId;
	private List<Principal> principal;
	
	public Delegate() {
		List<Principal> l=new ArrayList<Principal>();
		this.setPrincipal(l);
	}
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
	
	public void addPrincipal(String personId, String name, String industry, String service, String issue) {
		Principal p=new Principal();
		p.setName(name);
		p.setPersonId(personId);
		p.setIndustry(industry);
		p.setService(service);
		p.setIssue(issue);
		this.principal.add(p);
		
	}

}
