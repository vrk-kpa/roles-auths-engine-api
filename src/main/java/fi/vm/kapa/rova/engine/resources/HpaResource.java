package fi.vm.kapa.rova.engine.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest")
public interface HpaResource {

    @GET
    @Path("/hpa/delegate/{service}/{enduserId}/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDelegate(@PathParam("personId") String personId, @PathParam("service") String service);

    @GET
    @Path("/hpa/authorization/{service}/{enduserId}/{delegateId}/{principalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorization(@PathParam("delegateId") String delegateId, @PathParam("principalId") String principalId, @PathParam("service") String service);

}
