package org.graph;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: graph -N <number_of_vertices> -S <number_of_edges>");
            return;
        }

        int N = Integer.parseInt(args[1]);
        int S = Integer.parseInt(args[3]);

        Map<Integer, List<Edge>> graph = GraphGenerator.generateGraph(N, S);

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

        int start = 1;
        int end = N;
        List<Integer> shortestPath = Dijkstra.shortestPath(graph, start, end);
        System.out.println("Shortest path from " + start + " to " + end + ": " + shortestPath);

        System.out.println("Eccentricities:");
        graph.keySet().forEach(v -> System.out.println("Eccentricity of " + v + ": " + GraphProperties.eccentricity(graph, v)));
        System.out.println("Radius of the graph: " + GraphProperties.radius(graph));
        System.out.println("Diameter of the graph: " + GraphProperties.diameter(graph));
    }
}
