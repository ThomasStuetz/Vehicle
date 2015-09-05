package at.htl.vehicle.business;

import at.htl.vehicle.entity.Vehicle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class VehicleFacade {

    @PersistenceContext
    EntityManager em;

    public Vehicle findById(long id) {
        return this.em.find(Vehicle.class, id);
    }

    public void delete(long id) {
        try {
            Vehicle reference = this.em.getReference(Vehicle.class, id);
            this.em.remove(reference);
        } catch (EntityNotFoundException e) {
            // it's already removed ...
        }
    }

    public List<Vehicle> findAll() {
        return this.em
                .createNamedQuery("Vehicle.findAll", Vehicle.class)
                .getResultList();
    }

    public Vehicle save(Vehicle vehicle) {
        return this.em.merge(vehicle);
    }

    public Vehicle updateVignetteValid(long id, boolean vignetteValid) {
        Vehicle vehicle = this.findById(id);
        if (vehicle == null) {
            return null;
        }
        vehicle.setAnnualVignetteValid(vignetteValid);
        return vehicle;
    }
}



