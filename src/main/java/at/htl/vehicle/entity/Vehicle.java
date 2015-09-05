package at.htl.vehicle.entity;

import at.htl.vehicle.constraints.CrossCheck;
import at.htl.vehicle.constraints.ValidEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v")
@XmlRootElement
@CrossCheck
public class Vehicle implements ValidEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 256)
    private String brand;
    private String type;
    private boolean annualVignetteValid;

    @Version
    private long version;

    public Vehicle() {
    }

    public Vehicle(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }

    //region Getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAnnualVignetteValid() {
        return annualVignetteValid;
    }

    public void setAnnualVignetteValid(boolean annualVignetteValid) {
        this.annualVignetteValid = annualVignetteValid;
    }

    @Override
    public boolean isValid() {
        if (brand == null) {
            return true;
        }
        if (this.brand.length() > 10) {
            return this.type.length() > 10;
        }
        return true;
    }
    //endregion
}

