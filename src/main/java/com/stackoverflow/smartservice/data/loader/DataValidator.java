package com.stackoverflow.smartservice.data.loader;

import com.stackoverflow.smartservice.data.database.model.Location;
import com.stackoverflow.smartservice.data.database.model.Resource;
import com.stackoverflow.smartservice.data.database.model.Road;
import com.stackoverflow.smartservice.data.database.model.ServiceRequest;

import java.util.Set;

/**
 * Validates parsed CSV records before they reach the database.
 *
 * Every validate* method either returns a fully-formed model object or
 * throws a {@link ValidationException} describing exactly why the record
 * was rejected (missing field, bad type, out-of-range value, duplicate
 * ID, ...). Callers are expected to catch the exception, log it, and skip
 * the record rather than let a single bad row halt the whole load.
 */
public class DataValidator {

    private static final Set<String> VALID_URGENCY = Set.of("LOW", "MEDIUM", "HIGH", "CRITICAL");
    private static final Set<String> VALID_STATUS = Set.of("PENDING", "IN_PROGRESS", "COMPLETED", "CANCELLED");
    private static final Set<String> VALID_RESOURCE_STATUS = Set.of("AVAILABLE", "IN_USE", "UNAVAILABLE", "MAINTENANCE");

    /** Thrown when a record fails validation; the message is the human-readable reason. */
    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }

    public Location validateLocation(String[] row, Set<Integer> seenIds) throws ValidationException {
        requireFieldCount(row, 6, "locations");

        int locationId = requireInt(row[0], "locationId");
        String name = requireNonBlank(row[1], "name");
        String area = requireNonBlank(row[2], "area");
        String type = requireNonBlank(row[3], "type");
        double latitude = requireDouble(row[4], "latitude");
        double longitude = requireDouble(row[5], "longitude");

        requireUnique(locationId, seenIds, "locationId");
        if (latitude < -90 || latitude > 90) {
            throw new ValidationException("latitude " + latitude + " is out of range [-90, 90]");
        }
        if (longitude < -180 || longitude > 180) {
            throw new ValidationException("longitude " + longitude + " is out of range [-180, 180]");
        }

        seenIds.add(locationId);
        return new Location(locationId, name, area, type, latitude, longitude);
    }

    public Road validateRoad(String[] row, Set<Integer> validLocationIds) throws ValidationException {
        requireFieldCount(row, 5, "roads");

        int fromLocationId = requireInt(row[0], "fromLocationId");
        int toLocationId = requireInt(row[1], "toLocationId");
        double distance = requireDouble(row[2], "distance");
        double travelTime = requireDouble(row[3], "travelTime");
        double roadConditionWeight = requireDouble(row[4], "roadConditionWeight");

        requireKnownLocation(fromLocationId, validLocationIds, "fromLocationId");
        requireKnownLocation(toLocationId, validLocationIds, "toLocationId");
        if (distance < 0) {
            throw new ValidationException("distance " + distance + " cannot be negative");
        }
        if (travelTime < 0) {
            throw new ValidationException("travelTime " + travelTime + " cannot be negative");
        }
        if (roadConditionWeight <= 0) {
            throw new ValidationException("roadConditionWeight " + roadConditionWeight + " must be positive");
        }

        return new Road(fromLocationId, toLocationId, distance, travelTime, roadConditionWeight);
    }

    public ServiceRequest validateServiceRequest(String[] row, Set<Integer> seenIds, Set<Integer> validLocationIds)
            throws ValidationException {
        requireFieldCount(row, 8, "service_requests");

        int requestId = requireInt(row[0], "requestId");
        int source = requireInt(row[1], "source");
        int destination = requireInt(row[2], "destination");
        String category = requireNonBlank(row[3], "category");
        String urgency = requireNonBlank(row[4], "urgency").toUpperCase();
        String timeSubmitted = requireNonBlank(row[5], "timeSubmitted");
        String deadline = requireNonBlank(row[6], "deadline");
        String status = requireNonBlank(row[7], "status").toUpperCase();

        requireUnique(requestId, seenIds, "requestId");
        requireKnownLocation(source, validLocationIds, "source");
        requireKnownLocation(destination, validLocationIds, "destination");
        if (!VALID_URGENCY.contains(urgency)) {
            throw new ValidationException("urgency '" + urgency + "' is not one of " + VALID_URGENCY);
        }
        if (!VALID_STATUS.contains(status)) {
            throw new ValidationException("status '" + status + "' is not one of " + VALID_STATUS);
        }

        seenIds.add(requestId);
        return new ServiceRequest(requestId, source, destination, category, urgency, timeSubmitted, deadline, status);
    }

    public Resource validateResource(String[] row, Set<Integer> seenIds, Set<Integer> validLocationIds)
            throws ValidationException {
        requireFieldCount(row, 5, "resources");

        int resourceId = requireInt(row[0], "resourceId");
        String type = requireNonBlank(row[1], "type");
        int homeLocation = requireInt(row[2], "homeLocation");
        int capacity = requireInt(row[3], "capacity");
        String availabilityStatus = requireNonBlank(row[4], "availabilityStatus").toUpperCase();

        requireUnique(resourceId, seenIds, "resourceId");
        requireKnownLocation(homeLocation, validLocationIds, "homeLocation");
        if (capacity <= 0) {
            throw new ValidationException("capacity " + capacity + " must be positive");
        }
        if (!VALID_RESOURCE_STATUS.contains(availabilityStatus)) {
            throw new ValidationException("availabilityStatus '" + availabilityStatus + "' is not one of " + VALID_RESOURCE_STATUS);
        }

        seenIds.add(resourceId);
        return new Resource(resourceId, type, homeLocation, capacity, availabilityStatus);
    }

    // --- shared field-level checks -----------------------------------------

    private void requireFieldCount(String[] row, int expected, String entityName) throws ValidationException {
        if (row.length < expected) {
            throw new ValidationException("expected " + expected + " fields for " + entityName + " but found " + row.length);
        }
    }

    private String requireNonBlank(String value, String fieldName) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("missing required field '" + fieldName + "'");
        }
        return value.trim();
    }

    private int requireInt(String value, String fieldName) throws ValidationException {
        String trimmed = requireNonBlank(value, fieldName);
        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new ValidationException("field '" + fieldName + "' expected an integer but got '" + trimmed + "'");
        }
    }

    private double requireDouble(String value, String fieldName) throws ValidationException {
        String trimmed = requireNonBlank(value, fieldName);
        try {
            return Double.parseDouble(trimmed);
        } catch (NumberFormatException e) {
            throw new ValidationException("field '" + fieldName + "' expected a number but got '" + trimmed + "'");
        }
    }

    private void requireUnique(int id, Set<Integer> seenIds, String fieldName) throws ValidationException {
        if (seenIds.contains(id)) {
            throw new ValidationException("duplicate " + fieldName + " " + id);
        }
    }

    private void requireKnownLocation(int locationId, Set<Integer> validLocationIds, String fieldName)
            throws ValidationException {
        if (!validLocationIds.contains(locationId)) {
            throw new ValidationException(fieldName + " " + locationId + " does not reference a loaded location");
        }
    }
}
