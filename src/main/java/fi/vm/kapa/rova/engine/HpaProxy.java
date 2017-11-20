package fi.vm.kapa.rova.engine;

import fi.vm.kapa.rova.engine.model.hpa.AuthorizationInternal;
import fi.vm.kapa.rova.engine.model.hpa.AuthorizationListInternal;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface HpaProxy extends Proxy {

    String ACTION_PROXY_AUTHORIZATION = "proxyAuthorization";

    ResponseEntity<AuthorizationInternal> getAuthorizationResponse(String serviceIdType, String service, String userId, String reprCompanyId, String principalId, Set<String> issues);
    ResponseEntity<AuthorizationListInternal> getAuthorizationListResponse(String serviceIdType, String service, String userId, String reprCompanyId, String principalId);
}
