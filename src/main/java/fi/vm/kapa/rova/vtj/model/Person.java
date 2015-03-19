package fi.vm.kapa.rova.vtj.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String ssn;
	
	private String firstNames;
	
	private String lastName;
	
	private List<Person> principals = new ArrayList<Person>();
	
	private PrincipalType principality; // CHILD or EDUNVALVONNASSA

	private boolean deceased; // kuollut
	
	private boolean protectionOrder; // turvakielto
	
	private boolean custody; // huostaanotettu
	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Person> getPrincipals() {
		return principals;
	}

	public void setPrincipals(List<Person> principals) {
		this.principals = principals;
	}

	public PrincipalType getPrincipality() {
		return principality;
	}

	public void setPrincipality(PrincipalType principality) {
		this.principality = principality;
	}

	public boolean isDeceased() {
		return deceased;
	}

	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}

	public boolean isProtectionOrder() {
		return protectionOrder;
	}

	public void setProtectionOrder(boolean protectionOrder) {
		this.protectionOrder = protectionOrder;
	}

	public boolean isCustody() {
		return custody;
	}

	public void setCustody(boolean custody) {
		this.custody = custody;
	}

}
