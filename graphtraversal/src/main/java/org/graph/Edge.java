package org.graph;

import java.util.Objects;

public class Edge {
    private final int to;
    private final int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(: " + to + " " + weight + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return to == edge.to && weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, weight);
    }
}
