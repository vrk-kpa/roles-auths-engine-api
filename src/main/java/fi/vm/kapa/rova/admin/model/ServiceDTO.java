package fi.vm.kapa.rova.admin.model;

import java.util.Map;

public class ServiceDTO {

    private long id;
    private String name;
    private String serviceIdentifier;
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

    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(String serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
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

}
