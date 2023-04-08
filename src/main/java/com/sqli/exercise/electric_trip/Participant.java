package com.sqli.exercise.electric_trip;


import java.util.ArrayList;
import java.util.List;

public class Participant {

    private int id;

    private String startCityName;

    private City location=new City();

    private double batterySize;

    private double charge;
    private double consume=0;

    private int lowSpeedPerformance;

    private int highSpeedPerformance;

    private List<Path> paths=new ArrayList<>();

    public List<Path> getPaths() {
        return paths;
    }

    public String getStartCityName() {
        return startCityName;
    }

    public Participant(int id, String startCityName, int batterySize, int lowSpeedPerformance, int highSpeedPerformance,List<Path> paths) {
        this.id = id;
        this.startCityName = startCityName;
        this.batterySize = batterySize;
        this.lowSpeedPerformance = lowSpeedPerformance;
        this.highSpeedPerformance = highSpeedPerformance;
        this.paths=paths;
    }



    public int getId() {
        return id;
    }

    public City getLocation() {
        return location;
    }

    public double getBatterySize() {
        return batterySize;
    }

    public int getLowSpeedPerformance() {
        return lowSpeedPerformance;
    }

    public int getHighSpeedPerformance() {
        return highSpeedPerformance;
    }

    public double getConsume() {
        return consume;
    }

    public void setConsume(double consume) {
        this.consume = consume;
    }

    public void setLocation(City location) {
        this.location = location;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getCharge() {
        return charge;
    }
}
