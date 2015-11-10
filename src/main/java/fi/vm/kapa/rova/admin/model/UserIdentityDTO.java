package fi.vm.kapa.rova.admin.model;

public class UserIdentityDTO {

    private String userUUID;
    private String userIdentifier;
    private String userIdentifierType;

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
