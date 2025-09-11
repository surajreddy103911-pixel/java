// Full BST Implementation with TUF-style delete

// ================== Node Class ==================
class Node {
    int val;
    Node left, right;

    Node(int item) {
        val = item;
        left = right = null;
    }
}

// ================== BST Class ==================
public class BST {
    Node root;

    BST() {
        root = null;
    }

    // ================== INSERT ==================
    private Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.val) {
            root.left = insert(root.left, key);
        } else if (key > root.val) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    // ================== SEARCH ==================
    private boolean search(Node root, int key) {
        if (root == null) return false;
        if (root.val == key) return true;

        if (key < root.val) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    public boolean search(int key) {
        return search(root, key);
    }

    // ================== DELETE (TUF STYLE) ==================
    private Node deleteNode(Node root, int key) {
        if (root == null) return null;

        if (root.val == key) {
            return helper(root);
        }

        Node dummy = root;
        while (root != null) {
            if (root.val > key) {
                if (root.left != null && root.left.val == key) {
                    root.left = helper(root.left);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                if (root.right != null && root.right.val == key) {
                    root.right = helper(root.right);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return dummy;
    }

    private Node helper(Node root) {
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;

        Node rightChild = root.right;
        Node lastRight = findLastRight(root.left);
        lastRight.right = rightChild;
        return root.left;
    }

    private Node findLastRight(Node root) {
        if (root.right == null) return root;
        return findLastRight(root.right);
    }

    public void delete(int key) {
        root = deleteNode(root, key);
    }

    // ================== TRAVERSALS ==================
    public void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    public void preorder(Node root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.val + " ");
        }
    }

    // ================== MAIN ==================
    public static void main(String[] args) {
        BST tree = new BST();

        // Insert nodes
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal:");
        tree.inorder(tree.root);
        System.out.println();

        System.out.println("Search 40: " + tree.search(40));
        System.out.println("Search 90: " + tree.search(90));

        System.out.println("Delete 50 (root):");
        tree.delete(50);
        tree.inorder(tree.root);
        System.out.println();

        System.out.println("Preorder traversal:");
        tree.preorder(tree.root);
        System.out.println();

        System.out.println("Postorder traversal:");
        tree.postorder(tree.root);
        System.out.println();
    }
}
