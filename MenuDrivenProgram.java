import java.util.Scanner;

public class MenuDrivenProgram {

    // ==============
    // SEARCH METHODS
    // ==============

    static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

    static int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    // ======
    // STACK
    // ======

    static class Stack {
        int[] stack;
        int top = -1;

        Stack(int size) {
            stack = new int[size];
        }

        void push(int value) {
            if (top == stack.length - 1) {
                System.out.println("Stack Overflow!");
            } else {
                stack[++top] = value;
            }
        }

        int pop() {
            if (top == -1) {
                System.out.println("Stack Underflow!");
                return -1;
            }

            return stack[top--];
        }

        boolean isEmpty() {
            return top == -1;
        }

        void display() {
            if (top == -1) {
                System.out.println("Stack is empty.");
                return;
            }

            System.out.println("Stack elements:");

            for (int i = top; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }
    }

    // =================
    // INFIX TO POSTFIX
    // =================

    static int precedence(char ch) {
        switch (ch) {
            case '^':
                return 3;

            case '*':
            case '/':
            case '%':
                return 2;

            case '+':
            case '-':
                return 1;

            default:
                return -1;
        }
    }

    static String infixToPostfix(String infix) {

        char[] stack = new char[infix.length()];
        int top = -1;

        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {

            char ch = infix.charAt(i);

            if (Character.isWhitespace(ch))
                continue;

            if (Character.isDigit(ch) || ch == '.') {

                while (i < infix.length()
                        && (Character.isDigit(infix.charAt(i))
                        || infix.charAt(i) == '.')) {

                    postfix.append(infix.charAt(i));
                    i++;
                }

                postfix.append(" ");
                i--;
            }

            else if (ch == '(') {
                stack[++top] = ch;
            }

            else if (ch == ')') {

                while (top != -1 && stack[top] != '(') {
                    postfix.append(stack[top--]).append(" ");
                }

                if (top != -1)
                    top--;
            }

            else {

                while (top != -1
                        && stack[top] != '('
                        && precedence(ch) <= precedence(stack[top])) {

                    postfix.append(stack[top--]).append(" ");
                }

                stack[++top] = ch;
            }
        }

        while (top != -1) {
            postfix.append(stack[top--]).append(" ");
        }

        return postfix.toString().trim();
    }

    // ==================
    // POSTFIX EVALUATION
    // ==================

    static double evaluatePostfix(String postfix) {

        String[] tokens = postfix.split("\\s+");

        double[] stack = new double[tokens.length];
        int top = -1;

        for (String token : tokens) {

            if (token.matches("\\d+(\\.\\d+)?")) {

                stack[++top] = Double.parseDouble(token);

            } else {

                double b = stack[top--];
                double a = stack[top--];

                switch (token.charAt(0)) {

                    case '+':
                        stack[++top] = a + b;
                        break;

                    case '-':
                        stack[++top] = a - b;
                        break;

                    case '*':
                        stack[++top] = a * b;
                        break;

                    case '/':
                        stack[++top] = a / b;
                        break;

                    case '%':
                        stack[++top] = a % b;
                        break;

                    case '^':
                        stack[++top] = Math.pow(a, b);
                        break;
                }
            }
        }

        return stack[top];
    }

    // =======
    // QUEUE
    // =======

    static class Queue {

        int[] queue;
        int front;
        int rear;
        int size;

        Queue(int size) {
            this.size = size;
            queue = new int[size];

            front = -1;
            rear = -1;
        }

        // Insert
        void enqueue(int value) {

            if (rear == size - 1) {
                System.out.println("Queue Overflow!");
                return;
            }

            if (front == -1)
                front = 0;

            queue[++rear] = value;

            System.out.println(value + " inserted");
        }

        // Delete
        void dequeue() {

            if (front == -1 || front > rear) {
                System.out.println("Queue Underflow!");
                return;
            }

            System.out.println(queue[front] + " deleted");

            front++;

            if (front > rear) {
                front = -1;
                rear = -1;
            }
        }

        // Display
        void display() {

            if (front == -1) {
                System.out.println("Queue is empty");
                return;
            }

            System.out.print("Queue: ");

            for (int i = front; i <= rear; i++) {
                System.out.print(queue[i] + " ");
            }

            System.out.println();
        }

        // Reverse Queue using Stack
        void reverse() {

            if (front == -1) {
                System.out.println("Queue is empty");
                return;
            }

            Stack s = new Stack(size);

            // Push queue elements into stack
            for (int i = front; i <= rear; i++) {
                s.push(queue[i]);
            }

            // Pop stack elements and put them back into queue
            for (int i = front; i <= rear; i++) {
                queue[i] = s.pop();
            }

            System.out.println("Queue reversed successfully.");

            display();
        }
    }

    // ================
    // MAIN SEARCH MENU
    // ================

