
package fi.vm.kapa.rova.engine.model.hpa;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fi.vm.kapa.rova.external.model.AuthorizationType;
import java.util.List;

@JsonSerialize(as=HpaDelegate.class)
public interface HpaDelegate {

    String getDelegateId();
    AuthorizationType getAuthorizationType();
    List<Principal> getPrincipal();

}
