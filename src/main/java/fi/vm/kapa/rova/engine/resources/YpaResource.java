package fi.vm.kapa.rova.engine.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest")
public interface YpaResource {

    @GET
    @Path("/ypa/roles/{service}/{enduserId}/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoles(@PathParam("personId") String personId, @PathParam("service") String service);
}
