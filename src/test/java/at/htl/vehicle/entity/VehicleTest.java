package at.htl.vehicle.entity;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class VehicleTest {

    @Test
    public void validVehicle() {
        Vehicle valid = new Vehicle("abcdefghijk", "ABCDEFGHIJK");
        assertThat(valid.isValid(),is(true));     // hamcrest
        //assertTrue(valid.isValid());            // junit
    }

    @Test
    public void invalidVehicle() {
        Vehicle valid = new Vehicle("abcdefghijk", "ABC");
        assertThat(valid.isValid(),is(false));   // hamcrest
        //assertFalse(valid.isValid());          // junit
    }

    @Test
    public void vehicleWithoutBrand() {
        Vehicle valid = new Vehicle(null, "Kadett");
        assertThat(valid.isValid(),is(true));
    }
}

