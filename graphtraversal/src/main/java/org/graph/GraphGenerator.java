package org.graph;

import java.util.*;
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
        Map<Integer, List<Edge>> initialGraph = IntStream.rangeClosed(1, N)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> (i < N) ? new ArrayList<>(List.of(createEdge.apply(i + 1))) : new ArrayList<>(),
                        (e1, e2) -> {
                            List<Edge> edges = new ArrayList<>(e1);
                            edges.addAll(e2);
                            return edges;
                        },
                        TreeMap::new
                ));

        // Function to add additional edges to the graph until the required number of edges (S) is reached.
        int edgesToAdd = S - (N - 1);
        List<Integer> vertices = new ArrayList<>(initialGraph.keySet());

        while (edgesToAdd > 0) {
            int from = vertices.get(rand.nextInt(vertices.size()));
            int to = rand.nextInt(N) + 1;
            if (from != to) {
                int weight = rand.nextInt(10) + 1;
                List<Edge> neighbors = initialGraph.computeIfAbsent(from, k -> new ArrayList<>());
                if (neighbors.stream().noneMatch(e -> e.getTo() == to)) {
                    neighbors.add(new Edge(to, weight));
                    edgesToAdd--;
                }
            }
        }

        // Ensure all vertices are present in the graph, even if they have no edges.
        IntStream.rangeClosed(1, N)
                .forEach(i -> initialGraph.putIfAbsent(i, new ArrayList<>()));

        // Sort the edges for each node by the destination node ID.
        initialGraph.values().forEach(edges -> edges.sort(Comparator.comparingInt(Edge::getTo)));

        // Check if the graph is connected, throw an exception if it is not.
        if (!GraphValidator.isGraphConnected(initialGraph)) {
            throw new IllegalStateException("The generated graph is not connected.");
        }

        return Collections.unmodifiableMap(initialGraph);
    }
}
