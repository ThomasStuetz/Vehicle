package at.htl.vehicle.rest;

import at.htl.vehicle.business.VehicleFacade;
import at.htl.vehicle.entity.Vehicle;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public class VehicleResource {

    long id;
    VehicleFacade vehicleFacade;

    public VehicleResource(long id, VehicleFacade vehicleFacade) {
        this.id = id;
        this.vehicleFacade = vehicleFacade;
    }

    @PUT
    public Vehicle update(Vehicle vehicle) {
        vehicle.setId(id);
        return vehicleFacade.save(vehicle);
    }

    @GET
    public Vehicle find() {
        return vehicleFacade.findById(id);
    }

    @DELETE
    public void delete() {
        vehicleFacade.delete(id);
    }

    @PUT
    @Path("/vignette_valid")
    public Response updateVignetteValid (JsonObject vignetteValidUpdate) {
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
}


