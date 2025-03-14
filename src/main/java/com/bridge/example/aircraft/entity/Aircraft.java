package com.bridge.example.aircraft.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Aircraft")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String airframe;
    private String pilot;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id", referencedColumnName = "id")
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

    public void setEngine(Engine engineType) {
        this.engine = engineType;
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
