package org.graph;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * This class contains methods to validate the graph and check its connectivity.
 */
public class GraphValidator {

    /**
     * Validates the parameters for generating a graph.
     *
     * @param N The number of nodes.
     * @param S The number of edges.
     */
    public static void validateGraphParameters(int N, int S) {
        if (S < N - 1) {
            System.out.println("Validation Error: The number of edges (S = " + S + ") is insufficient to form a connected graph with " + N + " nodes. A minimum of " + (N - 1) + " edges is required.");
            throw new IllegalArgumentException("Number of edges S must be at least N - 1 for the graph to be connected. Given: N = " + N + ", S = " + S);
        }
    }

    /**
     * Traverses the graph using BFS and returns a stream of nodes.
     *
     * @param queue      The queue used for BFS.
     * @param graph      The graph as a map of nodes to edges.
     * @param start      The starting node.
     * @param edgeMapper A function to map edges to nodes.
     * @param <T>        The type of nodes.
     * @return A stream of nodes in BFS order.
     */
    public static <T> Stream<T> seqGraph(Queue<T> queue, Map<T, List<Edge>> graph, T start, Function<Edge, T> edgeMapper) {
        Set<T> explored = new HashSet<>();
        queue.add(start);
        explored.add(start);

        return Stream.generate(() -> {
            if (queue.isEmpty()) {
                return null;
            }
            T node = queue.poll();
            List<Edge> neighbors = graph.getOrDefault(node, Collections.emptyList());
            neighbors.stream()
                    .map(edgeMapper)
                    .filter(n -> !explored.contains(n))
                    .forEach(n -> {
                        queue.add(n);
                        explored.add(n);
                    });
            return node;
        }).takeWhile(Objects::nonNull);
    }

    /**
     * Traverses the graph using BFS and returns a stream of nodes.
     *
     * @param graph      The graph as a map of nodes to edges.
     * @param start      The starting node.
     * @param edgeMapper A function to map edges to nodes.
     * @param <T>        The type of nodes.
     * @return A stream of nodes in BFS order.
     */
    public static <T> Stream<T> seqGraphBfs(Map<T, List<Edge>> graph, T start, Function<Edge, T> edgeMapper) {
        return seqGraph(new ArrayDeque<>(), graph, start, edgeMapper);
    }

    /**
     * Checks if the graph is connected.
     *
     * @param graph The graph as a map of nodes to edges.
     * @return True if the graph is connected, false otherwise.
     */
    public static boolean isGraphConnected(Map<Integer, List<Edge>> graph) {
        return seqGraphBfs(graph, 1, Edge::getTo).count() == graph.size();
    }
}
