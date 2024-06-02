package org.graph;

import java.util.List;
import java.util.Map;

public class GraphUtil {

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

    public static void printGraphProperties(Map<Integer, List<Edge>> graph) {
        System.out.println("Eccentricities:");
        graph.keySet().forEach(v -> System.out.println("Eccentricity of " + v + ": " + GraphProperties.eccentricity(graph, v)));
        System.out.println("Radius of the graph: " + GraphProperties.radius(graph));
        System.out.println("Diameter of the graph: " + GraphProperties.diameter(graph));
    }
}
