package com.sqli.exercise.electric_trip;

public class Path {

    private String startLocation;
    private  String arriveLocation;
    private int distance;

    public Path(String startLocation, int distance , String arriveLocation) {
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.distance = distance;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getArriveLocation() {
        return arriveLocation;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Path{" +
                "startLocation='" + startLocation + '\'' +
                ", arriveLocation='" + arriveLocation + '\'' +
                ", distance=" + distance +
                '}';
    }
}
