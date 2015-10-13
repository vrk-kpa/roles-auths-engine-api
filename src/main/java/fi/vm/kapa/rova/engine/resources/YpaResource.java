package fi.vm.kapa.rova.engine.resources;

import fi.vm.kapa.rova.engine.model.OrganizationResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Path("/rest")
public interface YpaResource {

    @GET
    @Path("/ypa/roles/{service}/{enduserId}/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    Set<OrganizationResult> getRoles(@PathParam("personId") String personId, @PathParam("service") String service,
                                     @QueryParam("organizationId") List<String> organizationIds);
}
