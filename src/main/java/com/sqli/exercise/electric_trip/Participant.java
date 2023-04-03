package com.sqli.exercise.electric_trip;

public class Participant {

    private int id;

    private String location;

    private int batterySize;

    private int consume=0;

    private int lowSpeedPerformance;

    private int highSpeedPerformance;

    public Participant(int id, String startCityName, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
        this.id = id;
        this.location = startCityName;
        this.batterySize = batterySize;
        this.lowSpeedPerformance = lowSpeedPerformance;
        this.highSpeedPerformance = highSpeedPerformance;
    }



    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public int getBatterySize() {
        return batterySize;
    }

    public int getLowSpeedPerformance() {
        return lowSpeedPerformance;
    }

    public int getHighSpeedPerformance() {
        return highSpeedPerformance;
    }

    public int getConsume() {
        return consume;
    }

    public void setConsume(int consume) {
        this.consume = consume;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
