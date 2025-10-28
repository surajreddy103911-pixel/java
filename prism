import java.util.*;

// Pair class to store (weight, node)
class Pair {
    int weight;
    int node;
    Pair(int weight, int node) {
        this.weight = weight;
        this.node = node;
    }
}

public class PrimsAlgorithm {

    // ---------- Prim's Algorithm Function ----------
    static void prims(int n, ArrayList<ArrayList<Pair>> adj) {

        int[] parent = new int[n];      // Stores parent of each node
        int[] key = new int[n];         // Minimum weight to connect each node
        boolean[] mstSet = new boolean[n]; // Track nodes included in MST

        // Initialize arrays
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // Start from node 0
        key[0] = 0;

        // Min-heap based on key (weight)
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Pair(0, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().node;  // Extract minimum weight node
            mstSet[u] = true;        // Include in MST

            // Traverse adjacent vertices
            for (Pair it : adj.get(u)) {
                int v = it.node;
                int wt = it.weight;

                // If v not in MST and wt < key[v], update
                if (!mstSet[v] && wt < key[v]) {
                    parent[v] = u;
                    key[v] = wt;
                    pq.add(new Pair(wt, v));
                }
            }
        }

        // Print MST edges and total weight
        int totalWeight = 0;
        System.out.println("Edges in the MST:");
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + " - " + i + " : " + key[i]);
            totalWeight += key[i];
        }
        System.out.println("Total Weight of MST: " + totalWeight);
    }

    // ---------- Main Function ----------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        System.out.println("Enter edges (u v w):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj.get(u).add(new Pair(w, v));
            adj.get(v).add(new Pair(w, u)); // because undirected
        }

        prims(n, adj);
        sc.close();
    }
}


explain 
