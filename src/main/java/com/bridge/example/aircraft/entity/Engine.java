package com.bridge.example.aircraft.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serialNumber;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "engine_type_id", referencedColumnName = "id")
    private EngineType engineType;

    public Engine(String serialNumber, EngineType engineType) {
        this.serialNumber = serialNumber;
        this.engineType = engineType;
    }

    public Engine() {
        super();
    }

    public long getId() {
        return this.id;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public EngineType getEngineType() {
        return this.engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + this.id +
                ", serialNumber='" + this.serialNumber + '\'' +
                ", engineType=" + this.engineType +
                '}';
    }
}
