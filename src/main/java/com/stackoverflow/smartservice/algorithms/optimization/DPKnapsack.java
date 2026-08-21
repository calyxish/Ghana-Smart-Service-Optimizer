package main.java.com.stackoverflow.smartservice.algorithms.optimization;

import main.java.com.stackoverflow.smartservice.structures.linear.DynamicArray;


public class DPKnapsack {

    public static class MaintenanceTask {
        private final String title;
        private final int timeHours;
        private final int urgencyValue;

        public MaintenanceTask(String title, int timeHours, int urgencyValue) {
            this.title = title;
            this.timeHours = timeHours;
            this.urgencyValue = urgencyValue;
        }

        public String getTitle() {
            return title;
        }

        public int getTimeHours() {
            return timeHours;
        }

        public int getUrgencyValue() {
            return urgencyValue;
        }
    }

    public static class KnapsackResult {
        private final int maxUrgency;
        private final DynamicArray<MaintenanceTask> selectedTasks;

        public KnapsackResult(int maxUrgency, DynamicArray<MaintenanceTask> selectedTasks) {
            this.maxUrgency = maxUrgency;
            this.selectedTasks = selectedTasks;
        }

        public int getMaxUrgency() {
            return maxUrgency;
        }

        public DynamicArray<MaintenanceTask> getSelectedTasks() {
            return selectedTasks;
        }
    }

    /**
     * Solves 0/1 Knapsack problem for UG Campus maintenance scheduling.
     * Dynamic Programming DP Table stored using Role 4 DynamicArray.
     *
     * @param tasks List of campus maintenance requests
     * @param timeBudget Maximum hours available for maintenance crew
     * @return KnapsackResult containing optimal urgency value and list of selected tasks
     */
    public KnapsackResult solveKnapsack(DynamicArray<MaintenanceTask> tasks, int timeBudget) {
        int n = tasks.size();

        // Row-based storage using custom DynamicArray for DP table tracking
        DynamicArray<int[]> dpTable = new DynamicArray<>(n + 1);
        for (int i = 0; i <= n; i++) {
            dpTable.add(new int[timeBudget + 1]);
        }

        // Build DP table bottom-up
        for (int i = 1; i <= n; i++) {
            MaintenanceTask task = tasks.get(i - 1);
            int weight = task.getTimeHours();
            int value = task.getUrgencyValue();

            int[] currentRow = dpTable.get(i);
            int[] previousRow = dpTable.get(i - 1);

            for (int w = 0; w <= timeBudget; w++) {
                if (weight <= w) {
                    currentRow[w] = Math.max(previousRow[w], previousRow[w - weight] + value);
                } else {
                    currentRow[w] = previousRow[w];
                }
            }
        }

        int maxUrgency = dpTable.get(n)[timeBudget];

        // Backtrack to extract optimal task combination
        DynamicArray<MaintenanceTask> selectedTasks = new DynamicArray<>();
        int w = timeBudget;
        for (int i = n; i > 0 && maxUrgency > 0; i--) {
            if (dpTable.get(i)[w] != dpTable.get(i - 1)[w]) {
                MaintenanceTask task = tasks.get(i - 1);
                selectedTasks.add(task);
                w -= task.getTimeHours();
            }
        }

        return new KnapsackResult(maxUrgency, selectedTasks);
    }

    public static void main(String[] args) {
        System.out.println("=== UG Campus Service Optimization: DP Knapsack ===");

        DynamicArray<MaintenanceTask> requests = new DynamicArray<>();
        requests.add(new MaintenanceTask("Request A: Fix projector at JQB", 2, 5));
        requests.add(new MaintenanceTask("Request B: Plumbing at Akuafo Hall", 3, 7));
        requests.add(new MaintenanceTask("Request C: Wi-Fi fix at UGCS", 4, 8));
        requests.add(new MaintenanceTask("Request D: Clean Great Hall", 1, 2));
        requests.add(new MaintenanceTask("Request E: Generator repair at ISH", 5, 9));

        int budget = 8; // 8 Hours budget

        DPKnapsack optimizer = new DPKnapsack();
        KnapsackResult result = optimizer.solveKnapsack(requests, budget);

        System.out.println("Time Budget: " + budget + " hours");
        System.out.println("Optimal Total Urgency Handled: " + result.getMaxUrgency());
        System.out.println("\nSelected Tasks:");

        DynamicArray<MaintenanceTask> selected = result.getSelectedTasks();
        int totalHoursUsed = 0;
        for (int i = 0; i < selected.size(); i++) {
            MaintenanceTask task = selected.get(i);
            System.out.println(" - " + task.getTitle() + " (Hours: " + task.getTimeHours() + ", Urgency: " + task.getUrgencyValue() + ")");
            totalHoursUsed += task.getTimeHours();
        }
        System.out.println("Total Hours Utilized: " + totalHoursUsed + " / " + budget + " hours\n");

        // Run Counterexample demonstration
        GreedyCounterexample.runCounterexampleDemo();
    }
}