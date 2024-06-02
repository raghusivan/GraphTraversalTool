package org.graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphPropertiesTest {

    // Sample graph as per the provided structure
    private Map<Integer, List<Edge>> createSampleGraph() {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(new Edge(2, 4)));
        graph.put(2, Arrays.asList(new Edge(3, 5)));
        graph.put(3, Arrays.asList(new Edge(1, 5), new Edge(2, 8), new Edge(4, 9)));
        graph.put(4, Arrays.asList(new Edge(2, 6), new Edge(3, 8), new Edge(5, 1)));
        graph.put(5, Arrays.asList(new Edge(3, 6), new Edge(4, 9)));
        return graph;
    }

    @Test
    void testEccentricity() {
        Map<Integer, List<Edge>> graph = createSampleGraph();
        int eccentricity = GraphProperties.eccentricity(graph, 1);
        assertEquals(19, eccentricity, "Eccentricity of node 1 is incorrect.");
    }

    @Test
    void testRadius() {
        Map<Integer, List<Edge>> graph = createSampleGraph();
        int radius = GraphProperties.radius(graph);
        assertEquals(10, radius, "Radius of the graph is incorrect.");
    }

    @Test
    void testDiameter() {
        Map<Integer, List<Edge>> graph = createSampleGraph();
        int diameter = GraphProperties.diameter(graph);
        assertEquals(19, diameter, "Diameter of the graph is incorrect.");
    }

    // Sample graph based on the given structure
    private Map<Integer, List<Edge>> createNewSampleGraph() {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(new Edge(2, 1), new Edge(5, 10)));
        graph.put(2, Arrays.asList(new Edge(3, 6)));
        graph.put(3, Arrays.asList(new Edge(4, 3), new Edge(5, 9)));
        graph.put(4, Arrays.asList(new Edge(5, 6)));
        graph.put(5, Arrays.asList());
        return graph;
    }

    @Test
    void testNewGraphEccentricity() {
        Map<Integer, List<Edge>> graph = createNewSampleGraph();
        int eccentricity = GraphProperties.eccentricity(graph, 1);
        assertEquals(10, eccentricity, "Eccentricity of node 1 is incorrect.");
    }

    @Test
    void testNewGraphRadius() {
        Map<Integer, List<Edge>> graph = createNewSampleGraph();
        int radius = GraphProperties.radius(graph);
        assertEquals(6, radius, "Radius of the graph is incorrect.");
    }

    @Test
    void testNewGraphDiameter() {
        Map<Integer, List<Edge>> graph = createNewSampleGraph();
        int diameter = GraphProperties.diameter(graph);
        assertEquals(15, diameter, "Diameter of the graph is incorrect.");
    }
}
