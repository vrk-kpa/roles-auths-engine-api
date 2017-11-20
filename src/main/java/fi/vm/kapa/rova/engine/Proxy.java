package fi.vm.kapa.rova.engine;

import fi.vm.kapa.rova.admin.model.ApiType;
import fi.vm.kapa.rova.client.ApiSessionType;
import org.springframework.http.ResponseEntity;

public interface Proxy extends Engine {
    String ACTION_PROXY_COMPANIES = "proxyCompanies";

    String GET_PROXY_COMPANIES = "/rest/proxy/companies/{serviceIdType}/{apiType}/{service}/{personId}";

    ResponseEntity<String> getProxyCompanies(String serviceIdType, ApiSessionType apiType, String service, String personId);
}
