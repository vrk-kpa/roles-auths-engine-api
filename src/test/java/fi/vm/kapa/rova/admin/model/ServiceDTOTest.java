package fi.vm.kapa.rova.admin.model;

import org.junit.Assert;
import org.junit.Test;

public class ServiceDTOTest {
    
    @Test
    public void testSettingServiceIdentifier() {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceIdentifier("FI-DEV_GOV_0245458-3_IFI_Dev_RoVa_client_");
        Assert.assertEquals("FI-DEV",serviceDTO.getXinstance());
        Assert.assertEquals("GOV",serviceDTO.getMemberClass());
        Assert.assertEquals("0245458-3",serviceDTO.getMemberCode());
        Assert.assertEquals("IFI_Dev_RoVa_client_",serviceDTO.getSubsystemCode());
        
        Assert.assertEquals("FI-DEV_GOV_0245458-3_IFI_Dev_RoVa_client_",serviceDTO.getServiceIdentifier());
    }
    
}
