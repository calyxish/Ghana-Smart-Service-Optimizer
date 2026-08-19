package com.stackoverflow.smartservice.data.loader;

import com.stackoverflow.smartservice.data.database.DatabaseConnection;
import com.stackoverflow.smartservice.data.database.LocationDAO;
import com.stackoverflow.smartservice.data.database.ResourceDAO;
import com.stackoverflow.smartservice.data.database.RoadDAO;
import com.stackoverflow.smartservice.data.database.ServiceRequestDAO;
import com.stackoverflow.smartservice.data.database.model.Location;
import com.stackoverflow.smartservice.data.database.model.Resource;
import com.stackoverflow.smartservice.data.database.model.Road;
import com.stackoverflow.smartservice.data.database.model.ServiceRequest;
import com.stackoverflow.smartservice.data.loader.CSVParser.CSVRecord;
import com.stackoverflow.smartservice.data.loader.DataValidator.ValidationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Main loader that ties CSVParser, DataValidator, and Robert's (Role 2) DAO
 * classes together:
 *
 * 1. Reads each CSV file from data/csv/ with {@link CSVParser}.
 * 2. Validates every record with {@link DataValidator}.
 * 3. Inserts valid records into the SQLite database via the DAO classes.
 * 4. Prints a per-file and overall summary of what was loaded vs. skipped.
 *
 * Load order matters: locations must be loaded first because roads,
 * service_requests, and resources all reference locationId via foreign
 * keys, and the validator checks that referenced IDs actually exist.
 */
public class DataLoader {

    private static final String CSV_DIR = "data/csv/";

    private final CSVParser parser = new CSVParser();
    private final DataValidator validator = new DataValidator();

    private final LocationDAO locationDAO = new LocationDAO();
    private final RoadDAO roadDAO = new RoadDAO();
    private final ServiceRequestDAO serviceRequestDAO = new ServiceRequestDAO();
    private final ResourceDAO resourceDAO = new ResourceDAO();

    /** Result of loading one CSV file: how many rows were valid vs. rejected. */
    private record LoadSummary(String entityName, int total, int loaded) {
        int skipped() { return total - loaded; }
    }

    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        try {
            loader.run();
        } catch (IOException | SQLException e) {
            System.err.println("FATAL: data load could not proceed: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void run() throws IOException, SQLException {
        System.out.println("Initializing database schema...");
        DatabaseConnection.initializeSchema();

        Set<Integer> locationIds = new HashSet<>();

        LoadSummary locations = loadLocations(locationIds);
        LoadSummary roads = loadRoads(locationIds);
        LoadSummary requests = loadServiceRequests(locationIds);
        LoadSummary resources = loadResources(locationIds);

        printSummary(locations, roads, requests, resources);
    }

    private LoadSummary loadLocations(Set<Integer> locationIds) throws IOException {
        List<CSVRecord> rows = parser.parseFile(CSV_DIR + "locations.csv");
        int total = 0;
        int loaded = 0;

        for (int i = 1; i < rows.size(); i++) { // skip header at index 0
            CSVRecord record = rows.get(i);
            total++;
            try {
                Location location = validator.validateLocation(record.getFields(), locationIds);
                locationDAO.insert(location);
                loaded++;
            } catch (ValidationException | SQLException e) {
                logInvalid("locations.csv", record.getLineNumber(), e.getMessage());
            }
        }
        return new LoadSummary("locations", total, loaded);
    }

    private LoadSummary loadRoads(Set<Integer> locationIds) throws IOException {
        List<CSVRecord> rows = parser.parseFile(CSV_DIR + "roads.csv");
        int total = 0;
        int loaded = 0;

        for (int i = 1; i < rows.size(); i++) {
            CSVRecord record = rows.get(i);
            total++;
            try {
                Road road = validator.validateRoad(record.getFields(), locationIds);
                roadDAO.insert(road);
                loaded++;
            } catch (ValidationException | SQLException e) {
                logInvalid("roads.csv", record.getLineNumber(), e.getMessage());
            }
        }
        return new LoadSummary("roads", total, loaded);
    }

    private LoadSummary loadServiceRequests(Set<Integer> locationIds) throws IOException {
        List<CSVRecord> rows = parser.parseFile(CSV_DIR + "service_requests.csv");
        int total = 0;
        int loaded = 0;
        Set<Integer> seenRequestIds = new HashSet<>();

        for (int i = 1; i < rows.size(); i++) {
            CSVRecord record = rows.get(i);
            total++;
            try {
                ServiceRequest request = validator.validateServiceRequest(record.getFields(), seenRequestIds, locationIds);
                serviceRequestDAO.insert(request);
                loaded++;
            } catch (ValidationException | SQLException e) {
                logInvalid("service_requests.csv", record.getLineNumber(), e.getMessage());
            }
        }
        return new LoadSummary("service_requests", total, loaded);
    }

    private LoadSummary loadResources(Set<Integer> locationIds) throws IOException {
        List<CSVRecord> rows = parser.parseFile(CSV_DIR + "resources.csv");
        int total = 0;
        int loaded = 0;
        Set<Integer> seenResourceIds = new HashSet<>();

        for (int i = 1; i < rows.size(); i++) {
            CSVRecord record = rows.get(i);
            total++;
            try {
                Resource resource = validator.validateResource(record.getFields(), seenResourceIds, locationIds);
                resourceDAO.insert(resource);
                loaded++;
            } catch (ValidationException | SQLException e) {
                logInvalid("resources.csv", record.getLineNumber(), e.getMessage());
            }
        }
        return new LoadSummary("resources", total, loaded);
    }

    private void logInvalid(String fileName, int lineNumber, String reason) {
        System.out.println("  [INVALID] " + fileName + ":" + lineNumber + " - " + reason);
    }

    private void printSummary(LoadSummary... summaries) {
        System.out.println();
        System.out.println("=== Load Summary ===");
        int totalRecords = 0;
        int totalLoaded = 0;
        for (LoadSummary s : summaries) {
            System.out.printf("Loaded %d/%d %s (%d invalid record%s skipped)%n",
                    s.loaded(), s.total(), s.entityName(), s.skipped(), s.skipped() == 1 ? "" : "s");
            totalRecords += s.total();
            totalLoaded += s.loaded();
        }
        System.out.printf("TOTAL: %d/%d records loaded (%d skipped)%n",
                totalLoaded, totalRecords, totalRecords - totalLoaded);
    }
}
