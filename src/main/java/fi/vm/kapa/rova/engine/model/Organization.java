package fi.vm.kapa.rova.engine.model;

import java.util.Objects;

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
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.identifier);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        return true;
    }

}
