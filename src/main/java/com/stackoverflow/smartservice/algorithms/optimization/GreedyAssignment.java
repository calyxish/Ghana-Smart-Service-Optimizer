package main.java.com.stackoverflow.smartservice.algorithms.optimization;

import main.java.com.stackoverflow.smartservice.structures.graph.AdjacencyListGraph;
import main.java.com.stackoverflow.smartservice.structures.linear.DynamicArray;

public class GreedyAssignment {

    public static class MaintenanceCrew {
        private final int id;
        private final String name;
        private int currentLocationVertex;

        public MaintenanceCrew(int id, String name, int currentLocationVertex) {
            this.id = id;
            this.name = name;
            this.currentLocationVertex = currentLocationVertex;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCurrentLocationVertex() {
            return currentLocationVertex;
        }

        public void setCurrentLocationVertex(int currentLocationVertex) {
            this.currentLocationVertex = currentLocationVertex;
        }
    }

    public static class ServiceRequest {
        private final int requestId;
        private final String description;
        private final int locationVertex;

        public ServiceRequest(int requestId, String description, int locationVertex) {
            this.requestId = requestId;
            this.description = description;
            this.locationVertex = locationVertex;
        }

        public int getRequestId() {
            return requestId;
        }

        public String getDescription() {
            return description;
        }

        public int getLocationVertex() {
            return locationVertex;
        }
    }

    public static class AssignmentResult {
        private final ServiceRequest request;
        private final MaintenanceCrew crew;
        private final double travelDistance;

        public AssignmentResult(ServiceRequest request, MaintenanceCrew crew, double travelDistance) {
            this.request = request;
            this.crew = crew;
            this.travelDistance = travelDistance;
        }

        public ServiceRequest getRequest() {
            return request;
        }

        public MaintenanceCrew getCrew() {
            return crew;
        }

        public double getTravelDistance() {
            return travelDistance;
        }
    }

    /**
     * Greedily assigns available crews to pending requests by finding the minimum direct graph edge weight.
     * Uses Role 4 DynamicArray and Role 7 AdjacencyListGraph.
     */
    public DynamicArray<AssignmentResult> assignCrewsNearestFirst(
            AdjacencyListGraph campusGraph,
            DynamicArray<ServiceRequest> requests,
            DynamicArray<MaintenanceCrew> crews) {

        DynamicArray<AssignmentResult> assignments = new DynamicArray<>();
        boolean[] crewAssigned = new boolean[crews.size()];

        for (int i = 0; i < requests.size(); i++) {
            ServiceRequest req = requests.get(i);
            int reqLocation = req.getLocationVertex();

            int bestCrewIndex = -1;
            double minDistance = Double.POSITIVE_INFINITY;

            for (int j = 0; j < crews.size(); j++) {
                if (crewAssigned[j]) {
                    continue;
                }

                MaintenanceCrew crew = crews.get(j);
                int crewLocation = crew.getCurrentLocationVertex();

                double distance;
                if (crewLocation == reqLocation) {
                    distance = 0.0;
                } else if (campusGraph.hasEdge(crewLocation, reqLocation)) {
                    distance = campusGraph.getEdgeWeight(crewLocation, reqLocation);
                } else {
                    distance = Double.POSITIVE_INFINITY;
                }

                if (distance < minDistance) {
                    minDistance = distance;
                    bestCrewIndex = j;
                }
            }

            if (bestCrewIndex != -1 && minDistance != Double.POSITIVE_INFINITY) {
                crewAssigned[bestCrewIndex] = true;
                MaintenanceCrew assignedCrew = crews.get(bestCrewIndex);
                assignments.add(new AssignmentResult(req, assignedCrew, minDistance));
            }
        }

        return assignments;
    }
}