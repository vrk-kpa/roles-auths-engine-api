package fi.vm.kapa.rova.engine;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fi.vm.kapa.rova.engine.model.Authorization;
import fi.vm.kapa.rova.engine.model.Delegate;

@Path("/rova")
public interface EngineResource {

	@GET
	@Path("/delegate")
	public Delegate getDelegate(String personId, String industry, String service, String issue);

	@GET
	@Path("/authorization")
	public Authorization getAuthorization(String delegateId, String principalId, String industry, String service, String issue);

}
