import java.util.*;

public class GraphTUF {
    private int V;  // number of vertices
    private ArrayList<ArrayList<Integer>> adj; // adjacency list

    // Constructor
    GraphTUF(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Function to add an undirected edge
    void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);  // because undirected
    }

    // BFS Traversal
    List<Integer> bfsOfGraph(int start) {
        List<Integer> bfs = new ArrayList<>();
        boolean vis[] = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        vis[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            bfs.add(node);

            for (Integer it : adj.get(node)) {
                if (!vis[it]) {
                    vis[it] = true;
                    q.add(it);
                }
            }
        }
        return bfs;
    }

    // Print adjacency list
    void printGraph() {
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (Integer it : adj.get(i)) {
                System.out.print(it + " ");
            }
            System.out.println();
        }
    }

    // Main method
    public static void main(String[] args) {
        GraphTUF g = new GraphTUF(5);

        // Adding edges (undirected)
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        // Print adjacency list
        System.out.println("Adjacency List:");
        g.printGraph();

        // Perform BFS from node 0
        System.out.println("\nBFS Traversal starting from node 0:");
        System.out.println(g.bfsOfGraph(0));
    }
}
