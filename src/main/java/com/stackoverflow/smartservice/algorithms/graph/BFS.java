package com.stackoverflow.smartservice.algorithms.graph;

import com.stackoverflow.smartservice.structures.graph.AdjacencyListGraph;

public class BFS {

    public static int[] traverse(AdjacencyListGraph graph, int startVertex) {
        int n = graph.getVertexCount();
        boolean[] visited = new boolean[n];
        int[] order = new int[n];
        int orderSize = 0;

        int[] queue = new int[n];
        int front = 0, rear = 0;

        visited[startVertex] = true;
        queue[rear++] = startVertex;

        while (front < rear) {
            int current = queue[front++];
            order[orderSize++] = current;

            int[] neighbors = graph.getNeighbors(current);
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue[rear++] = neighbor;
                }
            }
        }

        int[] result = new int[orderSize];
        System.arraycopy(order, 0, result, 0, orderSize);
        return result;
    }

    public static int[] shortestPathUnweighted(AdjacencyListGraph graph, int source, int destination) {
        int n = graph.getVertexCount();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = -1;

        int[] queue = new int[n];
        int front = 0, rear = 0;

        visited[source] = true;
        queue[rear++] = source;

        while (front < rear) {
            int current = queue[front++];
            if (current == destination) break;

            int[] neighbors = graph.getNeighbors(current);
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    queue[rear++] = neighbor;
                }
            }
        }

        if (!visited[destination]) return new int[0];

        int pathLength = 0;
        int node = destination;
        while (node != -1) {
            pathLength++;
            node = parent[node];
        }

        int[] path = new int[pathLength];
        node = destination;
        for (int i = pathLength - 1; i >= 0; i--) {
            path[i] = node;
            node = parent[node];
        }
        return path;
    }

    public static boolean isReachable(AdjacencyListGraph graph, int source, int destination) {
        int n = graph.getVertexCount();
        boolean[] visited = new boolean[n];

        int[] queue = new int[n];
        int front = 0, rear = 0;

        visited[source] = true;
        queue[rear++] = source;

        while (front < rear) {
            int current = queue[front++];
            if (current == destination) return true;

            int[] neighbors = graph.getNeighbors(current);
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue[rear++] = neighbor;
                }
            }
        }
        return false;
    }
}
