package org.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides methods to calculate graph properties such as eccentricity, radius, and diameter.
 */
public class GraphProperties {

    /**
     * Calculates the eccentricity of a vertex in the graph.
     *
     * @param graph  The graph as a map of nodes to edges.
     * @param vertex The vertex to calculate the eccentricity for.
     * @return The eccentricity of the vertex.
     */
    public static int eccentricity(Map<Integer, List<Edge>> graph, int vertex) {
        Map<Integer, Integer> distances = Dijkstra.dijkstra(graph, vertex, new HashMap<>());
        return distances.values().stream()
                .filter(d -> d != Integer.MAX_VALUE)
                .max(Integer::compareTo)
                .orElse(0);
    }

    /**
     * Calculates the radius of the graph.
     *
     * @param graph The graph as a map of nodes to edges.
     * @return The radius of the graph.
     */
    public static int radius(Map<Integer, List<Edge>> graph) {
        return graph.keySet().stream()
                .mapToInt(v -> eccentricity(graph, v))
                .filter(e -> e != 0 && e != Integer.MAX_VALUE)
                .min()
                .orElse(0);
    }

    /**
     * Calculates the diameter of the graph.
     *
     * @param graph The graph as a map of nodes to edges.
     * @return The diameter of the graph.
     */
    public static int diameter(Map<Integer, List<Edge>> graph) {
        return graph.keySet().stream()
                .mapToInt(v -> eccentricity(graph, v))
                .filter(e -> e != 0 && e != Integer.MAX_VALUE)
                .max()
                .orElse(0);
    }
}
