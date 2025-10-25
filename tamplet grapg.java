package endsem;
import java.util.*;

public class Graph {

    // ---------- Node Class ----------
    class Node {
        int value;
        ArrayList<Edge> Alist;

        Node(int e) {
            this.value = e;
            this.Alist = new ArrayList<>();
        }

        void addToAlist(Edge e) {
            this.Alist.add(e);
        }
    }

    // ---------- Edge Class ----------
    class Edge {
        Node v1, v2;
        int weight;

        Edge(Node v1, Node v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = w;
        }
    }

    ArrayList<Node> nodes = new ArrayList<>();

    // ---------- Get Node ----------
    Node getNode(int e) {
        for (Node n : nodes)
            if (n.value == e)
                return n;
        return null;
    }

    // ---------- Insert Node ----------
    void insertNode(int e) {
        if (getNode(e) == null)
            nodes.add(new Node(e));
    }

    // ---------- Insert Edge ----------
    void insertEdge(int v1, int v2, int w) {
        Node n1 = getNode(v1);
        Node n2 = getNode(v2);

        if (n1 == null) {
            n1 = new Node(v1);
            nodes.add(n1);
        }
        if (n2 == null) {
            n2 = new Node(v2);
            nodes.add(n2);
        }

        // Undirected edge
        n1.addToAlist(new Edge(n1, n2, w));
        n2.addToAlist(new Edge(n2, n1, w));
    }

    // ---------- DFS ----------
    void DFS(int startVal) {
        Node start = getNode(startVal);
        if (start == null) return;
        ArrayList<Node> visited = new ArrayList<>();
        System.out.print("DFS: ");
        dfsHelper(start, visited);
        System.out.println();
    }

    void dfsHelper(Node curr, ArrayList<Node> visited) {
        visited.add(curr);
        System.out.print(curr.value + " ");
        for (Edge e : curr.Alist)
            if (!visited.contains(e.v2))
                dfsHelper(e.v2, visited);
    }

    // ---------- BFS ----------
    void BFS(int startVal) {
        Node start = getNode(startVal);
        if (start == null) return;
        ArrayList<Node> visited = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        visited.add(start);
        q.add(start);
        System.out.print("BFS: ");
        while (!q.isEmpty()) {
            Node curr = q.poll();
            System.out.print(curr.value + " ");
            for (Edge e : curr.Alist)
                if (!visited.contains(e.v2)) {
                    visited.add(e.v2);
                    q.add(e.v2);
                }
        }
        System.out.println();
    }

    // ---------- Prim’s MST ----------
    void MST_prims() {
        if (nodes.isEmpty()) return;

        ArrayList<Node> inMST = new ArrayList<>();
        ArrayList<Edge> allEdges = new ArrayList<>();
        ArrayList<Edge> mstEdges = new ArrayList<>();

        Node start = nodes.get(0);
        inMST.add(start);
        allEdges.addAll(start.Alist);
        int totalWeight = 0;

        while (inMST.size() < nodes.size() && !allEdges.isEmpty()) {
            Edge minEdge = allEdges.get(0);
            for (Edge e : allEdges)
                if (e.weight < minEdge.weight)
                    minEdge = e;

            allEdges.remove(minEdge);
            if (inMST.contains(minEdge.v2)) continue;

            inMST.add(minEdge.v2);
            mstEdges.add(minEdge);
            totalWeight += minEdge.weight;

            for (Edge e : minEdge.v2.Alist)
                if (!inMST.contains(e.v2))
                    allEdges.add(e);
        }

        System.out.println("Prim’s MST edges:");
        for (Edge e : mstEdges)
            System.out.println(e.v1.value + " - " + e.v2.value + " (" + e.weight + ")");
        System.out.println("Total Weight = " + totalWeight);
    }

    // ---------- Print Graph ----------
    void printGraph() {
        for (Node n : nodes) {
            System.out.print("Node " + n.value + ": ");
            for (Edge e : n.Alist)
                System.out.print("(" + e.v1.value + "," + e.v2.value + "," + e.weight + ") ");
            System.out.println();
        }
    }

    // ---------- Main ----------
    public static void main(String[] args) {
        Graph g = new Graph();

        g.insertEdge(1, 2, 432);
        g.insertEdge(1, 3, 134);
        g.insertEdge(3, 2, 35);
        g.insertEdge(2, 4, 14);
        g.insertEdge(3, 4, 546);

        g.printGraph();
        g.BFS(1);
        g.DFS(1);
        g.MST_prims();
    }
}
