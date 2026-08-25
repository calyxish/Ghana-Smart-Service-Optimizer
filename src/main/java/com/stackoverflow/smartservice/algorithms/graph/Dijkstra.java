package com.stackoverflow.smartservice.algorithms.graph;

import com.stackoverflow.smartservice.structures.graph.AdjacencyListGraph;

public class Dijkstra {

    public static class ShortestPathResult {
        public final double[] distances;
        public final int[] predecessors;
        public final int source;

        ShortestPathResult(double[] distances, int[] predecessors, int source) {
            this.distances = distances;
            this.predecessors = predecessors;
            this.source = source;
        }

        public int[] getPathTo(int destination) {
            if (distances[destination] == Double.MAX_VALUE) {
                return new int[0];
            }

            int pathLength = 0;
            int node = destination;
            while (node != -1) {
                pathLength++;
                node = predecessors[node];
            }

            int[] path = new int[pathLength];
            node = destination;
            for (int i = pathLength - 1; i >= 0; i--) {
                path[i] = node;
                node = predecessors[node];
            }
            return path;
        }

        public double getDistanceTo(int destination) {
            return distances[destination];
        }
    }

    public static ShortestPathResult shortestPath(AdjacencyListGraph graph, int source) {
        int n = graph.getVertexCount();
        double[] dist = new double[n];
        int[] pred = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Double.MAX_VALUE;
            pred[i] = -1;
        }
        dist[source] = 0;

        for (int count = 0; count < n; count++) {
            int u = minDistanceVertex(dist, visited, n);
            if (u == -1 || dist[u] == Double.MAX_VALUE) break;

            visited[u] = true;

            int[] neighbors = graph.getNeighbors(u);
            for (int v : neighbors) {
                if (!visited[v]) {
                    double edgeWeight = graph.getEdgeWeight(u, v);
                    double newDist = dist[u] + edgeWeight;
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pred[v] = u;
                    }
                }
            }
        }

        return new ShortestPathResult(dist, pred, source);
    }

    public static int[] findPath(AdjacencyListGraph graph, int source, int destination) {
        return shortestPath(graph, source).getPathTo(destination);
    }

    public static double findDistance(AdjacencyListGraph graph, int source, int destination) {
        return shortestPath(graph, source).getDistanceTo(destination);
    }

    private static int minDistanceVertex(double[] dist, boolean[] visited, int n) {
        double min = Double.MAX_VALUE;
        int minVertex = -1;
        for (int v = 0; v < n; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minVertex = v;
            }
        }
        return minVertex;
    }
}
