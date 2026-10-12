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

    public boolean search(int data) {
        BSTNode current = root;
        while (current != null) {
            if (data == current.data) return true;
            current = data < current.data ? current.left : current.right;
        }
        return false;
    }

    public void delete(int data) {
        root = deleteRecursive(root, data);
    }

    private BSTNode deleteRecursive(BSTNode node, int data) {
        if (node == null) return null;
        if (data < node.data) {
            node.left = deleteRecursive(node.left, data);
        } else if (data > node.data) {
            node.right = deleteRecursive(node.right, data);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BSTNode successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = deleteRecursive(node.right, successor.data);
        }
        return node;
    }

    public Integer min() {
        return root == null ? null : minimumNode(root).data;
    }

    public Integer max() {
        if (root == null) return null;
        BSTNode current = root;
        while (current.right != null) current = current.right;
        return current.data;
    }

    private BSTNode minimumNode(BSTNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Height is measured in edges: an empty tree and a single-node tree have height 0.
    public int height() {
        return root == null ? 0 : heightRecursive(root) - 1;
    }

    private int heightRecursive(BSTNode node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }

    public int countNodes() {
        return countNodesRecursive(root);
    }

    private int countNodesRecursive(BSTNode node) {
        if (node == null) return 0;
        return 1 + countNodesRecursive(node.left) + countNodesRecursive(node.right);
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
        int choice;

        do {
            System.out.println("\nBST MENU");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Traversals");
            System.out.println("5. Min/Max");
            System.out.println("6. Height");
            System.out.println("7. Count nodes");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("How many values do you want to insert? ");
                    int numberOfValues = sc.nextInt();
                    System.out.println("Enter " + numberOfValues + " values in insertion order:");
                    for (int i = 0; i < numberOfValues; i++) {
                        bst.insert(sc.nextInt());
                    }
                    break;
                case 2:
                    System.out.print("Enter value to search: ");
                    System.out.println(bst.search(sc.nextInt()) ? "Value found." : "Value not found.");
                    break;
                case 3:
                    System.out.print("Enter value to delete: ");
                    int value = sc.nextInt();
                    if (bst.search(value)) {
                        bst.delete(value);
                        System.out.println("Value deleted.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 4:
                    System.out.print("In-order: ");
                    bst.displayInorder();
                    System.out.print("Pre-order: ");
                    bst.displayPreorder();
                    System.out.print("Post-order: ");
                    bst.displayPostorder();
                    break;
                case 5:
                    if (bst.min() == null) {
                        System.out.println("Tree is empty.");
                    } else {
                        System.out.println("Minimum: " + bst.min());
                        System.out.println("Maximum: " + bst.max());
                    }
                    break;
                case 6:
                    System.out.println("Height: " + bst.height());
                    break;
                case 7:
                    System.out.println("Node count: " + bst.countNodes());
                    break;
                case 0:
                    System.out.println("Exiting.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
