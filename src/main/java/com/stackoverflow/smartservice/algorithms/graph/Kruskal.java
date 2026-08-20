package com.stackoverflow.smartservice.algorithms.graph;

import com.stackoverflow.smartservice.structures.graph.AdjacencyListGraph;
import com.stackoverflow.smartservice.structures.graph.DisjointSet;

public class Kruskal {

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

    public static MSTResult minimumSpanningTree(AdjacencyListGraph graph) {
        int n = graph.getVertexCount();
        int[][] allEdges = graph.getAllEdges();
        double[] allWeights = graph.getAllEdgeWeights();
        int edgeTotal = allEdges.length;

        sortEdgesByWeight(allEdges, allWeights, edgeTotal);

        DisjointSet ds = new DisjointSet(n);
        int[][] mstEdges = new int[n - 1][2];
        double[] mstWeights = new double[n - 1];
        int mstCount = 0;
        double totalWeight = 0;

        for (int i = 0; i < edgeTotal && mstCount < n - 1; i++) {
            int u = allEdges[i][0];
            int v = allEdges[i][1];
            double w = allWeights[i];

            if (!ds.connected(u, v)) {
                ds.union(u, v);
                mstEdges[mstCount][0] = u;
                mstEdges[mstCount][1] = v;
                mstWeights[mstCount] = w;
                totalWeight += w;
                mstCount++;
            }
        }

        return new MSTResult(mstEdges, mstWeights, mstCount, totalWeight);
    }

    private static void sortEdgesByWeight(int[][] edges, double[] weights, int n) {
        for (int i = 1; i < n; i++) {
            double keyW = weights[i];
            int[] keyE = edges[i];
            int j = i - 1;
            while (j >= 0 && weights[j] > keyW) {
                weights[j + 1] = weights[j];
                edges[j + 1] = edges[j];
                j--;
            }
            weights[j + 1] = keyW;
            edges[j + 1] = keyE;
        }
    }
}
