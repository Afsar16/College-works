package p8;

import java.util.Scanner;

public class Dijkstra {

    // Method to implement Dijkstra's algorithm
    public static void dijkstra(int vertices, int[][] graph, int source) {
        int[] distance = new int[vertices];
        boolean[] visited = new boolean[vertices];

        // Initialize distances to infinity and visited to false
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Distance to the source is 0
        distance[source] = 0;

        // Find shortest path for each vertex
        for (int count = 0; count < vertices - 1; count++) {
            // Get the vertex with the minimum distance value
            int u = minDistance(distance, visited, vertices);
            visited[u] = true;

            // Update the distance value of the adjacent vertices of the chosen vertex
            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && graph[u][v] != Integer.MAX_VALUE && distance[u] != Integer.MAX_VALUE &&
                        distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
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

    // Helper method to find the vertex with the minimum distance value
    private static int minDistance(int[] distance, boolean[] visited, int vertices) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }

        return minIndex;
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

        // Call Dijkstra's algorithm
        dijkstra(vertices, graph, source);

        scanner.close();
    }
}
