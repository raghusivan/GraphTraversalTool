package org.graph;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GraphValidatorTest {

    @Test
    void testValidateGraphParameters_valid() {
        int N = 5;
        int S = 5;
        assertDoesNotThrow(() -> GraphValidator.validateGraphParameters(N, S));
    }

    @Test
    void testValidateGraphParameters_invalid() {
        int N = 5;
        int S = 3;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> GraphValidator.validateGraphParameters(N, S));
        assertEquals("Number of edges S must be at least N - 1 for the graph to be connected. Given: N = " + N + ", S = " + S, exception.getMessage());
    }

    @Test
    void testSeqGraphBfs() {
        Map<Integer, List<Edge>> graph = createSampleGraph();
        Stream<Integer> result = GraphValidator.seqGraphBfs(graph, 1, Edge::getTo);

        List<Integer> nodes = result.collect(Collectors.toList());
        List<Integer> expectedNodes = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expectedNodes.size(), nodes.size());
        assertTrue(nodes.containsAll(expectedNodes));
    }

    @Test
    void testIsGraphConnected_connected() {
        Map<Integer, List<Edge>> graph = createSampleGraph();
        assertTrue(GraphValidator.isGraphConnected(graph));
    }

    @Test
    void testIsGraphConnected_notConnected() {
        Map<Integer, List<Edge>> graph = createDisconnectedGraph();
        assertFalse(GraphValidator.isGraphConnected(graph));
    }

    private Map<Integer, List<Edge>> createSampleGraph() {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(new Edge(2, 1), new Edge(5, 10)));
        graph.put(2, Arrays.asList(new Edge(3, 6)));
        graph.put(3, Arrays.asList(new Edge(4, 3), new Edge(5, 9)));
        graph.put(4, Arrays.asList(new Edge(5, 6)));
        graph.put(5, Collections.emptyList());
        return graph;
    }

    private Map<Integer, List<Edge>> createDisconnectedGraph() {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(new Edge(2, 1)));
        graph.put(2, Collections.emptyList());
        graph.put(3, Arrays.asList(new Edge(4, 3)));
        graph.put(4, Collections.emptyList());
        return graph;
    }
}
