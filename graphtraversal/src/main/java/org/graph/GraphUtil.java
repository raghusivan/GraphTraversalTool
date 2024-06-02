package org.graph;

import java.util.List;
import java.util.Map;
import java.util.Random;

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
        graph.forEach((key, value) -> {
            System.out.print("  :" + key + " [");
            for (int i = 0; i < value.size(); i++) {
                System.out.print(value.get(i));
                if (i < value.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("],");
        });
        System.out.println("}");
    }

    /**
     * Prints the properties of the graph such as eccentricity, radius, and diameter.
     *
     * @param graph The graph to print the properties of.
     */
    public static void printGraphProperties(Map<Integer, List<Edge>> graph) {
        System.out.println("Graph Properties:");
        graph.keySet().forEach(v -> {
            int eccentricity = GraphProperties.eccentricity(graph, v);
            System.out.println("Eccentricity of node " + v + ": " + eccentricity);
        });
        System.out.println("Radius of the graph: " + GraphProperties.radius(graph));
        System.out.println("Diameter of the graph: " + GraphProperties.diameter(graph));
    }
}
