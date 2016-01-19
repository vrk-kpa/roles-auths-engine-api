package fi.vm.kapa.rova.admin.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ServiceDTO {

    @JsonIgnore
    public static final String SERVIDE_IDENTIFIER_DELIMITER = "_";

    private long id;
    private String name;
    private String xinstance;
    private String memberClass;
    private String memberCode;
    private String subsystemCode;
    private String description;
    private Map<String, RuleSetDTO> ruleSets;
    private ServiceConfigurationDTO serviceConfig;
    private String apiServiceIdentifier;
    private String apiSecret;

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

    @JsonIgnore
    public String getServiceIdentifier() {
        List<String> idParts = Arrays.asList(
                new String [] { xinstance, memberClass, memberCode, subsystemCode });
        return idParts.stream().collect(Collectors.joining(SERVIDE_IDENTIFIER_DELIMITER));
    }

    @JsonIgnore
    public void setServiceIdentifier(String serviceIdentifier) {
        if (serviceIdentifier == null) {
            return;
        }
        List<String> idParts = Arrays.asList(serviceIdentifier.split(SERVIDE_IDENTIFIER_DELIMITER));
        if (idParts.size() >= 4) {
            xinstance = idParts.get(0);
            memberClass = idParts.get(1);
            memberCode = idParts.get(2);
            subsystemCode = idParts.subList(3, idParts.size()).stream()
                    .collect(Collectors.joining(SERVIDE_IDENTIFIER_DELIMITER));
            if(serviceIdentifier.endsWith("_")) {
                subsystemCode = subsystemCode + "_";
            }
            
        }
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

    public String getApiServiceIdentifier() {
        return apiServiceIdentifier;
    }

    public void setApiServiceIdentifier(String apiServiceIdentifier) {
        this.apiServiceIdentifier = apiServiceIdentifier;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }
    
    
}
