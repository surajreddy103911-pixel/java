import java.util.*;

// Node class
class Node {
    int data;
    Node left, right;

    Node(int value) {
        data = value;
        left = right = null;
    }
}

// Binary Tree class
public class BinaryTree {
    Node root;

    // 🔹 Insert (Level-order)
    void insert(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();

            if (temp.left == null) {
                temp.left = newNode;
                break;
            } else q.add(temp.left);

            if (temp.right == null) {
                temp.right = newNode;
                break;
            } else q.add(temp.right);
        }
    }

    // 🔹 Inorder Traversal (Left, Root, Right)
    void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    // 🔹 Preorder Traversal (Root, Left, Right)
    void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    // 🔹 Postorder Traversal (Left, Right, Root)
    void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    // 🔹 Level Order Traversal (BFS)
    void levelOrder() {
        if (root == null) return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();
            System.out.print(temp.data + " ");

            if (temp.left != null) q.add(temp.left);
            if (temp.right != null) q.add(temp.right);
        }
    }

    // 🔹 Search element in the tree
    boolean search(Node node, int key) {
        if (node == null) return false;
        if (node.data == key) return true;
        return search(node.left, key) || search(node.right, key);
    }

    // 🔹 Count total nodes
    int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // 🔹 Count leaf nodes
    int countLeaves(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    // 🔹 Height of tree
    int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // 🔹 Delete a node by value (using level order)
    void delete(int key) {
        if (root == null) return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        Node keyNode = null, temp = null;

        while (!q.isEmpty()) {
            temp = q.poll();

            if (temp.data == key) keyNode = temp;
            if (temp.left != null) q.add(temp.left);
            if (temp.right != null) q.add(temp.right);
        }

        if (keyNode != null) {
            int x = temp.data;
            deleteDeepest(temp);
            keyNode.data = x;
        }
    }

    // Helper: Delete deepest node
    void deleteDeepest(Node delNode) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();

            if (temp == delNode) {
                temp = null;
                return;
            }

            if (temp.left != null) {
                if (temp.left == delNode) {
                    temp.left = null;
                    return;
                } else q.add(temp.left);
            }

            if (temp.right != null) {
                if (temp.right == delNode) {
                    temp.right = null;
                    return;
                } else q.add(temp.right);
            }
        }
    }

    // 🔹 Main method to test
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Insert elements
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);

        /*
                    1
                   / \
                  2   3
                 / \ / \
                4  5 6  7
        */

        System.out.print("Inorder: ");
        tree.inorder(tree.root);
        System.out.print("\nPreorder: ");
        tree.preorder(tree.root);
        System.out.print("\nPostorder: ");
        tree.postorder(tree.root);
        System.out.print("\nLevel Order: ");
        tree.levelOrder();

        System.out.println("\n\nTotal Nodes: " + tree.countNodes(tree.root));
        System.out.println("Leaf Nodes: " + tree.countLeaves(tree.root));
        System.out.println("Height: " + tree.height(tree.root));

        int key = 5;
        System.out.println("\nSearching for " + key + ": " + tree.search(tree.root, key));

        tree.delete(3);
        System.out.print("\nAfter deleting 3, Level Order: ");
        tree.levelOrder();
    }
}


















class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class BinaryTree {
    Node root;

    // Constructor
    BinaryTree() {
        root = null;
    }

    // Inorder Traversal (Left, Root, Right)
    void inorder(Node node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    // Preorder Traversal (Root, Left, Right)
    void preorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    // Postorder Traversal (Left, Right, Root)
    void postorder(Node node) {
        if (node == null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Create binary tree
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        /*
                1
               / \
              2   3
             / \
            4   5
        */

        System.out.print("Inorder traversal: ");
        tree.inorder(tree.root);

        System.out.print("\nPreorder traversal: ");
        tree.preorder(tree.root);

        System.out.print("\nPostorder traversal: ");
        tree.postorder(tree.root);
    }
}


