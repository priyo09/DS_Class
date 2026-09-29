class MergeSortedLists {

    // Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Insert node at the end
    static Node insertEnd(Node head, int data) {
        Node newNode = new Node(data);

        if (head == null) {
            return newNode;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;

        return head;
    }

    // Merge two sorted linked lists
    static Node merge(Node head1, Node head2) {

        // Dummy node
        Node dummy = new Node(0);
        Node current = dummy;

        // Compare nodes of both lists
        while (head1 != null && head2 != null) {

            if (head1.data <= head2.data) {
                current.next = head1;
                head1 = head1.next;
            } else {
                current.next = head2;
                head2 = head2.next;
            }

            current = current.next;
        }

        // Add remaining nodes
        if (head1 != null) {
            current.next = head1;
        } else {
            current.next = head2;
        }

        return dummy.next;
    }

    // Display linked list
    static void display(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("NULL");
    }

    // Main method
    public static void main(String[] args) {

        Node list1 = null;
        Node list2 = null;

        // First sorted list
        list1 = insertEnd(list1, 10);
        list1 = insertEnd(list1, 20);
        list1 = insertEnd(list1, 30);
        list1 = insertEnd(list1, 40);

        // Second sorted list
        list2 = insertEnd(list2, 15);
        list2 = insertEnd(list2, 25);
        list2 = insertEnd(list2, 35);
        list2 = insertEnd(list2, 45);

        System.out.println("First Sorted List:");
        display(list1);

        System.out.println("Second Sorted List:");
        display(list2);

        // Merge the two lists
        Node mergedList = merge(list1, list2);

        System.out.println("Merged Sorted List:");
        display(mergedList);
    }
}