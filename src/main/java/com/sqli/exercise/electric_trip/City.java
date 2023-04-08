package com.sqli.exercise.electric_trip;

public class City {
    private  String cityName="NAN";

    private boolean isChargePlace =false;

    private double kwhChargedPerHour=0;

    public City() {
    }

    public String getCityName() {
        return cityName;
    }

    public boolean isChargePlace() {
        return isChargePlace;
    }

    public double getKwhChargedPerHour() {
        return kwhChargedPerHour;
    }

    public void setChargePlace(boolean chargePlace) {
        isChargePlace = chargePlace;
    }

    public void setKwhChargedPerHour(double kwhChargedPerHour) {
        this.kwhChargedPerHour = kwhChargedPerHour;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
