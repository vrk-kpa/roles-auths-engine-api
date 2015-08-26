package fi.vm.kapa.rova.engine.model;

import java.time.LocalDate;
import java.util.Date;

public class RoleType {
    private RoleNameType roleName;
    private BodyType bodyType;
    private LocalDate startDate;
    private LocalDate expirationDate;
    
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
    
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

}
