package org.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The GraphGenerator class is responsible for generating random, simple, connected directed graphs.
 * A graph is represented as a map where the keys are node IDs and the values are lists of edges.
 * Each edge has a destination node and a weight.
 */
public class GraphGenerator {

    /**
     * Generates a random, simple, connected directed graph with the given number of nodes (N) and edges (S).
     *
     * @param N The number of vertices in the graph.
     * @param S The number of edges in the graph.
     * @return A map representing the generated graph, where keys are node IDs and values are lists of edges.
     * @throws IllegalArgumentException if the number of edges is less than the minimum required to form a connected graph.
     * @throws IllegalStateException if the generated graph is not connected.
     */
    public static Map<Integer, List<Edge>> generateGraph(int N, int S) {

        // Validate the input parameters to ensure enough edges to form a connected graph.
        GraphValidator.validateGraphParameters(N, S);

        Random rand = new Random();

        // Function to create a new edge with a random weight between 1 and 10.
        Function<Integer, Edge> createEdge = to -> new Edge(to, rand.nextInt(10) + 1);

        // Create the initial graph with each node having an edge to the next node.
        Map<Integer, List<Edge>> initialGraph = IntStream.range(1, N)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> new ArrayList<>(List.of(createEdge.apply(i + 1))),
                        (e1, e2) -> {
                            List<Edge> edges = new ArrayList<>(e1);
                            edges.addAll(e2);
                            return edges;
                        },
                        TreeMap::new
                ));

        // Function to add additional edges to the graph until the required number of edges (S) is reached.
        Function<Map<Integer, List<Edge>>, Map<Integer, List<Edge>>> addEdges = graph -> {
            int edgesToAdd = S - (N - 1);
            List<Integer> vertices = new ArrayList<>(graph.keySet());

            // Add edges until the desired number of edges (S) is reached.
            while (edgesToAdd > 0) {
                int from = vertices.get(rand.nextInt(vertices.size()));
                int to = rand.nextInt(N) + 1;
                if (from != to) {
                    int weight = rand.nextInt(10) + 1;
                    List<Edge> neighbors = graph.computeIfAbsent(from, k -> new ArrayList<>());
                    if (neighbors.stream().noneMatch(e -> e.getTo() == to)) {
                        neighbors.add(new Edge(to, weight));
                        edgesToAdd--;
                    }
                }
            }

            return graph;
        };

        // Function to ensure all vertices are present in the graph, even if they have no edges.
        Function<Map<Integer, List<Edge>>, Map<Integer, List<Edge>>> ensureAllVertices = graph ->
                IntStream.rangeClosed(1, N)
                        .boxed()
                        .reduce(graph, (g, i) -> {
                            g.putIfAbsent(i, new ArrayList<>());
                            return g;
                        }, (g1, g2) -> g1);

        // Apply the functions to ensure all vertices are present and to add additional edges.
        Map<Integer, List<Edge>> graph = ensureAllVertices
                .andThen(addEdges)
                .apply(initialGraph);

        // Sort the edges for each node by the destination node ID.
        graph.values().forEach(edges -> edges.sort(Comparator.comparingInt(Edge::getTo)));

        // Check if the graph is connected, throw an exception if it is not.
        if (!GraphValidator.isGraphConnected(graph)) {
            throw new IllegalStateException("The generated graph is not connected.");
        }

        return graph;
    }
}
