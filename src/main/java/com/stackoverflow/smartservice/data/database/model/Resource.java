package com.stackoverflow.smartservice.data.database.model;

/** PLACEHOLDER (see DatabaseConnection.java note). Row of the resources table. */
public class Resource {
    private final int resourceId;
    private final String type;
    private final int homeLocation;
    private final int capacity;
    private final String availabilityStatus;

    public Resource(int resourceId, String type, int homeLocation, int capacity, String availabilityStatus) {
        this.resourceId = resourceId;
        this.type = type;
        this.homeLocation = homeLocation;
        this.capacity = capacity;
        this.availabilityStatus = availabilityStatus;
    }

    public int getResourceId() { return resourceId; }
    public String getType() { return type; }
    public int getHomeLocation() { return homeLocation; }
    public int getCapacity() { return capacity; }
    public String getAvailabilityStatus() { return availabilityStatus; }
}
