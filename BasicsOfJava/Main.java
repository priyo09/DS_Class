package BasicsOfJava;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static class Queue_imp {

        int size;
        int front;
        int rear;
        int[] queue;

        public Queue_imp(int capacity) {
            size = capacity;
            queue = new int[size];
            front = -1;
            rear = -1;
        }

        public void qinsert(int val) {

            if (rear == size - 1) {
                System.out.println("Overflow");
            } else {

                if (front == -1 && rear == -1) {
                    front = 0;
                    rear = 0;
                    queue[rear] = val;
                } else {
                    rear++;
                    queue[rear] = val;
                }
            }
        }

        public void qdelete() {

            if (front == -1) {
                System.out.println("Underflow");
            } else {

                if (front == rear) {
                    System.out.println("Deleted " + queue[front]);
                    front = -1;
                    rear = -1;
                } else {
                    System.out.println("Deleted " + queue[front]);
                    front++;
                }
            }
        }

        public void qdisplay() {

            if (front == -1) {
                System.out.println("Queue is empty");
            } else {

                System.out.println("Queue elements are:");

                for (int i = front; i <= rear; i++) {
                    System.out.println(queue[i]);
                }
            }
        }
    }

    public static void queueOperations(Scanner sc) {

        Queue_imp q = new Queue_imp(5);

        int ch, val;
        char choice;

        do {

            System.out.println("\n===== QUEUE OPERATIONS =====");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Display");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter element to be inserted: ");
                    val = sc.nextInt();
                    q.qinsert(val);
                    break;

                case 2:
                    q.qdelete();
                    break;

                case 3:
                    q.qdisplay();
                    break;

                case 4:
                    System.out.println("Exiting Queue Operations...");
                    return;

                default:
                    System.out.println("OUTSIDE CASE VALUE");
            }

            System.out.print("Do you want to continue [Y/N]: ");
            choice = sc.next().charAt(0);

        } while (choice == 'Y' || choice == 'y');
    }

    public static void reverseQueue(Scanner sc) {

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] queue = new int[n];
        int[] stack = new int[n];

        int front = 0;
        int rear = n - 1;
        int top = -1;

        System.out.println("Enter queue elements:");

        for (int i = 0; i < n; i++) {
            queue[i] = sc.nextInt();
        }

        while (front <= rear) {
            stack[++top] = queue[front++];
        }

        front = 0;
        rear = -1;

        while (top >= 0) {
            queue[++rear] = stack[top--];
        }

        System.out.println("Reversed Queue:");

        for (int i = front; i <= rear; i++) {
            System.out.print(queue[i] + " ");
        }

        System.out.println();
    }

    public static void searchOperations(Scanner sc) {

        char ch;

        do {

            System.out.println("\n===== SEARCH MENU =====");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");

            System.out.print("Enter your choice (1 or 2): ");
            int choice = sc.nextInt();

            System.out.print("Enter the size of the array: ");
            int size = sc.nextInt();

            int[] arr = new int[size];

            System.out.println("Enter the elements of the array:");

            for (int i = 0; i < size; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.print("Enter the element to search: ");
            int key = sc.nextInt();

            boolean found = false;

            switch (choice) {

                case 1:

                    for (int i = 0; i < size; i++) {

                        if (arr[i] == key) {
                            System.out.println("Element found at index: " + i);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Element not found in the array.");
                    }

                    break;

                case 2:

                    Arrays.sort(arr);

                    System.out.println("Sorted Array: " + Arrays.toString(arr));

                    int left = 0;
                    int right = size - 1;

                    while (left <= right) {

                        int mid = left + (right - left) / 2;

                        if (arr[mid] == key) {
                            System.out.println("Element found at index: " + mid);
                            found = true;
                            break;
                        } else if (arr[mid] < key) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }

                    if (!found) {
                        System.out.println("Element not found in the array.");
                    }

                    break;

                default:
                    System.out.println("Invalid choice.");
            }

            System.out.print("\nDo you want to continue? (Y/N): ");
            ch = sc.next().charAt(0);

        } while (ch == 'Y' || ch == 'y');

        System.out.println("Search operation terminated.");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String username = "Priyantan";
        String password = "250301120275";

        System.out.print("Enter your username: ");
        String user = sc.nextLine();

        System.out.print("Enter your password: ");
        String pass = sc.nextLine();

        if (!user.equals(username) || !pass.equals(password)) {

            System.out.println("Invalid username or password. Exiting the program.");

            sc.close();
            return;
        }

        int choice;

        do {

            System.out.println("\n==============================");
            System.out.println("        MAIN MENU");
            System.out.println("==============================");
            System.out.println("1. Linear Search / Binary Search");
            System.out.println("2. Queue Operations");
            System.out.println("3. Reverse Queue Using Stack");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    searchOperations(sc);
                    break;

                case 2:
                    queueOperations(sc);
                    break;

                case 3:
                    reverseQueue(sc);
                    break;

                case 4:
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}
