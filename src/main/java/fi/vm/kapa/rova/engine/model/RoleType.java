package fi.vm.kapa.rova.engine.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class RoleType {
    private RoleNameType roleName;
    private BodyType bodyType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
    @Override
    public String toString() {
        return "RoleType [roleName=" + roleName + ", bodyType=" + bodyType
                + ", startDate=" + startDate + ", expirationDate="
                + expirationDate + "]";
    }

}
