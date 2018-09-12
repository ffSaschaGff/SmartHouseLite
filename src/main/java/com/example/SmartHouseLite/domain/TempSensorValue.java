package com.example.SmartHouseLite.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;

@Entity
public class TempSensorValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Timestamp date;
    private Double tempeture;
    private Double pressure;
    private Double humidity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getTempeture() {
        return tempeture;
    }

    public void setTempeture(double tempeture) {
        this.tempeture = tempeture;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public TempSensorValue(Timestamp date, double tempeture, double pressure, double humidity) {
        this.date = date;
        this.tempeture = tempeture;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    TempSensorValue() {}
}