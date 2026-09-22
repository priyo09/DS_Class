import java.util.Scanner;

class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class MenuDLL {
    private static Node head = null;
    private static Node tail = null;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                handleChoice(choice);
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
                choice = -1;
            }
            System.out.println();
        } while (choice != 0);
    }

    // Displays the exact menu shown in the image[span_0](start_span)[span_0](end_span)
    private static void printMenu() {
        System.out.println("Double Linked List");
        System.out.println();
        System.out.println("1)Create List");
        System.out.println("2)Display");
        System.out.println("3)Insertion");
        System.out.println("   -Insert_Beg()");
        System.out.println("   -Insert_End()");
        System.out.println("   -Insert_Inbet()");
        System.out.println("4)Deletion");
        System.out.println("   -Delete_Beg()");
        System.out.println("   -Delete_End()");
        System.out.println("   -Delete_Inbet()");
        System.out.println("5) Double circular list");
        System.out.println("6)Count no of Nodes");
        System.out.println("7)Display every alternate Node");
        System.out.println("0)Exit");
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createList();
                break;
            case 2:
                displayList();
                break;
            case 3:
                System.out.println("not now");
                break;
            case 4:
                handleDeletion();
                break;
            case 5:
                handleDoubleCircularList();
                break;
            case 0:
                System.out.println("Exiting program...");
                break;
            default:
                // Covers 6, 7, and any other numbers
                System.out.println("not now");
                break;
        }
    }

    // Option 1: Create List
    private static void createList() {
        System.out.print("Enter the number of nodes to create: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number of nodes.");
            return;
        }

        head = null;
        tail = null;

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter data for node " + i + ": ");
            int data = scanner.nextInt();
            Node newNode = new Node(data);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }
        System.out.println("Doubly Linked List created successfully.");
    }

    // Option 2: Display
    private static void displayList() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node temp = head;
        System.out.print("List elements: null <-> ");
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Option 4: Deletion (Sub-menu handling for Delete_Beg, Delete_End, Delete_Inbet)
    private static void handleDeletion() {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        System.out.println("\n--- Deletion Sub-menu ---");
        System.out.println("1) Delete_Beg()");
        System.out.println("2) Delete_End()");
        System.out.println("3) Delete_Inbet()");
        System.out.print("Enter sub-choice for deletion: ");

        int subChoice = scanner.nextInt();
        switch (subChoice) {
            case 1:
                deleteBeg();
                break;
            case 2:
                deleteEnd();
                break;
            case 3:
                deleteInbet();
                break;
            default:
                System.out.println("Invalid sub-choice!");
                break;
        }
    }

    private static void deleteBeg() {
        System.out.println("Deleted node with value: " + head.data);
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    private static void deleteEnd() {
        System.out.println("Deleted node with value: " + tail.data);
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    private static void deleteInbet() {
        System.out.print("Enter position to delete: ");
        int pos = scanner.nextInt();

        if (pos <= 1) {
            deleteBeg();
            return;
        }

        Node current = head;
        int count = 1;

        while (current != null && count < pos) {
            current = current.next;
            count++;
        }

        if (current == null) {
            System.out.println("Position out of bounds.");
        } else if (current == tail) {
            deleteEnd();
        } else {
            System.out.println("Deleted node with value: " + current.data);
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    // Option 5: Convert linear doubly linked list to Double Circular List
    private static void handleDoubleCircularList() {
        if (head == null) {
            System.out.println("List is empty. Cannot convert to double circular list.");
            return;
        }

        // Link head prev to tail, and tail next to head
        head.prev = tail;
        tail.next = head;

        System.out.println("List successfully converted to Double Circular List!");
    }
}