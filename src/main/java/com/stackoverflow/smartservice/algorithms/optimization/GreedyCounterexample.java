package main.java.com.stackoverflow.smartservice.algorithms.optimization;



import main.java.com.stackoverflow.smartservice.structures.linear.DynamicArray;


public class GreedyCounterexample {

    public static class ScheduleRequest {
        private final String name;
        private final int travelTimeMinutes;
        private final int availableAfterMinutes;
        private final int urgencyValue;

        public ScheduleRequest(String name, int travelTimeMinutes, int availableAfterMinutes, int urgencyValue) {
            this.name = name;
            this.travelTimeMinutes = travelTimeMinutes;
            this.availableAfterMinutes = availableAfterMinutes;
            this.urgencyValue = urgencyValue;
        }

        public String getName() {
            return name;
        }

        public int getTravelTimeMinutes() {
            return travelTimeMinutes;
        }

        public int getAvailableAfterMinutes() {
            return availableAfterMinutes;
        }

        public int getUrgencyValue() {
            return urgencyValue;
        }
    }

    public static void runCounterexampleDemo() {
        System.out.println("=== Greedy Failure Counterexample Demo ===");
        System.out.println("Scenario: Selecting campus requests to dispatch within a 30-minute operational window.\n");

        DynamicArray<ScheduleRequest> requests = new DynamicArray<>();
        
        // Immediate option: Near crew available now, but high travel time relative to urgency ratio
        ScheduleRequest reqA = new ScheduleRequest("Balme Library Network Fix", 20, 0, 10);
        
        // Delayed option: Crew busy for 10 min at JQB, but highly efficient once available
        ScheduleRequest reqB = new ScheduleRequest("JQB Lab Maintenance", 10, 10, 25);
        ScheduleRequest reqC = new ScheduleRequest("UGCS Server Check", 10, 10, 20);

        requests.add(reqA);
        requests.add(reqB);
        requests.add(reqC);

        System.out.println("Available Requests:");
        for (int i = 0; i < requests.size(); i++) {
            ScheduleRequest r = requests.get(i);
            System.out.println(" - " + r.getName() + " | Travel: " + r.getTravelTimeMinutes() 
                + "m | Wait: " + r.getAvailableAfterMinutes() + "m | Urgency: " + r.getUrgencyValue());
        }

        // Greedy approach: Immediately picks available job with highest current value
        int greedyUrgency = reqA.getUrgencyValue(); // Takes 20 mins total. Total urgency = 10.
        
        // Optimal approach: Wait 10 mins for JQB/UGCS crew, complete both within remaining 20 mins
        int optimalUrgency = reqB.getUrgencyValue() + reqC.getUrgencyValue(); // Wait 10m + 10m + 10m = 30m total. Urgency = 45.

        System.out.println("\n--- Comparison Results ---");
        System.out.println("Greedy Choice (Pick immediate job): Total Urgency = " + greedyUrgency);
        System.out.println("Optimal Choice (Wait 10 mins for JQB/UGCS crew): Total Urgency = " + optimalUrgency);
        System.out.println("Conclusion: Pure greedy selection fails to consider waiting time efficiencies for overall utility!");
    }
}