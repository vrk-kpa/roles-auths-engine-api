package fi.vm.kapa.rova.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleSetDTO {

    private Map<String, RuleDTO> rules;
    private String type;
    private Map<String, Map<String, String>> ruleConfigMap;

    public Map<String, RuleDTO> getRules() {
        return rules;
    }

    public void setRules(Map<String, RuleDTO> rules) {
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
