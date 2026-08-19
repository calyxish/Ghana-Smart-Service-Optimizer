package com.stackoverflow.smartservice.data.database.model;

/** PLACEHOLDER (see DatabaseConnection.java note). Row of the roads table. */
public class Road {
    private int roadId; // 0 until assigned by the database (AUTOINCREMENT)
    private final int fromLocationId;
    private final int toLocationId;
    private final double distance;
    private final double travelTime;
    private final double roadConditionWeight;

    public Road(int fromLocationId, int toLocationId, double distance, double travelTime, double roadConditionWeight) {
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.distance = distance;
        this.travelTime = travelTime;
        this.roadConditionWeight = roadConditionWeight;
    }

    public int getRoadId() { return roadId; }
    public void setRoadId(int roadId) { this.roadId = roadId; }
    public int getFromLocationId() { return fromLocationId; }
    public int getToLocationId() { return toLocationId; }
    public double getDistance() { return distance; }
    public double getTravelTime() { return travelTime; }
    public double getRoadConditionWeight() { return roadConditionWeight; }
}
