package org.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphProperties {

    public static int eccentricity(Map<Integer, List<Edge>> graph, int vertex) {
        Map<Integer, Integer> distances = Dijkstra.dijkstra(graph, vertex, new HashMap<>());
        return distances.values().stream().filter(d -> d != Integer.MAX_VALUE).max(Integer::compareTo).orElse(Integer.MAX_VALUE);
    }

    public static int radius(Map<Integer, List<Edge>> graph) {
        return graph.keySet().stream().mapToInt(v -> eccentricity(graph, v)).min().orElse(Integer.MAX_VALUE);
    }

    public static int diameter(Map<Integer, List<Edge>> graph) {
        return graph.keySet().stream().mapToInt(v -> eccentricity(graph, v)).max().orElse(0);
    }
}