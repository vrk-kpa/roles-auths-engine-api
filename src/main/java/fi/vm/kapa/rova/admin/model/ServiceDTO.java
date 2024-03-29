/**
 * The MIT License
 * Copyright (c) 2016 Population Register Centre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fi.vm.kapa.rova.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ServiceDTO {

    @JsonIgnore
    public static final String SERVIDE_IDENTIFIER_DELIMITER = "_";

    private long id;
    private String uuid;
    private String name;
    private String xinstance;
    private String memberClass;
    private String memberCode;
    private String subsystemCode;
    private String katsoEntityId;
    private String description;
    private Map<String, RuleSetDTO> ruleSets;
    private ServiceConfigurationDTO serviceConfig;
    private String apiServiceIdentifier;
    private String apiSecret;
    private String apiOauthSecret;
    private List<String> apiOauthRedirectUrls = new ArrayList<>(); 
    private boolean removeApiServiceIdentifier;
    private boolean generateApiServiceIdentifier;
    private boolean generateApiSecret;
    private boolean generateApiOauthSecret;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
    	if (isBlank(xinstance) && isBlank(memberClass) && isBlank(memberCode) && isBlank(subsystemCode)) {
			return null;
		}
        List<String> idParts = Arrays.asList(xinstance, memberClass, memberCode, subsystemCode);
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

    public String getKatsoEntityId() { return katsoEntityId; }

    public void setKatsoEntityId(String katsoEntityId) { this.katsoEntityId = katsoEntityId; }

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


    public String getApiOauthSecret() {
        return apiOauthSecret;
    }

    public void setApiOauthSecret(String apiOauthSecret) {
        this.apiOauthSecret = apiOauthSecret;
    }

    public boolean isGenerateApiServiceIdentifier() {
        return generateApiServiceIdentifier;
    }

    public void setGenerateApiServiceIdentifier(boolean generateApiServiceIdentifier) {
        this.generateApiServiceIdentifier = generateApiServiceIdentifier;
    }

    public boolean isGenerateApiSecret() {
        return generateApiSecret;
    }

    public void setGenerateApiSecret(boolean generateApiSecret) {
        this.generateApiSecret = generateApiSecret;
    }

    public boolean isGenerateApiOauthSecret() {
        return generateApiOauthSecret;
    }

    public void setGenerateApiOauthSecret(boolean generateApiOauthSecret) {
        this.generateApiOauthSecret = generateApiOauthSecret;
    }

    public boolean isRemoveApiServiceIdentifier() {
        return removeApiServiceIdentifier;
    }

    public void setRemoveApiServiceIdentifier(boolean removeApiServiceIdentifier) {
        this.removeApiServiceIdentifier = removeApiServiceIdentifier;
    }

    public List<String> getApiOauthRedirectUrls() {
        return apiOauthRedirectUrls;
    }

    public void setApiOauthRedirectUrls(List<String> apiOauthRedirectUrls) {
        this.apiOauthRedirectUrls = apiOauthRedirectUrls;
    }

    
}
