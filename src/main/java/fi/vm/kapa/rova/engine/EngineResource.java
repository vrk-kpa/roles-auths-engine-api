package fi.vm.kapa.rova.engine;

import fi.vm.kapa.rova.engine.model.Authorization;
import fi.vm.kapa.rova.engine.model.Delegate;

public interface EngineResource {

	public Delegate getDelegate(String personId, String industry, String service, String issue);
	
	public Authorization getAuthorization(String delegateId, String principalId, String industry, String service, String issue);

}
