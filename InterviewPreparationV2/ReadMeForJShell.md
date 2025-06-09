# README

## Graph Traversal and Analysis

This project provides a set of utilities to generate, traverse, and analyze graphs using Dijkstra's algorithm. The graph is represented as a map where the keys are node IDs and the values are lists of edges. Each edge has a destination node and a weight.

Note: Ensure you have Java 11 or higher installed on your system.

## Steps to Run the Code

### 1. Start JShell
Open your terminal and start JShell with the following command:
```sh
jshell
```

### 2. Copy and Paste the Complete Code
Copy and paste the code for each Java class one by one into the JShell session:

```java
// Define necessary imports
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Define Edge class
public class Edge {
    private final int to;
    private final int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(: " + to + " " + weight + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return to == edge.to && weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, weight);
    }
}
```

```java
// Define GraphValidator class
public class GraphValidator {

    public static void validateGraphParameters(int N, int S) {
        if (S < N - 1) {
            System.out.println("Validation Error: The number of edges (S = " + S + ") is insufficient to form a connected graph with " + N + " nodes. A minimum of " + (N - 1) + " edges is required.");
            throw new IllegalArgumentException("Number of edges S must be at least N - 1 for the graph to be connected. Given: N = " + N + ", S = " + S);
        }
    }

    public static <T> Stream<T> seqGraph(Queue<T> queue, Map<T, List<Edge>> graph, T start, Function<Edge, T> edgeMapper) {
        Set<T> explored = new HashSet<>();
        queue.add(start);
        explored.add(start);

        return Stream.generate(() -> {
            if (queue.isEmpty()) {
                return null;
            }
            T node = queue.poll();
            List<Edge> neighbors = graph.getOrDefault(node, Collections.emptyList());
            neighbors.stream()
                    .map(edgeMapper)
                    .filter(n -> !explored.contains(n))
                    .forEach(n -> {
                        queue.add(n);
                        explored.add(n);
                    });
            return node;
        }).takeWhile(Objects::nonNull);
    }

    public static <T> Stream<T> seqGraphBfs(Map<T, List<Edge>> graph, T start, Function<Edge, T> edgeMapper) {
        return seqGraph(new ArrayDeque<>(), graph, start, edgeMapper);
    }

    public static boolean isGraphConnected(Map<Integer, List<Edge>> graph) {
        return seqGraphBfs(graph, 1, Edge::getTo).count() == graph.size();
    }
}
```

```java
// Define Dijkstra class
public class Dijkstra {

    public static Map<Integer, Integer> dijkstra(Map<Integer, List<Edge>> graph, int start, Map<Integer, Integer> previous) {
        Map<Integer, Integer> distances = graph.keySet().stream()
                .collect(Collectors.toMap(Function.identity(), node -> Integer.MAX_VALUE));
        PriorityQueue<Integer> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        distances.put(start, 0);
        nodes.add(start);

        Function<Integer, Integer> getMinDistance = distances::get;
        BiFunction<Integer, Integer, Integer> updateDistance = (closest, edgeWeight) -> distances.get(closest) + edgeWeight;

        while (!nodes.isEmpty()) {
            int closest = nodes.poll();
            for (Edge edge : graph.getOrDefault(closest, Collections.emptyList())) {
                int newDist = updateDistance.apply(closest, edge.getWeight());
                if (newDist < distances.get(edge.getTo())) {
                    distances.put(edge.getTo(), newDist);
                    previous.put(edge.getTo(), closest);
                    nodes.remove(edge.getTo());
                    nodes.add(edge.getTo());
                }
            }
        }

        return distances;
    }

    public static List<Integer> shortestPath(Map<Integer, List<Edge>> graph, int start, int end) {
        Map<Integer, Integer> previous = new HashMap<>();
        Map<Integer, Integer> distances = dijkstra(graph, start, previous);

        List<Integer> path = new LinkedList<>();
        for (Integer at = end; at != null; at = previous.get(at)) {
            path.add(0, at);
        }
        return path.isEmpty() || !path.get(0).equals(start) ? Collections.emptyList() : path;
    }
}
```

