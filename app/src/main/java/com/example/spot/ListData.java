package com.example.spot;

public class ListData {
    String name, raiting, city, id, desc, spot_type;
    double latitude, longitude;
    public ListData(String id, String name, String raiting, String city, String spot_type, String desc, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.raiting = raiting;
        this.city = city;
        this.spot_type = spot_type;
        this.desc = desc;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
