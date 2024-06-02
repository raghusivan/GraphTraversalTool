package org.graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraTest {

    @Test
    void testDijkstraShortestPath() {
        Map<Integer, List<Edge>> graph = createSampleGraph();
        List<Integer> path = Dijkstra.shortestPath(graph, 1, 6);
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), path, "Shortest path from 1 to 6 is incorrect.");
    }

    private Map<Integer, List<Edge>> createSampleGraph() {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(new Edge(2, 1)));
        graph.put(2, Arrays.asList(new Edge(3, 2)));
        graph.put(3, Arrays.asList(new Edge(4, 3)));
        graph.put(4, Arrays.asList(new Edge(5, 1)));
        graph.put(5, Arrays.asList(new Edge(6, 2)));
        graph.put(6, Collections.emptyList());
        return graph;
    }

    private Map<Integer, List<Edge>> createNewGraph() {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(new Edge(2, 1)));
        graph.put(2, Arrays.asList(new Edge(3, 9), new Edge(5, 30)));
        graph.put(3, Arrays.asList(new Edge(4, 4)));
        graph.put(4, Arrays.asList(new Edge(5, 9)));
        graph.put(5, Arrays.asList(new Edge(1, 3)));
        return graph;
    }

    @Test
    void testDijkstraShortestDistance() {

        int start = 1;
        int end = 5;
        Map<Integer, List<Edge>> graph = createNewGraph();

        Map<Integer, Integer> distances = Dijkstra.dijkstra(graph, 1, new HashMap<>());
        int shortestDistance = distances.get(5);

        List<Integer> shortestPath = Dijkstra.shortestPath(graph, start, end);
        System.out.println("Shortest path from " + start + " to " + end + ": " + shortestPath);
        Assertions.assertEquals(23, shortestDistance, "Shortest distance from 1 to 5 is incorrect.");
    }
}
