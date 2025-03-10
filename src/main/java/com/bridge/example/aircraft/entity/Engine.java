package com.bridge.example.aircraft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String manufacturer;
    private double volume;
    private String fuelType;
    @OneToOne
    private Aircraft aircraft;

    public Engine(String manufacturer, double volume, String fuelType) {
        this.manufacturer = manufacturer;
        this.volume = volume;
        this.fuelType = fuelType;
    }

    public Engine() {
        super();
    }

    public long getId() {
        return this.id;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getVolume() {
        return this.volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + this.id +
                ", manufacturer='" + this.manufacturer + '\'' +
                ", volume=" + this.volume +
                ", fuelType='" + this.fuelType + '\'' +
                '}';
    }
}
