package p7;

import java.util.Scanner;

public class Belman {

    // Method to implement the Bellman-Ford algorithm
    public static void bellmanFord(int vertices, int[][] graph, int source) {
        // Initialize distance array with a large value (infinity)
        int[] distance = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // Distance to the source is 0
        distance[source] = 0;

        // Relax edges (vertices - 1) times
        for (int i = 1; i < vertices; i++) {
            for (int u = 0; u < vertices; u++) {
                for (int v = 0; v < vertices; v++) {
                    if (graph[u][v] != Integer.MAX_VALUE) {
                        if (distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                            distance[v] = distance[u] + graph[u][v];
                        }
                    }
                }
            }
        }

        // Print the results
        for (int i = 0; i < vertices; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + " is unreachable from the source.");
            } else {
                System.out.println("Shortest distance from source to vertex " + i + " is: " + distance[i]);
            }
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of vertices
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        // Initialize the graph (adjacency matrix)
        int[][] graph = new int[vertices][vertices];
        
        // Input the adjacency matrix directly
        System.out.println("Enter the adjacency matrix (enter Integer.MAX_VALUE for no edge):");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = scanner.nextInt();
                if (i == j) {
                    graph[i][j] = 0; // Distance from a vertex to itself is 0
                }
            }
        }

        // Input the source vertex
        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        // Call Bellman-Ford algorithm
        bellmanFord(vertices, graph, source);

        scanner.close();
    }
}
