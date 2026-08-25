package com.stackoverflow.smartservice.algorithms.graph;

import com.stackoverflow.smartservice.structures.graph.AdjacencyListGraph;

public class DFS {

    public static int[] traverse(AdjacencyListGraph graph, int startVertex) {
        int n = graph.getVertexCount();
        boolean[] visited = new boolean[n];
        int[] order = new int[n];
        int[] orderSize = {0};

        dfsRecursive(graph, startVertex, visited, order, orderSize);

        int[] result = new int[orderSize[0]];
        System.arraycopy(order, 0, result, 0, orderSize[0]);
        return result;
    }

    private static void dfsRecursive(AdjacencyListGraph graph, int vertex,
                                      boolean[] visited, int[] order, int[] orderSize) {
        visited[vertex] = true;
        order[orderSize[0]++] = vertex;

        int[] neighbors = graph.getNeighbors(vertex);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                dfsRecursive(graph, neighbor, visited, order, orderSize);
            }
        }
    }

    public static int[] traverseIterative(AdjacencyListGraph graph, int startVertex) {
        int n = graph.getVertexCount();
        boolean[] visited = new boolean[n];
        int[] order = new int[n];
        int orderSize = 0;

        int[] stack = new int[n];
        int top = 0;
        stack[top++] = startVertex;

        while (top > 0) {
            int current = stack[--top];

            if (visited[current]) continue;
            visited[current] = true;
            order[orderSize++] = current;

            int[] neighbors = graph.getNeighbors(current);
            for (int i = neighbors.length - 1; i >= 0; i--) {
                if (!visited[neighbors[i]]) {
                    stack[top++] = neighbors[i];
                }
            }
        }

        int[] result = new int[orderSize];
        System.arraycopy(order, 0, result, 0, orderSize);
        return result;
    }

    public static boolean hasCycle(AdjacencyListGraph graph) {
        int n = graph.getVertexCount();
        boolean[] visited = new boolean[n];

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                if (hasCycleDFS(graph, v, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasCycleDFS(AdjacencyListGraph graph, int vertex,
                                        int parent, boolean[] visited) {
        visited[vertex] = true;
        int[] neighbors = graph.getNeighbors(vertex);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                if (hasCycleDFS(graph, neighbor, vertex, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }
}
