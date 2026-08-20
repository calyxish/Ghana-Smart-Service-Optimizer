package com.stackoverflow.smartservice.structures.graph;

public class AdjacencyMatrixGraph {

    private final double[][] matrix;
    private final int vertexCount;
    private int edgeCount;
    private final boolean directed;
    private static final double NO_EDGE = Double.POSITIVE_INFINITY;

    public AdjacencyMatrixGraph(int vertexCount, boolean directed) {
        this.vertexCount = vertexCount;
        this.directed = directed;
        this.edgeCount = 0;
        this.matrix = new double[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                matrix[i][j] = (i == j) ? 0 : NO_EDGE;
            }
        }
    }

    public AdjacencyMatrixGraph(int vertexCount) {
        this(vertexCount, false);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public boolean isDirected() {
        return directed;
    }

    public void addEdge(int from, int to, double weight) {
        validateVertex(from);
        validateVertex(to);
        if (matrix[from][to] == NO_EDGE) {
            edgeCount++;
        }
        matrix[from][to] = weight;
        if (!directed) {
            matrix[to][from] = weight;
        }
    }

    public void addEdge(int from, int to) {
        addEdge(from, to, 1.0);
    }

    public boolean hasEdge(int from, int to) {
        validateVertex(from);
        validateVertex(to);
        return matrix[from][to] != NO_EDGE && from != to;
    }

    public double getEdgeWeight(int from, int to) {
        validateVertex(from);
        validateVertex(to);
        return matrix[from][to];
    }

    public int[] getNeighbors(int vertex) {
        validateVertex(vertex);
        int count = 0;
        for (int j = 0; j < vertexCount; j++) {
            if (j != vertex && matrix[vertex][j] != NO_EDGE) {
                count++;
            }
        }

        int[] neighbors = new int[count];
        int idx = 0;
        for (int j = 0; j < vertexCount; j++) {
            if (j != vertex && matrix[vertex][j] != NO_EDGE) {
                neighbors[idx++] = j;
            }
        }
        return neighbors;
    }

    public int getDegree(int vertex) {
        validateVertex(vertex);
        int count = 0;
        for (int j = 0; j < vertexCount; j++) {
            if (j != vertex && matrix[vertex][j] != NO_EDGE) {
                count++;
            }
        }
        return count;
    }

    public double[][] getMatrix() {
        double[][] copy = new double[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, vertexCount);
        }
        return copy;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertexCount) {
            throw new IndexOutOfBoundsException("Vertex " + v + " is out of range [0, " + (vertexCount - 1) + "]");
        }
    }
}
