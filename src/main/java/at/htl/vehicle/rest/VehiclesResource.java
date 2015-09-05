package at.htl.vehicle.rest;

import at.htl.vehicle.business.VehicleFacade;
import at.htl.vehicle.entity.Vehicle;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("vehicle")
public class VehiclesResource {

    @Inject
    VehicleFacade vehicleFacade;

    @Path("{id}")
    public VehicleResource find(@PathParam("id") long id) {
        return new VehicleResource(id, vehicleFacade);
    }

    @GET
    public List<Vehicle> findAll() {
        return vehicleFacade.findAll();
    }


    @POST
    public Response save(@Valid Vehicle vehicle, @Context UriInfo info) {
        Vehicle saved = this.vehicleFacade.save(vehicle);
        Long id = saved.getId();
        URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).build();
    }
}

