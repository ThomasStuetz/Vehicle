package at.htl.vehicle.rest;

import at.htl.vehicle.business.VehicleFacade;
import at.htl.vehicle.entity.Vehicle;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
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
public class VehicleResource {

    @Inject
    VehicleFacade vehicleFacade;

    @GET
    @Path("{id}")
    public Vehicle find(@PathParam("id") long id) {
        return vehicleFacade.findById(id);
    }

    @GET
    public List<Vehicle> findAll() {
        return vehicleFacade.findAll();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        vehicleFacade.delete(id);
    }

    @PUT
    @Path("{id}")
    public Vehicle update(@PathParam("id") long id, Vehicle vehicle) {
        vehicle.setId(id);
        return vehicleFacade.save(vehicle);
    }

    @PUT
    @Path("{id}/vignette_valid")
    public Response updateVignetteValid (@PathParam("id") long id, JsonObject vignetteValidUpdate) {
        if (!vignetteValidUpdate.containsKey("annualVignetteValid")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("reason", "JSON should contain field annualVignetteValid")
                    .build();
        }
        boolean vignetteValid = vignetteValidUpdate.getBoolean("annualVignetteValid");
        Vehicle vehicle = vehicleFacade.updateVignetteValid(id, vignetteValid);
        if (vehicle == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("reason", "vehicle with id " + id + " does not exist")
                    .build();
        } else {
            return Response.ok(vehicle).build();
        }
    }

    @POST
    public Response save(Vehicle vehicle, @Context UriInfo info) {
        Vehicle saved = this.vehicleFacade.save(vehicle);
        Long id = saved.getId();
        URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).build();
    }
}

