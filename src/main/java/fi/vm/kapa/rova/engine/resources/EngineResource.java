package fi.vm.kapa.rova.engine.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fi.vm.kapa.rova.engine.model.Authorization;
import fi.vm.kapa.rova.engine.model.Delegate;

@Path("/rova")
public interface EngineResource {

	@GET
	@Path("/delegate/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Delegate getDelegate(@PathParam("personId") String personId, @QueryParam("industry") String industry, @QueryParam("service") String service, @QueryParam("issue") String issue);

	@GET
	@Path("/authorization/{delegateId}/{principalId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Authorization getAuthorization(@PathParam("delegateId") String delegateId, @PathParam("principalId") String principalId, @QueryParam("industry") String industry, @QueryParam("service") String service, @QueryParam("issue") String issue);

}