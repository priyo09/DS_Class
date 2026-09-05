import java.util.*;

class SLinkedList {
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head = null;
    void createList(Scanner sc) {
        head = null;

        System.out.print("Enter no. of nodes: ");
        int n = sc.nextInt();

        Node temp = null;

        for (int i = 1; i <= n; i++) {

            System.out.print("Enter data for node " + i + ": ");
            int value = sc.nextInt();

            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
            } else {
                temp.next = newNode;
            }

            temp = newNode;
        }

        System.out.println("List created successfully.");
    }

    // Display
    void display() {

        Node p = head;

        if (p == null) {
            System.out.println("List is empty.");
            return;
        }

        while (p != null) {
            System.out.print(p.data + " ---> ");
            p = p.next;
        }

        System.out.println("NULL");
    }

    // Combine second list with first list
    void combine(SLinkedList secondList) {
        if (head == null) {
            head = secondList.head;
            return;
        }
        if (secondList.head == null) {
            return;
        }
        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = secondList.head;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SLinkedList firstList = new SLinkedList();
        SLinkedList secondList = new SLinkedList();

        int choice;

        do {

            System.out.println("\n========== MENU ==========");
            System.out.println("1. Create First List");
            System.out.println("2. Display First List");
            System.out.println("3. Create Second List");
            System.out.println("4. Display Second List");
            System.out.println("5. Combine Two Lists");
            System.out.println("6. Exit");
            System.out.println("==========================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Create First List ---");
                    firstList.createList(sc);
                    break;

                case 2:
                    System.out.println("\n--- First List ---");
                    firstList.display();
                    break;

                case 3:
                    System.out.println("\n--- Create Second List ---");
                    secondList.createList(sc);
                    break;

                case 4:
                    System.out.println("\n--- Second List ---");
                    secondList.display();
                    break;

                case 5:

                    // Combine the two lists
                    firstList.combine(secondList);

                    // Display all three lists
                    System.out.println("\n========== FINAL OUTPUT ==========");

                    System.out.println("\nFirst List:");
                    firstList.display();

                    System.out.println("\nSecond List:");
                    secondList.display();

                    System.out.println("\nCombined List:");
                    firstList.display();

                    System.out.println("\n==================================");
                    break;

                case 6:
                    System.out.println("Program exited.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}