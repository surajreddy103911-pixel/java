package endsem;

import java.util.*;

// Class to represent a weighted edge (neighbor, weight)
class Pair {
    int node, weight;
    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

public class WeightedGraph {
    private int V;  // number of vertices
    private ArrayList<ArrayList<Pair>> adj; // adjacency list

    // Constructor
    WeightedGraph(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Add edge (undirected)
    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Pair(v, w));
        adj.get(v).add(new Pair(u, w)); // remove for directed graph
    }

    // ✅ Print adjacency list
    void printGraph() {
        System.out.println("Adjacency List of Weighted Graph:");
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (Pair p : adj.get(i)) {
                System.out.print("(" + p.node + ", " + p.weight + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // ✅ BFS Traversal
    List<Integer> bfsOfGraph(int start) {
        List<Integer> bfs = new ArrayList<>();
        boolean[] vis = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        vis[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            bfs.add(node);

            for (Pair neighbor : adj.get(node)) {
                if (!vis[neighbor.node]) {
                    vis[neighbor.node] = true;
                    q.add(neighbor.node);
                }
            }
        }
        return bfs;
    }

    // ✅ DFS Traversal
    List<Integer> dfsOfGraph(int start) {
        boolean[] vis = new boolean[V];
        List<Integer> dfs = new ArrayList<>();
        dfsUtil(start, vis, dfs);
        return dfs;
    }

    private void dfsUtil(int node, boolean[] vis, List<Integer> dfs) {
        vis[node] = true;
        dfs.add(node);

        for (Pair neighbor : adj.get(node)) {
            if (!vis[neighbor.node]) {
                dfsUtil(neighbor.node, vis, dfs);
            }
        }
    }

    // ✅ Main function
    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph(5);

        // Add weighted edges
        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 4);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 7);
        g.addEdge(2, 4, 3);

        // Print adjacency list
        g.printGraph();

        // BFS
        System.out.println("BFS starting from node 0: " + g.bfsOfGraph(0));

        // DFS
        System.out.println("DFS starting from node 0: " + g.dfsOfGraph(0));
    }
}
