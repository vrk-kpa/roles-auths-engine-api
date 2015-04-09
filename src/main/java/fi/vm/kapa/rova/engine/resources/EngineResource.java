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
	@Path("/delegate/{service}/{enduserId}/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Delegate getDelegate(@PathParam("personId") String personId, @QueryParam("industry") String industry, @PathParam("service") String service, @PathParam("enduserId") String endUserId);

	@GET
	@Path("/delegate/{service}/{enduserId}/{personId}/{issue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Delegate getDelegateWithIssue(@PathParam("personId") String personId, @QueryParam("industry") String industry, @PathParam("service") String service, @PathParam("issue") String issue, @PathParam("enduserId") String endUserId);

	@GET
	@Path("/authorization/{service}/{enduserId}/{delegateId}/{principalId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Authorization getAuthorization(@PathParam("delegateId") String delegateId, @PathParam("principalId") String principalId, @QueryParam("industry") String industry, @PathParam("service") String service, @PathParam("enduserId") String endUserId);

	@GET
	@Path("/authorization/{service}/{enduserId}/{delegateId}/{principalId}/{issue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Authorization getAuthorizationWithIssue(@PathParam("delegateId") String delegateId, @PathParam("principalId") String principalId, @QueryParam("industry") String industry, @PathParam("service") String service, @PathParam("issue") String issue, @PathParam("enduserId") String endUserId);

}
