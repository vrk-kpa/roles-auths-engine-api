package fi.vm.kapa.rova.admin.model;


public class UserAuthorizationDTO {

    private long id;
    private UserDTO user;
    private String roleName;
    private String serviceId;

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
    
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "UserAuthorizationDTO [roleName=" + roleName + ", serviceId="
                + serviceId + "]";
    }
}
