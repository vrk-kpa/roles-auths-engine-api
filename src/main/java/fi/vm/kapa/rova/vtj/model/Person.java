package fi.vm.kapa.rova.vtj.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String ssn;
	
	private String firstNames;
	
	private String lastName;
	
	private List<Person> principals = new ArrayList<Person>(); // huollettavat (sekä lapset että edunvalvottavat)
	
	private List<Person> guardians = new ArrayList<Person>(); // edunvalvojat
	
	private List<Person> custodians = new ArrayList<Person>(); // huoltajat

	private boolean deceased; // kuollut
	
	private boolean protectionOrder; // turvakielto
	
	private boolean custody; // huostaanotettu
	
	private boolean guardianship; //edunvalvoja määrätty
	
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

	public List<Person> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Person> guardians) {
		this.guardians = guardians;
	}

	public List<Person> getCustodians() {
		return custodians;
	}

	public void setCustodians(List<Person> custodians) {
		this.custodians = custodians;
	}

	public List<Person> getPrincipals() {
		return principals;
	}

	public void setPrincipals(List<Person> principals) {
		this.principals = principals;
	}

	public boolean isGuardianship() {
		return guardianship;
	}

	public void setGuardianship(boolean guardianship) {
		this.guardianship = guardianship;
	}

}
