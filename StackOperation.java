import java.util.Scanner;

public class StackOperation {
    static final int MAX = 5;
    static int[] stack = new int[MAX];
    static int top = -1;

    static void push(Scanner sc) {
        if (top == MAX - 1) {
            System.out.println("Stack Overflow!");
        } else {
            System.out.print("Enter element to push: ");
            int item = sc.nextInt();
            top++;
            stack[top] = item;
            System.out.println(item + " pushed into stack.");
        }
    }

    static void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow!");
        } else {
            System.out.println("Popped element: " + stack[top]);
            top--;
        }
    }

    static void display() {
        if (top == -1) {
            System.out.println("Stack is empty.");
        } else {
            System.out.println("Stack elements are:");
            for (int i = top; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n----- STACK MENU -----");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Display");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    push(sc);
                    break;

                case 2:
                    pop();
                    break;

                case 3:
                    display();
                    break;

                case 4:
                    System.out.println("Program Terminated...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}