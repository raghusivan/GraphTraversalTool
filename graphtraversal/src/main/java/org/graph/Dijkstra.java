package org.graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {

    public static Map<Integer, Integer> dijkstra(Map<Integer, List<Edge>> graph, int start, Map<Integer, Integer> previous) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<Integer> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (int node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        nodes.add(start);

        while (!nodes.isEmpty()) {
            int closest = nodes.poll();
            for (Edge edge : graph.getOrDefault(closest, Collections.emptyList())) {
                int alt = distances.get(closest) + edge.getWeight();
                if (alt < distances.get(edge.getTo())) {
                    distances.put(edge.getTo(), alt);
                    previous.put(edge.getTo(), closest);
                    nodes.remove(edge.getTo());
                    nodes.add(edge.getTo());
                }
            }
        }

        // Ensure distances for unreachable nodes remain as Integer.MAX_VALUE
        for (int node : graph.keySet()) {
            distances.putIfAbsent(node, Integer.MAX_VALUE);
        }

        return distances;
    }

    public static List<Integer> shortestPath(Map<Integer, List<Edge>> graph, int start, int end) {
        Map<Integer, Integer> previous = new HashMap<>();
        Map<Integer, Integer> distances = dijkstra(graph, start, previous);

        List<Integer> path = new LinkedList<>();
        for (Integer at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        if (!path.isEmpty() && path.get(0) == start) {
            return path;
        } else {
            return Collections.emptyList();
        }
    }
}
