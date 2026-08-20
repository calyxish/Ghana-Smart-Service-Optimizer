package com.stackoverflow.smartservice.structures.graph;

public class AdjacencyListGraph {

    private static class EdgeNode {
        int destination;
        double weight;
        EdgeNode next;

        EdgeNode(int destination, double weight, EdgeNode next) {
            this.destination = destination;
            this.weight = weight;
            this.next = next;
        }
    }

    private EdgeNode[] adjacencyList;
    private int vertexCount;
    private int edgeCount;
    private final boolean directed;

    public AdjacencyListGraph(int maxVertices, boolean directed) {
        this.adjacencyList = new EdgeNode[maxVertices];
        this.vertexCount = maxVertices;
        this.edgeCount = 0;
        this.directed = directed;
    }

    public AdjacencyListGraph(int maxVertices) {
        this(maxVertices, false);
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
        adjacencyList[from] = new EdgeNode(to, weight, adjacencyList[from]);
        if (!directed) {
            adjacencyList[to] = new EdgeNode(from, weight, adjacencyList[to]);
        }
        edgeCount++;
    }

    public void addEdge(int from, int to) {
        addEdge(from, to, 1.0);
    }

    public boolean hasEdge(int from, int to) {
        validateVertex(from);
        validateVertex(to);
        EdgeNode current = adjacencyList[from];
        while (current != null) {
            if (current.destination == to) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public double getEdgeWeight(int from, int to) {
        validateVertex(from);
        validateVertex(to);
        EdgeNode current = adjacencyList[from];
        while (current != null) {
            if (current.destination == to) {
                return current.weight;
            }
            current = current.next;
        }
        return -1;
    }

    public int[] getNeighbors(int vertex) {
        validateVertex(vertex);
        int count = 0;
        EdgeNode current = adjacencyList[vertex];
        while (current != null) {
            count++;
            current = current.next;
        }

        int[] neighbors = new int[count];
        current = adjacencyList[vertex];
        int i = 0;
        while (current != null) {
            neighbors[i++] = current.destination;
            current = current.next;
        }
        return neighbors;
    }

    public double[] getNeighborWeights(int vertex) {
        validateVertex(vertex);
        int count = 0;
        EdgeNode current = adjacencyList[vertex];
        while (current != null) {
            count++;
            current = current.next;
        }

        double[] weights = new double[count];
        current = adjacencyList[vertex];
        int i = 0;
        while (current != null) {
            weights[i++] = current.weight;
            current = current.next;
        }
        return weights;
    }

    public int getDegree(int vertex) {
        validateVertex(vertex);
        int count = 0;
        EdgeNode current = adjacencyList[vertex];
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public int[][] getAllEdges() {
        int[][] edges = new int[edgeCount * (directed ? 1 : 2)][2];
        int idx = 0;
        for (int v = 0; v < vertexCount; v++) {
            EdgeNode current = adjacencyList[v];
            while (current != null) {
                if (directed || v <= current.destination) {
                    if (idx < edges.length) {
                        edges[idx][0] = v;
                        edges[idx][1] = current.destination;
                        idx++;
                    }
                }
                current = current.next;
            }
        }
        int[][] result = new int[idx][2];
        System.arraycopy(edges, 0, result, 0, idx);
        return result;
    }

    public double[] getAllEdgeWeights() {
        double[] weights = new double[edgeCount];
        int idx = 0;
        for (int v = 0; v < vertexCount; v++) {
            EdgeNode current = adjacencyList[v];
            while (current != null) {
                if (directed || v <= current.destination) {
                    if (idx < weights.length) {
                        weights[idx++] = current.weight;
                    }
                }
                current = current.next;
            }
        }
        return weights;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertexCount) {
            throw new IndexOutOfBoundsException("Vertex " + v + " is out of range [0, " + (vertexCount - 1) + "]");
        }
    }
}
