package at.htl.vehicle.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v")
@XmlRootElement
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String type;
    private boolean annualVignetteValid;

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
    //endregion
}

