package fi.vm.kapa.rova.engine.model;

import java.time.LocalDateTime;
import java.util.Date;

public class RoleType {
    private RoleNameType roleName;
    private BodyType bodyType;
    private LocalDateTime startDate;
    private LocalDateTime expirationDate;
    
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
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

}
