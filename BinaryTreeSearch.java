import java.util.Scanner;

class BSTNode {
    int data;
    BSTNode left, right;

    public BSTNode(int value) {
        data = value;
        left = right = null;
    }
}

// Class managing the Binary Search Tree operations
class BinarySearchTree {
    BSTNode root;

    public BinarySearchTree() {
        root = null;
    }
    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    private BSTNode insertRecursive(BSTNode root, int data) {
        if (root == null) {
            root = new BSTNode(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRecursive(root.left, data);
        } else if (data > root.data) {
            root.right = insertRecursive(root.right, data);
        }

        return root;
    }

    // In-order 
    public void displayInorder() {
        inorderRecursive(root);
        System.out.println();
    }
    private void inorderRecursive(BSTNode root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.data + " ");
            inorderRecursive(root.right);
        }
    }

    //Pre-order
    public void displayPreorder() {
        preorderRecursive(root);
        System.out.println();
    }
    private void preorderRecursive(BSTNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRecursive(root.left);
            preorderRecursive(root.right);
        }
    }

    // Post-order
    public void displayPostorder() {
        postorderRecursive(root);
        System.out.println();
    }
    private void postorderRecursive(BSTNode root) {
        if (root != null) {
            postorderRecursive(root.left);
            postorderRecursive(root.right);
            System.out.print(root.data + " ");
        }
    }
}

class BinaryTreeSearchApp {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BinarySearchTree bst = new BinarySearchTree();
    System.out.print("Enter the number of nodes: ");
    int n = sc.nextInt();
    System.out.println("Enter " + n + " values:");
    for (int i = 0; i < n; i++) {
       int value = sc.nextInt();
            bst.insert(value);
        }

        System.out.print("In-order Traversal (Sorted): ");
        bst.displayInorder();
        System.out.print("Pre-order Traversal: ");
        bst.displayPreorder();
        System.out.print("Post-order Traversal: ");
        bst.displayPostorder();

        sc.close();
    }
}
