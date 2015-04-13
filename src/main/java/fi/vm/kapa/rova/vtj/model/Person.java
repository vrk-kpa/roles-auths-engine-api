package fi.vm.kapa.rova.vtj.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String ssn;
	
	private boolean ssnValid;
	
	private String firstNames;
	
	private String lastName;
	
	private List<Person> principals = new ArrayList<Person>(); // huollettavat (sekä lapset että edunvalvottavat)
	
	private List<Person> guardians = new ArrayList<Person>(); // edunvalvojat
	
	private List<Person> custodians = new ArrayList<Person>(); // huoltajat

	private boolean deceased; // kuollut
	
	private boolean protectionOrder; // turvakielto
	
	private boolean huostaanotettu;
	
	private boolean guardianship; //edunvalvoja määrätty
	
	private boolean guardianshipLimited; //edunvalvonta rajoitettu
	
	private boolean huollonjakoSopimus;
	
	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public boolean isSsnValid() {
		return ssnValid;
	}

	public void setSsnValid(boolean ssnValid) {
		this.ssnValid = ssnValid;
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

	public boolean isHuostaanotettu() {
		return huostaanotettu;
	}

	public void setHuostaanotettu(boolean huostaanotettu) {
		this.huostaanotettu = huostaanotettu;
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
	
	public boolean isGuardianshipLimited() {
		return guardianshipLimited;
	}

	public void setGuardianshipLimited(boolean guardianshipLimited) {
		this.guardianshipLimited = guardianshipLimited;
	}
	
	public boolean isHuollonjakoSopimus() {
		return huollonjakoSopimus;
	}

	public void setHuollonjakoSopimus(boolean huollonjakoSopimus) {
		this.huollonjakoSopimus = huollonjakoSopimus;
	}

	@Override
	public String toString() {
		return "Person [ssn=" + ssn + ", ssnValid=" + ssnValid
				+ ", firstNames=" + firstNames + ", lastName=" + lastName
				+ ", principals=" + principals + ", guardians=" + guardians
				+ ", custodians=" + custodians + ", deceased=" + deceased
				+ ", protectionOrder=" + protectionOrder + ", huostaanotettu="
				+ huostaanotettu + ", guardianship=" + guardianship + ", huollonjakosopimus=" + huollonjakoSopimus + "]";
	}
}
