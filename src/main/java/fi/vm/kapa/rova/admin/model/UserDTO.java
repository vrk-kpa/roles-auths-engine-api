package fi.vm.kapa.rova.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private String uuid;
    private String name;
    private List<UserAuthorizationDTO> userAuthorizations;
    private List<UserIdentityDTO> userIdentities;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<UserAuthorizationDTO> getUserAuthorizations() {
        return userAuthorizations;
    }

    public void setUserAuthorizations(
            List<UserAuthorizationDTO> userAuthorizations) {
        this.userAuthorizations = userAuthorizations;
    }

    public List<UserIdentityDTO> getUserIdentities() {
        return userIdentities;
    }

    public void setUserIdentities(List<UserIdentityDTO> userIdentities) {
        this.userIdentities = userIdentities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
