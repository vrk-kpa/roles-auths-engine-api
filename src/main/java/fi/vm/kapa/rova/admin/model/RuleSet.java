package fi.vm.kapa.rova.admin.model;

import java.util.List;
import java.util.Map;

public class RuleSet {
	
	private List<Rule> rules;
	private String type;
	private Map<String, Map<String, String>> ruleConfigMap;

	public List<Rule> getRules() {
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, Map<String, String>> getRuleConfigMap() {
		return ruleConfigMap;
	}
	public void setRuleConfigMap(Map<String, Map<String, String>> ruleConfigMap) {
		this.ruleConfigMap = ruleConfigMap;
	}
	
}
