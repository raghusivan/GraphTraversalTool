package org.graph;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Map<Integer, List<Edge>> graph = GraphUtil.createGraphFromArgs(args);

        if (graph == null) {
            return;
        }

        GraphUtil.printGraph(graph);

        int N = Integer.parseInt(args[1]);

        // Randomly select two nodes and print the shortest path distance between them
        Random rand = new Random();
        int start = rand.nextInt(N) + 1;
        int end = rand.nextInt(N) + 1;
        List<Integer> shortestPath = Dijkstra.shortestPath(graph, start, end);
        System.out.println("Shortest path from " + start + " to " + end + ": " + shortestPath);

        GraphUtil.printGraphProperties(graph);
    }
}
