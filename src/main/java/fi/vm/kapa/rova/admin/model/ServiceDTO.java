package fi.vm.kapa.rova.admin.model;

import java.util.Map;

public class ServiceDTO {

    private long id;
    private String name;
    private String serviceIdentifier;
    private String xinstance;
    private String memberClass;
    private String memberCode;
    private String subsystemCode;
    private String description;
    private Map<String, RuleSetDTO> ruleSets;
    private ServiceConfigurationDTO serviceConfig;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, RuleSetDTO> getRuleSets() {
        return ruleSets;
    }

    public void setRuleSets(Map<String, RuleSetDTO> ruleSets) {
        this.ruleSets = ruleSets;
    }

    public ServiceConfigurationDTO getServiceConfig() {
        return serviceConfig;
    }

    public void setServiceConfig(ServiceConfigurationDTO serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(String serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }

    public String getXinstance() {
        return xinstance;
    }

    public void setXinstance(String xinstance) {
        this.xinstance = xinstance;

    }

    public String getMemberClass() {
        return memberClass;
    }

    public void setMemberClass(String memberClass) {
        this.memberClass = memberClass;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getSubsystemCode() {
        return subsystemCode;
    }

    public void setSubsystemCode(String subsystemCode) {
        this.subsystemCode = subsystemCode;
    }
}
