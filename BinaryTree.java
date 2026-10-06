class Node {
  int key;
  Node left, right;

  public Node(int item) {
    key = item;
    left = right = null;
  }
}

class BinaryTree {
  Node root;
  // Traverse tree
  public void Inorder(Node node) {
    if (node != null) {
      Inorder(node.left);
      System.out.print(" " + node.key);
      Inorder(node.right);
    }
  }

  public void Preorder(Node node) {
    if (node != null) {
      System.out.print(" " + node.key);
      Preorder(node.left);

      Preorder(node.right);
    }
  }

  public void Postorder(Node node) {
    if (node != null) {
      Postorder(node.left);

      Postorder(node.right);
      System.out.print(" " + node.key);
    }
  }
}

class Main {
  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();
    // create nodes of the tree
    tree.root = new Node(1);
    tree.root.left = new Node(12);
    tree.root.right = new Node(9);
    tree.root.left.left = new Node(5);
    tree.root.left.right = new Node(6);
    System.out.print("\nBinary Tree: \n");
    System.out.println("IN.");
    tree.Inorder(tree.root);
    System.out.println("\nPRE.");
    tree.Preorder(tree.root);
    System.out.println("\nPOST");
    tree.Postorder(tree.root);
  }
}