package com.sqli.exercise.electric_trip;

public class Path {

    private City startLocation;
    private  City arriveLocation;
    private int distance;

    public Path(City startLocation, int distance , City arriveLocation) {
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.distance = distance;
    }

    public City getStartLocation() {
        return startLocation;
    }

    public City getArriveLocation() {
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
