package fi.vm.kapa.rova.engine.model;


public class Organization {
    private String identifier;
    private String name;
    private OrganizationType type;
    private SigningCodeType signingCode;
    private String exceptionStatus; 
    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public SigningCodeType getSigningCode() {
        return signingCode;
    }

    public void setSigningCode(SigningCodeType signingCode) {
        this.signingCode = signingCode;
    }

    public String getExceptionStatus() {
        return exceptionStatus;
    }

    public void setExceptionStatus(String exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }

    @Override
    public String toString() {
        return "Organization [identifier=" + identifier + ", name=" + name
                + ", type=" + type + ", signingCode=" + signingCode
                + ", exceptionStatus=" + exceptionStatus + "]";
    }

}
