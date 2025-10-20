import java.util.*;

// Class to store node and weight pair
class Pair {
    int node, weight;
    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

public class DijkstraExample {
    private int V; // number of vertices
    private ArrayList<ArrayList<Pair>> adj; // adjacency list

    // Constructor
    DijkstraExample(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++)
            adj.add(new ArrayList<>());
    }

    // Add weighted edge
    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Pair(v, w)); // u → v
        adj.get(v).add(new Pair(u, w)); // v → u (remove for directed)
    }

    // Dijkstra's algorithm
    void dijkstra(int src) {
        int[] dist = new int[V]; // store shortest distances
        Arrays.fill(dist, Integer.MAX_VALUE); // initialize as infinity
        dist[src] = 0; // distance to source = 0

        // PriorityQueue to always pick the node with smallest distance
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll(); // node with smallest distance
            int node = current.node;
            int currentDist = current.weight;

            // Visit all neighbors
            for (Pair neighbor : adj.get(node)) {
                int nextNode = neighbor.node;
                int edgeWeight = neighbor.weight;

                // Relaxation: check if going through current node gives shorter path
                if (currentDist + edgeWeight < dist[nextNode]) {
                    dist[nextNode] = currentDist + edgeWeight;
                    pq.add(new Pair(nextNode, dist[nextNode])); // add to PQ
                }
            }
        }

        // Print shortest distances from source
        System.out.println("Shortest distances from node " + src + ":");
        for (int i = 0; i < V; i++)
            System.out.println("To " + i + " -> " + dist[i]);
    }

    // Main method
    public static void main(String[] args) {
        DijkstraExample g = new DijkstraExample(6);

        // Weighted edges (u, v, w)
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 2);
        g.addEdge(1, 2, 5);
        g.addEdge(1, 3, 10);
        g.addEdge(2, 4, 3);
        g.addEdge(4, 3, 4);
        g.addEdge(3, 5, 11);

        g.dijkstra(0); // find shortest path from node 0
    }
}
