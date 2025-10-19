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

    // Add edge (undirected)
    void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // remove if directed graph
    }

    // ✅ BFS Traversal (returns list instead of printing)
    List<Integer> bfsOfGraph(int start) {
        List<Integer> bfs = new ArrayList<>();
        boolean[] vis = new boolean[V];
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

    // DFS Traversal (same as before)
    void DFS(int start) {
        boolean[] vis = new boolean[V];
        System.out.print("DFS starting from " + start + ": ");
        dfsUtil(start, vis);
        System.out.println();
    }

    private void dfsUtil(int node, boolean[] vis) {
        vis[node] = true;
        System.out.print(node + " ");

        for (int it : adj.get(node)) {
            if (!vis[it]) {
                dfsUtil(it, vis);
            }
        }
    }

    public static void main(String[] args) {
        GraphTUF g = new GraphTUF(7);

        // Example edges (undirected graph)
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);

        // Perform BFS (now returns list)
        System.out.println("BFS Traversal starting from node 0: " + g.bfsOfGraph(0));

        // Perform DFS (prints directly)
        g.DFS(0);
    }
}
