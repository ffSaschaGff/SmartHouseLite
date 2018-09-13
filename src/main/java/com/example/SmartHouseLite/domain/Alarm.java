package com.example.SmartHouseLite.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alarm {
    @Id
    private Integer id;
    private Integer minute;
    private Integer hour;
    private Boolean enabled;
    private String lastDay;

    public Alarm() {}

    public Alarm(int minute, int hour, boolean enabled) {
        this.minute = minute;
        this.hour = hour;
        this.enabled = enabled;
        this.id = 1;
        this.lastDay = "";
    }

    public String getLastDay() {
        return lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
