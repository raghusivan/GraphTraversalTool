package org.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class for graph-related operations.
 */
public class GraphUtil {

    /**
     * Creates a graph from command line arguments.
     *
     * @param args The command line arguments.
     * @return The generated graph as a map of nodes to edges.
     */
    public static Map<Integer, List<Edge>> createGraphFromArgs(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: graph -N <number_of_vertices> -S <number_of_edges>");
            return null;
        }

        int N = Integer.parseInt(args[1]);
        int S = Integer.parseInt(args[3]);

        try {
            return GraphGenerator.generateGraph(N, S);
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Print stack trace explicitly
            return null;
        }
    }

    /**
     * Prints the graph in a readable format.
     *
     * @param graph The graph to print.
     */
    public static void printGraph(Map<Integer, List<Edge>> graph) {
        System.out.println("{");
        String graphString = graph.entrySet().stream()
                .map(entry -> "  :" + entry.getKey() + " [" +
                        entry.getValue().stream()
                                .map(Edge::toString)
                                .collect(Collectors.joining(", ")) + "]")
                .collect(Collectors.joining(",\n"));
        System.out.println(graphString);
        System.out.println("}");
    }

    /**
     * Prints the properties of the graph such as eccentricity, radius, and diameter.
     *
     * @param graph The graph to print the properties of.
     */
    public static void printGraphProperties(Map<Integer, List<Edge>> graph) {
        System.out.println("Graph Properties:");

        // Select a random node
        List<Integer> nodes = new ArrayList<>(graph.keySet());
        Random rand = new Random();
        int randomNode = nodes.get(rand.nextInt(nodes.size()));

        // Calculate the eccentricity of the random node
        int eccentricity = GraphProperties.eccentricity(graph, randomNode);
        System.out.println("Eccentricity of node " + randomNode + ": " + eccentricity);

        System.out.println("Radius of the graph: " + GraphProperties.radius(graph));
        System.out.println("Diameter of the graph: " + GraphProperties.diameter(graph));
    }
}
