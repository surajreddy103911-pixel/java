import java.util.*;

// ---------- Pair Class ----------
// Used to store (distance, node)
class Pair {
    int dist;
    int node;
    Pair(int dist, int node) {
        this.dist = dist;
        this.node = node;
    }
}

public class DijkstraArrayList {

    // ---------- Dijkstra Function ----------
    static void dijkstra(int n, ArrayList<ArrayList<Pair>> adj, int src) {

        // Distance and Parent Arrays
        ArrayList<Integer> dist = new ArrayList<>(Collections.nCopies(n + 1, Integer.MAX_VALUE));
        ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(n + 1, -1));

        dist.set(src, 0);
        parent.set(src, src); // source’s parent = itself

        // PriorityQueue (Min-Heap) based on distance
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.add(new Pair(0, src));

        // Dijkstra's Algorithm
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();  // node with smallest distance
            int node = curr.node;
            int dis = curr.dist;

            // Traverse all adjacent nodes
            for (Pair it : adj.get(node)) {
                int adjNode = it.node;
                int weight = it.dist;

                // Relaxation step
                if (dis + weight < dist.get(adjNode)) {
                    dist.set(adjNode, dis + weight);
                    parent.set(adjNode, node);
                    pq.add(new Pair(dist.get(adjNode), adjNode));
                }
            }
        }

        // ---------- Print Results ----------
        System.out.println("\nShortest distances from source " + src + ":");
        for (int i = 1; i <= n; i++) {
            System.out.println("Node " + i + " → Distance: " + dist.get(i));
        }

        System.out.println("\nShortest paths from source " + src + ":");
        for (int i = 1; i <= n; i++) {
            if (i == src) continue;
            System.out.print("Path to " + i + ": ");
            printPath(i, parent);
            System.out.println();
        }
    }

    // ---------- Helper Function to Print Path ----------
    static void printPath(int node, ArrayList<Integer> parent) {
        if (parent.get(node) == node) {
            System.out.print(node + " ");
            return;
        }
        printPath(parent.get(node), parent);
        System.out.print(node + " ");
    }

    // ---------- Main Function ----------
    public static void main(String[] args) {
        int n = 6; // number of nodes

        // Create adjacency list
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
