package fi.vm.kapa.rova.vtj.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String ssn;
	
	private String firstNames;
	
	private String lastName;
	
	private List<Person> children = new ArrayList<Person>();
	
	private List<Person> custodians = new ArrayList<Person>();
	
	private List<Person> guardians = new ArrayList<Person>();
	
	private List<Person> parents = new ArrayList<Person>();

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

	public List<Person> getChildren() {
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}

	public List<Person> getCustodians() {
		return custodians;
	}

	public void setCustodians(List<Person> custodians) {
		this.custodians = custodians;
	}

	public List<Person> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Person> guardians) {
		this.guardians = guardians;
	}

	public List<Person> getParents() {
		return parents;
	}

	public void setParents(List<Person> parents) {
		this.parents = parents;
	}

}
