package fi.vm.kapa.rova.admin.model;


public class UserAuthorizationDTO {

    private long id;
    private UserDTO user;
    private String roleName;
    private String serviceUuid;

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

    public String getServiceUuid() {
        return serviceUuid;
    }

    public void setServiceUuid(String serviceUuid) {
        this.serviceUuid = serviceUuid;
    }

    @Override
    public String toString() {
        return "UserAuthorizationDTO [roleName=" + roleName + ", serviceUuid="
                + serviceUuid + "]";
    }
}
