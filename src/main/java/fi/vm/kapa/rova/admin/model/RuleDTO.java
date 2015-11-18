package fi.vm.kapa.rova.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDTO {

    private String ruleId;
    private String identification;
    private String description;
    private String group;
    private boolean selected;
    private Collection<String> localizationKeys;
    private String ruleType;
    private Map<String, String> defaultConfiguration;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Collection<String> getLocalizationKeys() {
        return localizationKeys;
    }

    public void setLocalizationKeys(Collection<String> localizationKeys) {
        this.localizationKeys = localizationKeys;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public void setDefaultConfiguration(Map<String, String> defaultConfiguration) {
        this.defaultConfiguration = defaultConfiguration;
    }

    public Map<String, String> getDefaultConfiguration() {
        return defaultConfiguration;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
