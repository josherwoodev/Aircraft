package com.bridge.example.aircraft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String airframe;
    private String pilot;
    @OneToOne(mappedBy = "aircraft")
    private Engine engine;

    public Aircraft(String airframe, String pilot, Engine engine) {
        this.airframe = airframe;
        this.pilot = pilot;
        this.engine = engine;
    }

    public Aircraft() {
        super();
    }

    public long getId() {
        return this.id;
    }

    public String getAirframe() {
        return this.airframe;
    }

    public void setAirframe(String airframe) {
        this.airframe = airframe;
    }

    public String getPilot() {
        return this.pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + this.id +
                ", airframe='" + this.airframe + '\'' +
                ", pilot='" + this.pilot + '\'' +
                ", engine=" + this.engine +
                '}';
    }
}
