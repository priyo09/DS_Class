import java.util.Scanner;

public class CIRQUEUE {

    int size;
    int front;
    int rear;
    int[] queue;

    // Constructor
    public CIRQUEUE(int capacity) {
        size = capacity;
        queue = new int[size];
        front = -1;
        rear = -1;
    }

    // Insert
    void cqinsert(int val) {

        if (front == (rear + 1) % size) {
            System.out.println("Overflow");
        }
        else {
            if (front == -1 && rear == -1) {
                front = 0;
                rear = 0;
                queue[rear] = val;
            }
            else {
                rear = (rear + 1) % size;
                queue[rear] = val;
            }

            System.out.println("Inserted: " + val);
        }
    }

    // Delete
    void cqdelete() {

        if (front == -1) {
            System.out.println("Underflow");
        }
        else {
            System.out.println("Deleted: " + queue[front]);

            if (front == rear) {
                front = -1;
                rear = -1;
            }
            else {
                front = (front + 1) % size;
            }
        }
    }

    // Display
    void cqdisplay() {

        if (front == -1) {
            System.out.println("Queue is empty");
        }
        else {
            System.out.println("Queue elements are:");

            int i = front;

            while (i != rear) {
                System.out.print(queue[i] + " ");
                i = (i + 1) % size;
            }

            System.out.println(queue[rear]);
        }
    }

    // Main method
    public static void main(String[] args) {

        CIRQUEUE q = new CIRQUEUE(3);

        int ch, val;
        char choice;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Display");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");

            ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.println("Enter element to be inserted:");
                    val = sc.nextInt();
                    q.cqinsert(val);
                    break;

                case 2:
                    q.cqdelete();
                    break;

                case 3:
                    q.cqdisplay();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("OUTSIDE CASE VALUE");
            }

            System.out.println("\nDO YOU WANT TO CONTINUE [Y/N]");
            choice = sc.next().charAt(0);

        } while (choice == 'Y' || choice == 'y');

        sc.close();
    }
}