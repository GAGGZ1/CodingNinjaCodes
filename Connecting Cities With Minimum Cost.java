import java.util.* ;
import java.io.*; 
class Edge implements Comparable<Edge> {
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class Solution {
    private static int findParent(int vertex, int[] parent) {
        if (parent[vertex] != vertex) {
            parent[vertex] = findParent(parent[vertex], parent);
        }
        return parent[vertex];
    }

    // Union by rank
    private static void union(int x, int y, int[] parent, int[] rank) {
        int rootX = findParent(x, parent);
        int rootY = findParent(y, parent);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
    

    public static int getMinimumCost(int n, int m, int[][] connections) {
        // Write your code here.
        // Convert connections into a list of Edge objects
        List<Edge> edgeList = new ArrayList<>();
        for (int[] connection : connections) {
            edgeList.add(new Edge(connection[0], connection[1], connection[2]));
        }

        // Sort edges by weight
        Collections.sort(edgeList);

        // Initialize parent and rank for union-find
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int mstWeight = 0;
        int edgeCount = 0;

        // Process edges to build MST
        for (Edge edge : edgeList) {
            int rootSource = findParent(edge.source, parent);
            int rootDestination = findParent(edge.destination, parent);

            // If the edge connects two different components, include it in the MST
            if (rootSource != rootDestination) {
                mstWeight += edge.weight;
                edgeCount++;
                union(rootSource, rootDestination, parent, rank);

                // Stop if we have n-1 edges in the MST
                if (edgeCount == n - 1) break;
            }
        }

        // If we couldn't include enough edges to connect all nodes, return -1
        return edgeCount == n - 1 ? mstWeight : -1;
    }
    
}
