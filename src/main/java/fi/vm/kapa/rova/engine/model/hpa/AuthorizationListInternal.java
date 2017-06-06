package fi.vm.kapa.rova.engine.model.hpa;

/**
 * Created by jkorkala on 06/06/2017.
 */
public class AuthorizationListInternal extends AuthorizationList {

    private String serviceUuid;

    public String getServiceUuid() {
        return serviceUuid;
    }

    public void setServiceUuid(String serviceUuid) {
        this.serviceUuid = serviceUuid;
    }

}
