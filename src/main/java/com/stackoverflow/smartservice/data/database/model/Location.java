package com.stackoverflow.smartservice.data.database.model;

/**
 * PLACEHOLDER (see DatabaseConnection.java note): plain data holder for a
 * row of the locations table, written to unblock/test the Role 3 loader
 * ahead of Robert's (Role 2) branch.
 */
public class Location {
    private final int locationId;
    private final String name;
    private final String area;
    private final String type;
    private final double latitude;
    private final double longitude;

    public Location(int locationId, String name, String area, String type, double latitude, double longitude) {
        this.locationId = locationId;
        this.name = name;
        this.area = area;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLocationId() { return locationId; }
    public String getName() { return name; }
    public String getArea() { return area; }
    public String getType() { return type; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