    static void searchMenu(Scanner sc) {

        int choice;

        do {

            System.out.println("\n========== SEARCH ==========");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter number of elements: ");
                    int n1 = sc.nextInt();

                    int[] arr1 = new int[n1];

                    System.out.println("Enter " + n1 + " elements:");

                    for (int i = 0; i < n1; i++) {
                        arr1[i] = sc.nextInt();
                    }

                    System.out.print("Enter element to search: ");
                    int key1 = sc.nextInt();

                    int result1 = linearSearch(arr1, key1);

                    if (result1 != -1)
                        System.out.println(
                                "Element found at position "
                                        + (result1 + 1));
                    else
                        System.out.println("Element not found.");

                    break;

                case 2:

                    System.out.print("Enter number of elements: ");
                    int n2 = sc.nextInt();

                    int[] arr2 = new int[n2];

                    System.out.println(
                            "Enter elements in sorted ascending order:");

                    for (int i = 0; i < n2; i++) {
                        arr2[i] = sc.nextInt();
                    }

                    System.out.print("Enter element to search: ");
                    int key2 = sc.nextInt();

                    int result2 = binarySearch(arr2, key2);

                    if (result2 != -1)
                        System.out.println(
                                "Element found at position "
                                        + (result2 + 1));
                    else
                        System.out.println("Element not found.");

                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 3);
    }

    // ======================
    // STACK OPERATIONS MENU
    // ======================

    static void stackOperationsMenu(Scanner sc) {

        int choice;
        String postfix = "";

        do {

            System.out.println("\n====== STACK OPERATIONS ======");
            System.out.println("1. Implementation of Stack using Array");
            System.out.println("2. Conversion of Infix to Postfix");
            System.out.println("3. Evaluation of Postfix Expression");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter stack size: ");
                    int size = sc.nextInt();

                    Stack s = new Stack(size);

                    int stackChoice;

                    do {

                        System.out.println("\n===== STACK =====");
                        System.out.println("1. Push");
                        System.out.println("2. Pop");
                        System.out.println("3. Display");
                        System.out.println("4. Back");
                        System.out.print("Enter your choice: ");

                        stackChoice = sc.nextInt();

                        switch (stackChoice) {

                            case 1:
                                System.out.print("Enter element: ");
                                s.push(sc.nextInt());
                                break;

                            case 2:

                                int popped = s.pop();

                                if (popped != -1)
                                    System.out.println(
                                            "Element popped: " + popped);

                                break;

                            case 3:
                                s.display();
                                break;

                            case 4:
                                break;

                            default:
                                System.out.println("Invalid Choice!");
                        }

                    } while (stackChoice != 4);

                    break;

                case 2:

                    System.out.print("Enter infix expression: ");
                    String infix = sc.nextLine();

                    postfix = infixToPostfix(infix);

                    System.out.println(
                            "Postfix expression: " + postfix);

                    break;

                case 3:

                    if (postfix.isEmpty()) {

                        System.out.println(
                                "First convert an infix expression "
                                        + "to postfix.");

                    } else {

                        System.out.println(
                                "Postfix expression: " + postfix);

                        double result = evaluatePostfix(postfix);

                        System.out.println("Result: " + result);
                    }

                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);
    }

    // ======================
    // QUEUE OPERATIONS MENU
    // ======================

    static void queueOperationsMenu(Scanner sc) {

        int choice;

        do {

            System.out.println("\n====== QUEUE OPERATIONS ======");
            System.out.println("1. Implement Queue using Array");
            System.out.println("2. Reverse Queue using Stack");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter queue size: ");
                    int size = sc.nextInt();

                    Queue q = new Queue(size);

                    int queueChoice;

                    do {

                        System.out.println("\n===== QUEUE =====");
                        System.out.println("1. Insert");
                        System.out.println("2. Delete");
                        System.out.println("3. Display");
                        System.out.println("4. Back");
                        System.out.print("Enter choice: ");

                        queueChoice = sc.nextInt();

                        switch (queueChoice) {

                            case 1:
                                System.out.print("Enter value: ");
                                q.enqueue(sc.nextInt());
                                break;

                            case 2:
                                q.dequeue();
                                break;

                            case 3:
                                q.display();
                                break;

                            case 4:
                                break;

                            default:
                                System.out.println("Invalid choice");
                        }

                    } while (queueChoice != 4);

                    break;

                case 2:

                    System.out.print("Enter queue size: ");
                    int reverseSize = sc.nextInt();

                    Queue reverseQueue = new Queue(reverseSize);

                    System.out.print(
                            "Enter number of elements: ");
                    int n = sc.nextInt();

                    System.out.println("Enter queue elements:");

                    for (int i = 0; i < n; i++) {
                        reverseQueue.enqueue(sc.nextInt());
                    }

                    System.out.println("\nOriginal Queue:");
                    reverseQueue.display();

                    reverseQueue.reverse();

                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 3);
    }

    // ============
    // MAIN METHOD
    // ============

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password (Regd No.): ");
        String password = sc.nextLine();

        int choice;

        do {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Search");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    searchMenu(sc);
                    break;

                case 2:
                    stackOperationsMenu(sc);
                    break;

                case 3:
                    queueOperationsMenu(sc);
                    break;

                case 4:
                    System.out.println("Program ended.");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}