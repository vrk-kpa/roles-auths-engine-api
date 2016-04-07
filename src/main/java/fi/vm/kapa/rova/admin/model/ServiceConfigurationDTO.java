
package fi.vm.kapa.rova.admin.model;

public class ServiceConfigurationDTO {

    private long id;
    private ServiceDTO serviceId;
    private Boolean includeReasons;
    private Boolean allowMultiSelection;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ServiceDTO getServiceId() {
        return serviceId;
    }

    public void setServiceId(ServiceDTO serviceId) {
        this.serviceId = serviceId;
    }

    public Boolean getIncludeReasons() {
        return includeReasons;
    }

    public void setIncludeReasons(Boolean includeReasons) {
        this.includeReasons = includeReasons;
    }

    public Boolean getAllowMultiSelection() {
        return allowMultiSelection;
    }

    public void setAllowMultiSelection(Boolean allowMultiSelection) {
        this.allowMultiSelection = allowMultiSelection;
    }
}
