package fi.vm.kapa.rova.admin.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RuleSetDTO {
	
	private List<RuleDTO> rules;
	private String type;
	private Map<String, Map<String, String>> ruleConfigMap;

	public List<RuleDTO> getRules() {
		return rules;
	}
	public void setRules(List<RuleDTO> rules) {
		this.rules = rules;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, Map<String, String>> getRuleConfig() {
		return ruleConfigMap;
	}
	public void setRuleConfig(Map<String, Map<String, String>> ruleConfigMap) {
		this.ruleConfigMap = ruleConfigMap;
	}
	
}
