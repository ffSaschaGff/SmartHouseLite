package com.example.SmartHouseLite;

public class OutWeather {
    private static final int REFRESH_TIME = 1000000;

    private static OutWeather lastOutWeater;

    private double tempeture;
    private double pressure;
    private double humidity;

    private long time;

    public double getTempeture() {
        return tempeture;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public long getTime() {
        return time;
    }

    public static OutWeather getOutWeather() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastOutWeater.getTime()) > REFRESH_TIME) {
            refreshWeather();
        }
        return lastOutWeater;
    }

    public OutWeather(double tempeture, double pressure, double humidity) {
        this.tempeture = tempeture;
        this.pressure = pressure;
        this.humidity = humidity;
        this.time = System.currentTimeMillis();
    }

    private static synchronized void refreshWeather() {

    }
}
