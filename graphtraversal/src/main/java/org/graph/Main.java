package org.graph;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, List<Edge>> graph = GraphUtil.createGraphFromArgs(args);

        if (graph == null) {
            return;
        }

        GraphUtil.printGraph(graph);

        // Randomly select two nodes and print the shortest path distance between them
        GraphUtil.printShortestPathBetweenRandomNodes(graph, args);

        // Print graph properties
        GraphUtil.printGraphProperties(graph);
    }
}
