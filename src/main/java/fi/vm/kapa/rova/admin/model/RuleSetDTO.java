package fi.vm.kapa.rova.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleSetDTO {

    private long id;
    private Map<String, RuleDTO> rules;
    private String type;
    private Map<String, Map<String, String>> ruleConfigMap;
    private Map<String, RuleDTO> fixedRules;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Map<String, RuleDTO> getFixedRules() {
        return fixedRules;
    }

    public void setFixedRules(Map<String, RuleDTO> fixedRules) {
        this.fixedRules = fixedRules;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
