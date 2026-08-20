package com.stackoverflow.smartservice.algorithms.graph;

import com.stackoverflow.smartservice.structures.graph.AdjacencyListGraph;

public class GraphRoutingDemo {

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
        System.out.println("=== Role 9: Graph Routing Algorithms Demo ===\n");

        AdjacencyListGraph graph = buildCampusGraph();

        demoBFS(graph);
        demoDFS(graph);
        demoDijkstra(graph);
        demoPrim(graph);
        demoKruskal(graph);
    }

    private static AdjacencyListGraph buildCampusGraph() {
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

    private static void demoBFS(AdjacencyListGraph graph) {
        System.out.println("--- BFS (Breadth-First Search) ---");
        System.out.println("Starting from Balme Library, visiting all reachable locations level by level:\n");

        int[] order = BFS.traverse(graph, BALME);
        System.out.print("Traversal order: ");
        for (int i = 0; i < order.length; i++) {
            System.out.print(NAMES[order[i]]);
            if (i < order.length - 1) System.out.print(" -> ");
        }
        System.out.println();

        System.out.println("\nReachability check:");
        System.out.println("  Balme -> Commonwealth: " + BFS.isReachable(graph, BALME, COMMONWEALTH));

        System.out.println("\nShortest path (by hops) from Akuafo Hall to UGCS:");
        int[] path = BFS.shortestPathUnweighted(graph, AKUAFO, UGCS);
        printPath(path);
        System.out.println();
    }

    private static void demoDFS(AdjacencyListGraph graph) {
        System.out.println("--- DFS (Depth-First Search) ---");
        System.out.println("Starting from Balme Library, exploring as deep as possible:\n");

        int[] order = DFS.traverse(graph, BALME);
        System.out.print("Recursive traversal: ");
        for (int i = 0; i < order.length; i++) {
            System.out.print(NAMES[order[i]]);
            if (i < order.length - 1) System.out.print(" -> ");
        }
        System.out.println();

        int[] iterOrder = DFS.traverseIterative(graph, BALME);
        System.out.print("Iterative traversal: ");
        for (int i = 0; i < iterOrder.length; i++) {
            System.out.print(NAMES[iterOrder[i]]);
            if (i < iterOrder.length - 1) System.out.print(" -> ");
        }
        System.out.println();

        System.out.println("\nCampus graph has cycle: " + DFS.hasCycle(graph));
        System.out.println();
    }

    private static void demoDijkstra(AdjacencyListGraph graph) {
        System.out.println("--- Dijkstra's Shortest Path ---");
        System.out.println("Finding shortest weighted path from Akuafo Hall to UGCS:\n");

        Dijkstra.ShortestPathResult result = Dijkstra.shortestPath(graph, AKUAFO);

        int[] path = result.getPathTo(UGCS);
        System.out.print("  Path: ");
        printPath(path);
        System.out.printf("  Total distance: %.2f km%n", result.getDistanceTo(UGCS));

        System.out.println("\nShortest distances from Akuafo Hall to all locations:");
        for (int i = 0; i < NAMES.length; i++) {
            double d = result.getDistanceTo(i);
            String dist = (d == Double.MAX_VALUE) ? "unreachable" : String.format("%.2f km", d);
            System.out.println("  -> " + NAMES[i] + ": " + dist);
        }

        System.out.println("\nShortest path from Commonwealth Hall to Pentagon:");
        path = Dijkstra.findPath(graph, COMMONWEALTH, PENTAGON);
        System.out.print("  Path: ");
        printPath(path);
        System.out.printf("  Distance: %.2f km%n", Dijkstra.findDistance(graph, COMMONWEALTH, PENTAGON));
        System.out.println();
    }

    private static void demoPrim(AdjacencyListGraph graph) {
        System.out.println("--- Prim's Minimum Spanning Tree ---");
        System.out.println("Building minimum campus connection network starting from Balme:\n");

        Prim.MSTResult result = Prim.minimumSpanningTree(graph, BALME);

        System.out.println("MST edges:");
        for (int i = 0; i < result.edgeCount; i++) {
            System.out.printf("  %s -- %s (%.2f km)%n",
                NAMES[result.edges[i][0]], NAMES[result.edges[i][1]], result.weights[i]);
        }
        System.out.printf("\nTotal MST weight: %.2f km%n", result.totalWeight);
        System.out.println("Edges in MST: " + result.edgeCount);
        System.out.println();
    }

    private static void demoKruskal(AdjacencyListGraph graph) {
        System.out.println("--- Kruskal's Minimum Spanning Tree ---");
        System.out.println("Building MST by adding cheapest edges first:\n");

        Kruskal.MSTResult result = Kruskal.minimumSpanningTree(graph);

        System.out.println("MST edges (sorted by weight):");
        for (int i = 0; i < result.edgeCount; i++) {
            System.out.printf("  %s -- %s (%.2f km)%n",
                NAMES[result.edges[i][0]], NAMES[result.edges[i][1]], result.weights[i]);
        }
        System.out.printf("\nTotal MST weight: %.2f km%n", result.totalWeight);
        System.out.println("Edges in MST: " + result.edgeCount);
    }

    private static void printPath(int[] path) {
        if (path.length == 0) {
            System.out.println("No path found");
            return;
        }
        for (int i = 0; i < path.length; i++) {
            System.out.print(NAMES[path[i]]);
            if (i < path.length - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
}
