package com.stackoverflow.smartservice.structures.graph;

public class DisjointSet {

    private final int[] parent;
    private final int[] rank;
    private int componentCount;

    public DisjointSet(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        this.componentCount = size;
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (x < 0 || x >= parent.length) {
            throw new IndexOutOfBoundsException("Element " + x + " is out of range [0, " + (parent.length - 1) + "]");
        }
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        componentCount--;
        return true;
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public int getComponentCount() {
        return componentCount;
    }

    public int size() {
        return parent.length;
    }
}
