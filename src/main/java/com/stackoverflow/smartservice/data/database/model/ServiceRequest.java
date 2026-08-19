package com.stackoverflow.smartservice.data.database.model;

/** PLACEHOLDER (see DatabaseConnection.java note). Row of the service_requests table. */
public class ServiceRequest {
    private final int requestId;
    private final int source;
    private final int destination;
    private final String category;
    private final String urgency;
    private final String timeSubmitted;
    private final String deadline;
    private final String status;

    public ServiceRequest(int requestId, int source, int destination, String category, String urgency,
                           String timeSubmitted, String deadline, String status) {
        this.requestId = requestId;
        this.source = source;
        this.destination = destination;
        this.category = category;
        this.urgency = urgency;
        this.timeSubmitted = timeSubmitted;
        this.deadline = deadline;
        this.status = status;
    }

    public int getRequestId() { return requestId; }
    public int getSource() { return source; }
    public int getDestination() { return destination; }
    public String getCategory() { return category; }
    public String getUrgency() { return urgency; }
    public String getTimeSubmitted() { return timeSubmitted; }
    public String getDeadline() { return deadline; }
    public String getStatus() { return status; }
}
