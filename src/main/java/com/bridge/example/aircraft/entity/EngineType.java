package com.bridge.example.aircraft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EngineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String manufacturer;
    private double volume;
    private String fuelType;

    public EngineType(String manufacturer, double volume, String fuelType) {
        this.manufacturer = manufacturer;
        this.volume = volume;
        this.fuelType = fuelType;
    }

    public EngineType() {
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
        return "EngineType{" +
                "id=" + this.id +
                ", manufacturer='" + this.manufacturer + '\'' +
                ", volume=" + this.volume +
                ", fuelType='" + this.fuelType + '\'' +
                '}';
    }
}
