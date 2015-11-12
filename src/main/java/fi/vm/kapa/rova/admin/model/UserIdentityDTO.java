package fi.vm.kapa.rova.admin.model;

public class UserIdentityDTO {

    private long id;
    private UserDTO user;
    private String userIdentifier;
    private String userIdentifierType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getUserIdentifierType() {
        return userIdentifierType;
    }

    public void setUserIdentifierType(String userIdentifierType) {
        this.userIdentifierType = userIdentifierType;
    }
}
