package com.stackoverflow.smartservice.algorithms.graph;

import com.stackoverflow.smartservice.structures.graph.AdjacencyListGraph;

public class Prim {

    public static class MSTResult {
        public final int[][] edges;
        public final double[] weights;
        public final int edgeCount;
        public final double totalWeight;

        MSTResult(int[][] edges, double[] weights, int edgeCount, double totalWeight) {
            this.edges = edges;
            this.weights = weights;
            this.edgeCount = edgeCount;
            this.totalWeight = totalWeight;
        }
    }

    public static MSTResult minimumSpanningTree(AdjacencyListGraph graph, int startVertex) {
        int n = graph.getVertexCount();
        boolean[] inMST = new boolean[n];
        double[] key = new double[n];
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            key[i] = Double.MAX_VALUE;
            parent[i] = -1;
        }
        key[startVertex] = 0;

        int[][] mstEdges = new int[n - 1][2];
        double[] mstWeights = new double[n - 1];
        int mstCount = 0;
        double totalWeight = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            double minKey = Double.MAX_VALUE;
            for (int v = 0; v < n; v++) {
                if (!inMST[v] && key[v] < minKey) {
                    minKey = key[v];
                    u = v;
                }
            }

            if (u == -1) break;
            inMST[u] = true;

            if (parent[u] != -1) {
                mstEdges[mstCount][0] = parent[u];
                mstEdges[mstCount][1] = u;
                mstWeights[mstCount] = key[u];
                totalWeight += key[u];
                mstCount++;
            }

            int[] neighbors = graph.getNeighbors(u);
            for (int v : neighbors) {
                if (!inMST[v]) {
                    double edgeWeight = graph.getEdgeWeight(u, v);
                    if (edgeWeight < key[v]) {
                        key[v] = edgeWeight;
                        parent[v] = u;
                    }
                }
            }
        }

        return new MSTResult(mstEdges, mstWeights, mstCount, totalWeight);
    }
}
