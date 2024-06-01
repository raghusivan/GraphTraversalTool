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

public class GraphValidator {

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

    public static <T> Stream<T> seqGraphBfs(Map<T, List<Edge>> graph, T start, Function<Edge, T> edgeMapper) {
        return seqGraph(new ArrayDeque<>(), graph, start, edgeMapper);
    }

    public static boolean isGraphConnected(Map<Integer, List<Edge>> graph) {
        return seqGraphBfs(graph, 1, Edge::getTo).count() == graph.size();
    }
}
