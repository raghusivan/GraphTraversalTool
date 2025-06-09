# Graph Traversal and Analysis Project

This project provides a set of utilities to generate, traverse, and analyze graphs using Dijkstra's algorithm. The graph is represented as a map where the keys are node IDs and the values are lists of edges. Each edge has a destination node and a weight.

**Note:** Ensure you have Java 11 or higher installed on your system.

## Running the Application

### 1. Unzip the Project
Unzip the downloaded project archive. It contains the `graph.jar` file or you can download from above as well and the source code.

### 2. Navigate to the Project Directory
Open your terminal and navigate to the directory where the graph.jar file has been downloaded.

### 3. Run the JAR File
To run the JAR file, use the following command:

```sh
java -jar graph.jar -N <number_of_vertices> -S <number_of_edges>
```

Replace `<number_of_vertices>` and `<number_of_edges>` with the desired values.

Example:

```sh
java -jar graph.jar -N 5 -S 10
```

This command generates a graph with 5 vertices and 10 edges, then prints the graph, the shortest path between two randomly selected nodes, and the graph properties.

### Example Output

Here is an example of what the output might look like:

```sh
$ java -jar graph.jar -N 5 -S 10
{
  :1 [(: 2 7), (: 4 7)],
  :2 [(: 1 3), (: 3 1), (: 4 5)],
  :3 [(: 2 9), (: 4 6)],
  :4 [(: 3 6), (: 5 8)],
  :5 [(: 3 10)]
}
Shortest path from 3 to 5: [3, 4, 5]
Graph Properties:
Eccentricity of node 1: 15
Radius of the graph: 13
Diameter of the graph: 22
```

## Running the Application from JShell

If you prefer to run the application from JShell, please refer to the [ReadMeForJShell.md](ReadMeForJShell.md) for detailed instructions.

## Project Source Code

The zip file also contains the source code for your reference. You can take a look at the implementation details and understand how the project is structured.

## Functional Programming Principles

The code is written using Java 11 and follows functional programming principles as much as possible within the constraints of Java 11. It leverages the functional interfaces and streams API introduced in Java 8 and further enhanced in subsequent versions.

## Minimum Java Version

Ensure you have Java 11 or higher installed on your system to run this project successfully.

---
