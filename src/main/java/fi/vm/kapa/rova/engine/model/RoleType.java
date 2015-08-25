package fi.vm.kapa.rova.engine.model;

import java.util.Date;

public class RoleType {
    private RoleNameType roleName;
    private BodyType bodyType;
    private Date startDate;
    private Date expirationDate;
    
    public RoleNameType getRoleName() {
        return roleName;
    }
    public void setRoleName(RoleNameType roleName) {
        this.roleName = roleName;
    }
    
    public BodyType getBodyType() {
        return bodyType;
    }
    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
