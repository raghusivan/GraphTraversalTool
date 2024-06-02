package org.graph;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class contains methods to compute the shortest paths in a graph using Dijkstra's algorithm.
 * The graph is represented as a map of node integers to a list of edges.
 */
public class Dijkstra {

    /**
     * Applies Dijkstra's algorithm to find the shortest path distances from the start node.
     *
     * @param graph    The graph as a map of nodes to edges.
     * @param start    The starting node.
     * @param previous A map to store the previous nodes in the shortest path.
     * @return A map of nodes to their shortest path distances from the start node.
     */
    public static Map<Integer, Integer> dijkstra(Map<Integer, List<Edge>> graph, int start, Map<Integer, Integer> previous) {
        Map<Integer, Integer> distances = graph.keySet().stream()
                .collect(Collectors.toMap(Function.identity(), node -> Integer.MAX_VALUE));
        PriorityQueue<Integer> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        distances.put(start, 0);
        nodes.add(start);

        Function<Integer, Integer> getMinDistance = (node) -> distances.get(node);
        BiFunction<Integer, Integer, Integer> updateDistance = (closest, edgeWeight) -> distances.get(closest) + edgeWeight;

        while (!nodes.isEmpty()) {
            int closest = nodes.poll();
            for (Edge edge : graph.getOrDefault(closest, Collections.emptyList())) {
                int newDist = updateDistance.apply(closest, edge.getWeight());
                if (newDist < distances.get(edge.getTo())) {
                    distances.put(edge.getTo(), newDist);
                    previous.put(edge.getTo(), closest);
                    nodes.remove(edge.getTo());
                    nodes.add(edge.getTo());
                }
            }
        }

        return distances;
    }

    /**
     * Finds the shortest path between the start and end nodes using Dijkstra's algorithm.
     *
     * @param graph The graph as a map of nodes to edges.
     * @param start The starting node.
     * @param end   The ending node.
     * @return A list of nodes representing the shortest path from start to end.
     */
    public static List<Integer> shortestPath(Map<Integer, List<Edge>> graph, int start, int end) {
        Map<Integer, Integer> previous = new HashMap<>();
        Map<Integer, Integer> distances = dijkstra(graph, start, previous);

        List<Integer> path = Optional.ofNullable(constructPath(previous, end))
                .orElse(Collections.emptyList());

        if (!path.isEmpty() && path.get(0).equals(start)) {
            return path;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Constructs the shortest path from the previous nodes map.
     *
     * @param previous The map of previous nodes.
     * @param end      The ending node.
     * @return A list of nodes representing the path from start to end.
     */
    private static List<Integer> constructPath(Map<Integer, Integer> previous, int end) {
        LinkedList<Integer> path = new LinkedList<>();
        for (Integer at = end; at != null; at = previous.get(at)) {
            path.addFirst(at);
        }
        return path;
    }
}
