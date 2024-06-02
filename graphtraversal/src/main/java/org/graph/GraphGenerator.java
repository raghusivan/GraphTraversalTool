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

public class GraphGenerator {

    public static Map<Integer, List<Edge>> generateGraph(int N, int S) {

        GraphValidator.validateGraphParameters(N, S);

        Random rand = new Random();

        Function<Integer, Edge> createEdge = to -> new Edge(to, rand.nextInt(10) + 1);

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

        Function<Map<Integer, List<Edge>>, Map<Integer, List<Edge>>> addEdges = graph -> {
            int edgesToAdd = S - (N - 1);
            List<Integer> vertices = new ArrayList<>(graph.keySet());

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

        Function<Map<Integer, List<Edge>>, Map<Integer, List<Edge>>> ensureAllVertices = graph ->
                IntStream.rangeClosed(1, N)
                        .boxed()
                        .reduce(graph, (g, i) -> {
                            g.putIfAbsent(i, new ArrayList<>());
                            return g;
                        }, (g1, g2) -> g1);

        Map<Integer, List<Edge>> graph = ensureAllVertices
                .andThen(addEdges)
                .apply(initialGraph);

        graph.values().forEach(edges -> edges.sort(Comparator.comparingInt(Edge::getTo)));

        if (!GraphValidator.isGraphConnected(graph)) {
            throw new IllegalStateException("The generated graph is not connected.");
        }

        return graph;
    }
}
