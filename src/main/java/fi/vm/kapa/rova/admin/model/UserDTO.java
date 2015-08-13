package fi.vm.kapa.rova.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private String userName;
    private List<UserAuthorizationDTO> userAuthorizations;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public List<UserAuthorizationDTO> getUserAuthorizations() {
        return userAuthorizations;
    }
    public void setUserAuthorizations(List<UserAuthorizationDTO> userAuthorizations) {
        this.userAuthorizations = userAuthorizations;
    }
}