```java
// Define GraphGenerator class
public class GraphGenerator {

    public static Map<Integer, List<Edge>> generateGraph(int N, int S) {

        GraphValidator.validateGraphParameters(N, S);

        Random rand = new Random();

        Function<Integer, Edge> createEdge = to -> new Edge(to, rand.nextInt(10) + 1);

        Map<Integer, List<Edge>> initialGraph = IntStream.rangeClosed(1, N)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> (i < N) ? new ArrayList<>(List.of(createEdge.apply(i + 1))) : new ArrayList<>(),
                        (e1, e2) -> {
                            List<Edge> edges = new ArrayList<>(e1);
                            edges.addAll(e2);
                            return edges;
                        },
                        TreeMap::new
                ));

        int edgesToAdd = S - (N - 1);
        List<Integer> vertices = new ArrayList<>(initialGraph.keySet());

        while (edgesToAdd > 0) {
            int from = vertices.get(rand.nextInt(vertices.size()));
            int to = rand.nextInt(N) + 1;
            if (from != to) {
                int weight = rand.nextInt(10) + 1;
                List<Edge> neighbors = initialGraph.computeIfAbsent(from, k -> new ArrayList<>());
                if (neighbors.stream().noneMatch(e -> e.getTo() == to)) {
                    neighbors.add(new Edge(to, weight));
                    edgesToAdd--;
                }
            }
        }

        IntStream.rangeClosed(1, N)
                .forEach(i -> initialGraph.putIfAbsent(i, new ArrayList<>()));

        initialGraph.values().forEach(edges -> edges.sort(Comparator.comparingInt(Edge::getTo)));

        if (!GraphValidator.isGraphConnected(initialGraph)) {
            throw new IllegalStateException("The generated graph is not connected.");
        }

        return Collections.unmodifiableMap(initialGraph);
    }
}
```

```java
// Define GraphProperties class
public class GraphProperties {

    public static int eccentricity(Map<Integer, List<Edge>> graph, int vertex) {
        Map<Integer, Integer> distances = Dijkstra.dijkstra(graph, vertex, new HashMap<>());
        return distances.values().stream()
                .filter(d -> d != Integer.MAX_VALUE)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public static int radius(Map<Integer, List<Edge>> graph) {
        return graph.keySet().stream()
                .mapToInt(v -> eccentricity(graph, v))
                .filter(e -> e != Integer.MAX_VALUE)
                .min()
                .orElse(0);
    }

    public static int diameter(Map<Integer, List<Edge>> graph) {
        return graph.keySet().stream()
                .mapToInt(v -> eccentricity(graph, v))
                .filter(e -> e != Integer.MAX_VALUE)
                .max()
                .orElse(0);
    }
}
```
```java
// Define GraphUtil class
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
        String graphString = graph.entrySet().stream()
                .map(entry -> "  :" + entry.getKey() + " [" +
                        entry.getValue().stream()
                                .map(Edge::toString)
                                .collect(Collectors.joining(", ")) + "]")
                .collect(Collectors.joining(",\n"));
        System.out.println(graphString);
        System.out.println("}");
    }

    public static void printGraphProperties(Map<Integer, List<Edge>> graph) {
        System.out.println("Graph Properties:");

        List<Integer> nodes = new ArrayList<>(graph.keySet());
        Random rand = new Random();
        int randomNode = nodes.get(rand.nextInt(nodes.size()));

        int eccentricity = GraphProperties.eccentricity(graph, randomNode);
        System.out.println("Eccentricity of node " + randomNode + ": " + eccentricity);

        System.out.println("Radius of the graph: " + GraphProperties.radius(graph));
        System.out.println("Diameter of the graph: " + GraphProperties.diameter(graph));
    }

    public static void printShortestPathBetweenRandomNodes(Map<Integer, List<Edge>> graph, String[] args) {
        int N = Integer.parseInt(args[1]);


        Random rand = new Random();
        int start = rand.nextInt(N) + 1;
        int end;
        do {
            end = rand.nextInt(N) + 1;
        } while (start == end);

        List<Integer> shortestPath = Dijkstra.shortestPath(graph, start, end);
        System.out.println("Shortest path from " + start + " to " + end + ": " + shortestPath);
    }
}

```

### Running the Methods Individually

You can call the methods individually with different arguments. For example:

1. **Create the Graph Randomly:**
   ```java
   String[] args = {"-N", "10", "-S", "15"};
   Map<Integer, List<Edge>> graph = GraphUtil.createGraphFromArgs(args);
   ```

2. **Print the Graph:**
   ```java
   GraphUtil.printGraph(graph);
   ```

3. **Print the Shortest Path:**
   ```java
   GraphUtil.printShortestPathBetweenRandomNodes(graph, args);
   ```

4. **Print Graph Properties:**
   ```java
   GraphUtil.printGraphProperties(graph);
   ```

Adjust the `args` array to change the number of vertices and edges as needed.
