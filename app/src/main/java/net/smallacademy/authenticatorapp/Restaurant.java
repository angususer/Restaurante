package net.smallacademy.authenticatorapp;

public class Restaurant {
    private String name,logo,address,food,wait_time,hours,id;

    public Restaurant(String id,String name, String logo, String address, String food, String wait_time, String hours) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.address = address;
        this.food = food;
        this.wait_time = wait_time;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public String getAddress() {
        return address;
    }

    public String getFood() {
        return food;
    }

    public String getWait_time() {
        return wait_time;
    }

    public String getHours() {
        return hours;
    }

    public String getId() {
        return id;
    }
}
