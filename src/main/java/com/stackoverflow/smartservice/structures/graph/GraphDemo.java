package com.stackoverflow.smartservice.structures.graph;

public class GraphDemo {

    // Vertex indices for UG campus locations
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

    private static final String[] NAMES = {
        "Balme Library", "JQB", "UGCS", "N-Block", "Akuafo Hall",
        "Legon Hall", "Volta Hall", "Commonwealth Hall", "Pentagon", "ISH"
    };

    public static void main(String[] args) {
        System.out.println("=== UG Campus Smart Service Optimizer ===");
        System.out.println("=== Role 7: Graph Foundations Demo ===\n");

        demoAdjacencyList();
        demoAdjacencyMatrix();
        demoDisjointSet();
    }

    private static void demoAdjacencyList() {
        System.out.println("--- Adjacency List Graph Demo ---");
        System.out.println("Building UG campus road network (10 locations):\n");

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

        System.out.println("Vertices: " + graph.getVertexCount());
        System.out.println("Edges: " + graph.getEdgeCount());
        System.out.println();

        for (int v = 0; v < 10; v++) {
            int[] neighbors = graph.getNeighbors(v);
            if (neighbors.length > 0) {
                System.out.print(NAMES[v] + " connects to: ");
                for (int i = 0; i < neighbors.length; i++) {
                    double w = graph.getEdgeWeight(v, neighbors[i]);
                    System.out.print(NAMES[neighbors[i]] + " (" + w + " km)");
                    if (i < neighbors.length - 1) System.out.print(", ");
                }
                System.out.println();
            }
        }

        System.out.println("\nBalme -> JQB edge exists: " + graph.hasEdge(BALME, JQB));
        System.out.println("Balme -> Akuafo direct edge: " + graph.hasEdge(BALME, AKUAFO));
        System.out.println("JQB degree: " + graph.getDegree(JQB));
        System.out.println();
    }

    private static void demoAdjacencyMatrix() {
        System.out.println("--- Adjacency Matrix Graph Demo ---");
        System.out.println("Same campus network using a 10x10 matrix:\n");

        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(10);

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

        System.out.println("Vertices: " + graph.getVertexCount());
        System.out.println("Edges: " + graph.getEdgeCount());
        System.out.println();

        System.out.println("Distance Balme -> JQB: " + graph.getEdgeWeight(BALME, JQB) + " km");
        System.out.println("Distance Akuafo -> Legon: " + graph.getEdgeWeight(AKUAFO, LEGON) + " km");
        System.out.println("Distance Balme -> Akuafo (no direct road): " +
            (graph.hasEdge(BALME, AKUAFO) ? graph.getEdgeWeight(BALME, AKUAFO) + " km" : "no direct connection"));

        System.out.println("\nNeighbors of JQB:");
        int[] neighbors = graph.getNeighbors(JQB);
        for (int n : neighbors) {
            System.out.println("  " + NAMES[n] + " (" + graph.getEdgeWeight(JQB, n) + " km)");
        }
        System.out.println();
    }

    private static void demoDisjointSet() {
        System.out.println("--- Disjoint Set Demo ---");
        System.out.println("Checking campus zone connectivity:\n");

        DisjointSet ds = new DisjointSet(10);
        System.out.println("Initial components: " + ds.getComponentCount());

        System.out.println("\nConnecting campus locations via roads:");
        connectAndPrint(ds, BALME, JQB);
        connectAndPrint(ds, JQB, NBLOCK);
        connectAndPrint(ds, JQB, UGCS);
        connectAndPrint(ds, NBLOCK, AKUAFO);
        connectAndPrint(ds, AKUAFO, LEGON);
        connectAndPrint(ds, LEGON, VOLTA);
        connectAndPrint(ds, VOLTA, COMMONWEALTH);
        connectAndPrint(ds, PENTAGON, ISH);

        System.out.println("\nComponents after connections: " + ds.getComponentCount());

        System.out.println("\nConnectivity checks:");
        System.out.println("  Balme <-> Commonwealth: " + ds.connected(BALME, COMMONWEALTH) + " (same zone)");
        System.out.println("  Balme <-> Pentagon: " + ds.connected(BALME, PENTAGON) + " (different zones)");
        System.out.println("  Pentagon <-> ISH: " + ds.connected(PENTAGON, ISH) + " (same zone)");

        System.out.println("\nConnecting Pentagon zone to main campus:");
        connectAndPrint(ds, PENTAGON, UGCS);
        System.out.println("  Balme <-> Pentagon now: " + ds.connected(BALME, PENTAGON));
        System.out.println("  Final components: " + ds.getComponentCount());
    }

    private static void connectAndPrint(DisjointSet ds, int a, int b) {
        boolean merged = ds.union(a, b);
        System.out.println("  union(" + NAMES[a] + ", " + NAMES[b] + ") -> " +
            (merged ? "merged" : "already connected"));
    }
}
