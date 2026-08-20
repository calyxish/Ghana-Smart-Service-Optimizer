package com.stackoverflow.smartservice.app;

import java.util.Scanner;

import com.stackoverflow.smartservice.data.loader.DataLoader;
import com.stackoverflow.smartservice.structures.linear.*;
import com.stackoverflow.smartservice.structures.tree.*;
import com.stackoverflow.smartservice.structures.hashing.*;
import com.stackoverflow.smartservice.structures.graph.*;
import com.stackoverflow.smartservice.algorithms.graph.*;

public class ConsoleMenu {

    private final Scanner scanner;
    private boolean dataLoaded = false;

    public ConsoleMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        printBanner();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> loadData();
                case 2 -> dataStructuresMenu();
                case 3 -> algorithmsMenu();
                case 4 -> campusNavigationMenu();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Try again.\n");
            }
        }

        System.out.println("\nThank you for using the Ghana Smart Service Optimizer!");
        System.out.println("Stack Overflow Team - DCIT 204/308 DSA Project");
    }

    private void printBanner() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     GHANA SMART SERVICE OPTIMIZER                       ║");
        System.out.println("║     University of Ghana (Legon) Campus                  ║");
        System.out.println("║     Stack Overflow Team - DCIT 204/308                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private void printMainMenu() {
        System.out.println("========== MAIN MENU ==========");
        System.out.println("  1. Load Campus Data (CSV -> SQLite)");
        System.out.println("  2. Data Structures Demos");
        System.out.println("  3. Algorithm Demos");
        System.out.println("  4. Campus Navigation (Interactive)");
        System.out.println("  0. Exit");
        System.out.println("================================");
    }

    // ---------------------------------------------------------------
    //  1. Data Loading
    // ---------------------------------------------------------------

    private void loadData() {
        if (dataLoaded) {
            System.out.println("\nData has already been loaded this session.");
            System.out.print("Reload? (y/n): ");
            String answer = scanner.nextLine().trim();
            if (!answer.equalsIgnoreCase("y")) {
                System.out.println();
                return;
            }
        }

        System.out.println("\nLoading campus data from CSV files into SQLite...\n");
        try {
            DataLoader loader = new DataLoader();
            loader.run();
            dataLoaded = true;
            System.out.println("\nData loaded successfully.\n");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            System.out.println("Make sure the data/csv/ directory contains the CSV files.\n");
        }
    }

    // ---------------------------------------------------------------
    //  2. Data Structures Demos
    // ---------------------------------------------------------------

    private void dataStructuresMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== DATA STRUCTURES DEMOS =====");
            System.out.println("  1. Linear Structures (DynamicArray, LinkedList, Stack, Queue, Deque)");
            System.out.println("  2. Tree Structures (BST, Red-Black Tree, B-Tree)");
            System.out.println("  3. Hash & Priority Structures (HashTable, HashMap, HashSet, Heaps, PriorityQueue)");
            System.out.println("  4. Graph Structures (Adjacency List, Adjacency Matrix, Disjoint Set)");
            System.out.println("  5. Run ALL Structure Demos");
            System.out.println("  0. Back to Main Menu");
            System.out.println("=================================");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> runLinearDemo();
                case 2 -> runTreeDemo();
                case 3 -> runHashingDemo();
                case 4 -> runGraphStructuresDemo();
                case 5 -> { runLinearDemo(); runTreeDemo(); runHashingDemo(); runGraphStructuresDemo(); }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void runLinearDemo() {
        System.out.println("\n" + separator("LINEAR STRUCTURES DEMO (Role 4)"));
        LinearDemo.main(new String[0]);
        pauseForUser();
    }

    private void runTreeDemo() {
        System.out.println("\n" + separator("TREE STRUCTURES DEMO (Role 5)"));
        TreeDemo.main(new String[0]);
        pauseForUser();
    }

    private void runHashingDemo() {
        System.out.println("\n" + separator("HASH & PRIORITY STRUCTURES DEMO (Role 6)"));
        HashingDemo.main(new String[0]);
        pauseForUser();
    }

    private void runGraphStructuresDemo() {
        System.out.println("\n" + separator("GRAPH STRUCTURES DEMO (Role 7)"));
        GraphDemo.main(new String[0]);
        pauseForUser();
    }

    // ---------------------------------------------------------------
    //  3. Algorithm Demos
    // ---------------------------------------------------------------

    private void algorithmsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== ALGORITHM DEMOS =====");
            System.out.println("  1. Graph Routing (BFS, DFS, Dijkstra, Prim, Kruskal)");
            System.out.println("  2. Search & Sort Algorithms  [Not yet implemented - Role 8]");
            System.out.println("  3. Scheduling Algorithms      [Not yet implemented - Role 10]");
            System.out.println("  4. Optimization Algorithms    [Not yet implemented - Role 11]");
            System.out.println("  0. Back to Main Menu");
            System.out.println("===========================");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> runGraphRoutingDemo();
                case 2 -> notImplemented("Search & Sort Algorithms", 8, "Renny Darko & Hamidu Abdul-Mumin");
                case 3 -> notImplemented("Scheduling Algorithms", 10, "Kennedy Frimpong");
                case 4 -> notImplemented("Optimization Algorithms", 11, "Hator Faith");
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void runGraphRoutingDemo() {
        System.out.println("\n" + separator("GRAPH ROUTING DEMO (Role 9)"));
        GraphRoutingDemo.main(new String[0]);
        pauseForUser();
    }

    // ---------------------------------------------------------------
    //  4. Campus Navigation (Interactive)
    // ---------------------------------------------------------------

    private static final int BALME = 0;
    private static final int JQB = 1;
    private static final int UGCS = 2;
    private static final int NBLOCK = 3;
    private static final int AKUAFO = 4;
    private static final int LEGON = 5;
    private static final int VOLTA = 6;
    private static final int COMMONWEALTH = 7;
    private static final int PENTAGON = 8;
    private static final int ISH = 9;

    private static final String[] LOCATION_NAMES = {
        "Balme Library", "JQB", "UGCS", "N-Block", "Akuafo Hall",
        "Legon Hall", "Volta Hall", "Commonwealth Hall", "Pentagon", "ISH"
    };

    private AdjacencyListGraph buildCampusGraph() {
        AdjacencyListGraph graph = new AdjacencyListGraph(10);
        graph.addEdge(BALME, JQB, 0.3);
        graph.addEdge(BALME, UGCS, 0.5);
        graph.addEdge(JQB, NBLOCK, 0.2);
        graph.addEdge(JQB, UGCS, 0.4);
        graph.addEdge(NBLOCK, AKUAFO, 0.6);
        graph.addEdge(AKUAFO, LEGON, 0.8);
        graph.addEdge(LEGON, VOLTA, 0.5);
        graph.addEdge(VOLTA, COMMONWEALTH, 0.7);
        graph.addEdge(PENTAGON, ISH, 0.4);
        graph.addEdge(PENTAGON, UGCS, 0.6);
        graph.addEdge(ISH, BALME, 0.9);
        return graph;
    }

    private void campusNavigationMenu() {
        AdjacencyListGraph graph = buildCampusGraph();

        boolean back = false;
        while (!back) {
            System.out.println("\n===== CAMPUS NAVIGATION =====");
            System.out.println("  1. Find shortest path between two locations");
            System.out.println("  2. Check if two locations are connected");
            System.out.println("  3. List all campus locations");
            System.out.println("  4. Find neighbors of a location");
            System.out.println("  5. Build minimum spanning tree (cheapest network)");
            System.out.println("  0. Back to Main Menu");
            System.out.println("=============================");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> findShortestPath(graph);
                case 2 -> checkConnectivity(graph);
                case 3 -> listLocations();
                case 4 -> findNeighbors(graph);
                case 5 -> buildMST(graph);
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void printLocationList() {
        System.out.println("  Campus Locations:");
        for (int i = 0; i < LOCATION_NAMES.length; i++) {
            System.out.printf("    %2d. %s%n", i, LOCATION_NAMES[i]);
        }
    }

    private void findShortestPath(AdjacencyListGraph graph) {
        System.out.println("\n--- Shortest Path Finder ---");
        printLocationList();

        int src = readInt("Enter source location number: ");
        int dst = readInt("Enter destination location number: ");

        if (!validLocation(src) || !validLocation(dst)) {
            System.out.println("Invalid location number.\n");
            return;
        }

        if (src == dst) {
            System.out.println("Source and destination are the same location.\n");
            return;
        }

        int[] path = Dijkstra.findPath(graph, src, dst);
        double distance = Dijkstra.findDistance(graph, src, dst);

        if (path.length == 0) {
            System.out.println("No path found between " + LOCATION_NAMES[src] +
                    " and " + LOCATION_NAMES[dst] + ".\n");
            return;
        }

        System.out.print("\nShortest path: ");
        for (int i = 0; i < path.length; i++) {
            System.out.print(LOCATION_NAMES[path[i]]);
            if (i < path.length - 1) System.out.print(" -> ");
        }
        System.out.printf("%nTotal distance: %.2f km%n%n", distance);
    }

    private void checkConnectivity(AdjacencyListGraph graph) {
        System.out.println("\n--- Connectivity Check ---");
        printLocationList();

        int src = readInt("Enter first location number: ");
        int dst = readInt("Enter second location number: ");

        if (!validLocation(src) || !validLocation(dst)) {
            System.out.println("Invalid location number.\n");
            return;
        }

        boolean reachable = BFS.isReachable(graph, src, dst);
        System.out.println(LOCATION_NAMES[src] + " -> " + LOCATION_NAMES[dst] +
                ": " + (reachable ? "CONNECTED" : "NOT CONNECTED") + "\n");
    }

    private void listLocations() {
        System.out.println("\n--- UG Campus Locations ---");
        printLocationList();
        System.out.println();
    }

    private void findNeighbors(AdjacencyListGraph graph) {
        System.out.println("\n--- Find Neighbors ---");
        printLocationList();

        int loc = readInt("Enter location number: ");
        if (!validLocation(loc)) {
            System.out.println("Invalid location number.\n");
            return;
        }

        int[] neighbors = graph.getNeighbors(loc);
        if (neighbors.length == 0) {
            System.out.println(LOCATION_NAMES[loc] + " has no direct connections.\n");
            return;
        }

        System.out.println(LOCATION_NAMES[loc] + " connects to:");
        for (int n : neighbors) {
            System.out.printf("  -> %s (%.2f km)%n", LOCATION_NAMES[n], graph.getEdgeWeight(loc, n));
        }
        System.out.println();
    }

    private void buildMST(AdjacencyListGraph graph) {
        System.out.println("\n--- Minimum Spanning Tree (Kruskal) ---");
        System.out.println("Building cheapest network to connect all campus locations:\n");

        Kruskal.MSTResult result = Kruskal.minimumSpanningTree(graph);

        System.out.println("MST edges:");
        for (int i = 0; i < result.edgeCount; i++) {
            System.out.printf("  %s -- %s (%.2f km)%n",
                    LOCATION_NAMES[result.edges[i][0]],
                    LOCATION_NAMES[result.edges[i][1]],
                    result.weights[i]);
        }
        System.out.printf("%nTotal network distance: %.2f km%n", result.totalWeight);
        System.out.println("Edges in MST: " + result.edgeCount + "\n");
    }

    private boolean validLocation(int index) {
        return index >= 0 && index < LOCATION_NAMES.length;
    }

    // ---------------------------------------------------------------
    //  Utilities
    // ---------------------------------------------------------------

    private void notImplemented(String featureName, int roleNumber, String assignee) {
        System.out.println("\n[NOT YET IMPLEMENTED]");
        System.out.println("  Feature:  " + featureName);
        System.out.println("  Role:     " + roleNumber);
        System.out.println("  Assigned: " + assignee);
        System.out.println("  Status:   Awaiting implementation\n");
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void pauseForUser() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
        System.out.println();
    }

    private String separator(String title) {
        return "========== " + title + " ==========";
    }
}
