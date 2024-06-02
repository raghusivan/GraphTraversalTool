package org.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class GraphGeneratorTest {

    @Test
    void testGenerateGraphWithSingleNode() {
        int N = 1; // Number of nodes
        int S = 0; // Number of edges

        Map<Integer, List<Edge>> graph = GraphGenerator.generateGraph(N, S);

        // Check if the graph has the correct number of nodes
        Assertions.assertEquals(N, graph.size(), "Graph should have " + N + " node.");

        // Check if the graph has no edges
        long totalEdges = graph.values().stream().mapToLong(List::size).sum();
        Assertions.assertEquals(S, totalEdges, "Graph should have no edges.");
    }

    @Test
    void testGenerateGraphWithMinimalNodesAndEdges() {
        int N = 2; // Number of nodes
        int S = 1; // Number of edges

        Map<Integer, List<Edge>> graph = GraphGenerator.generateGraph(N, S);

        // Check if the graph has the correct number of nodes
        Assertions.assertEquals(N, graph.size(), "Graph should have " + N + " nodes.");

        // Check if the graph has at least one edge
        long totalEdges = graph.values().stream().mapToLong(List::size).sum();
        Assertions.assertTrue(totalEdges >= S, "Graph should have at least " + S + " edges.");
    }

    @Test
    void testGenerateGraphWithMoreEdges() {
        int N = 5; // Number of nodes
        int S = 10; // Number of edges

        Map<Integer, List<Edge>> graph = GraphGenerator.generateGraph(N, S);

        // Check if the graph has the correct number of nodes
        Assertions.assertEquals(N, graph.size(), "Graph should have " + N + " nodes.");

        // Check if the graph has at least the number of edges
        long totalEdges = graph.values().stream().mapToLong(List::size).sum();
        Assertions.assertTrue(totalEdges >= S, "Graph should have at least " + S + " edges.");
    }

    @Test
    void testGenerateGraphWithNodesAndEdgesEqual() {
        int N = 5; // Number of nodes
        int S = 5; // Number of edges

        Map<Integer, List<Edge>> graph = GraphGenerator.generateGraph(N, S);

        // Check if the graph has the correct number of nodes
        Assertions.assertEquals(N, graph.size(), "Graph should have " + N + " nodes.");

        // Check if the graph has at least the number of edges
        long totalEdges = graph.values().stream().mapToLong(List::size).sum();
        Assertions.assertTrue(totalEdges >= S, "Graph should have at least " + S + " edges.");
    }

    @Test
    void testGenerateGraphEnsuringConnected() {
        int N = 6; // Number of nodes
        int S = 10; // Number of edges

        Map<Integer, List<Edge>> graph = GraphGenerator.generateGraph(N, S);

        // Check if the graph is connected
        Assertions.assertTrue(GraphValidator.isGraphConnected(graph), "Graph should be connected.");
    }

    @Test
    void testGenerateGraphWithLargeNumberOfNodesAndEdges() {
        int N = 100; // Number of nodes
        int S = 200; // Number of edges

        Map<Integer, List<Edge>> graph = GraphGenerator.generateGraph(N, S);

        // Check if the graph has the correct number of nodes
        Assertions.assertEquals(N, graph.size(), "Graph should have " + N + " nodes.");

        // Check if the graph has at least the number of edges
        long totalEdges = graph.values().stream().mapToLong(List::size).sum();
        Assertions.assertTrue(totalEdges >= S, "Graph should have at least " + S + " edges.");
    }

    @Test
    void testGenerateGraphWithInvalidEdges() {
        int N = 5; // Number of nodes
        int S = 3; // Invalid number of edges (less than N - 1)

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            GraphGenerator.generateGraph(N, S);
        });

        String expectedMessage = "Number of edges S must be at least N - 1 for the graph to be connected. Given: N = " + N + ", S = " + S;
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage), "Exception message should contain the correct details.");
    }
}
