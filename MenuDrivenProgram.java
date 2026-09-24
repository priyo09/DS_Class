import java.util.Scanner;

public class MenuDrivenProgram {

    private static final String VALID_USERNAME = "Priyantan";
    private static final String VALID_PASSWORD = "250301120275";

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
        boolean singleInsertionMode;

        Queue(int size) {
            this(size, false);
        }

        Queue(int size, boolean singleInsertionMode) {
            this.size = size;
            this.singleInsertionMode = singleInsertionMode;
            queue = new int[size];

            front = -1;
            rear = -1;
        }

        // Insert
        void enqueue(int value) {

            if ((singleInsertionMode && front != -1) || rear == size - 1) {
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

    // =====
    // DEQUE
    // =====

    static class Deque {
        private final int[] elements;
        private int front = -1;
        private int rear = -1;
        private int count = 0;

        Deque(int capacity) {
            elements = new int[capacity];
        }

        boolean insertFront(int value) {
            if (count == elements.length) {
                System.out.println("Deque Overflow!");
                return false;
            }

            if (count == 0) {
                front = rear = 0;
            } else {
                front = (front - 1 + elements.length) % elements.length;
            }

            elements[front] = value;
            count++;
            System.out.println(value + " inserted at front");
            return true;
        }

        boolean insertRear(int value) {
            if (count == elements.length) {
                System.out.println("Deque Overflow!");
                return false;
            }

            if (count == 0) {
                front = rear = 0;
            } else {
                rear = (rear + 1) % elements.length;
            }

            elements[rear] = value;
            count++;
            System.out.println(value + " inserted at rear");
            return true;
        }

        void deleteFront() {
            if (count == 0) {
                System.out.println("Deque Underflow!");
                return;
            }

            System.out.println(elements[front] + " deleted from front");
            front = (front + 1) % elements.length;
            count--;

            if (count == 0) {
                front = rear = -1;
            }
        }

        void deleteRear() {
            if (count == 0) {
                System.out.println("Deque Underflow!");
                return;
            }

            System.out.println(elements[rear] + " deleted from rear");
            rear = (rear - 1 + elements.length) % elements.length;
            count--;

            if (count == 0) {
                front = rear = -1;
            }
        }

        void display() {
            if (count == 0) {
                System.out.println("Deque is empty.");
                return;
            }

            System.out.print("Deque: ");
            for (int i = 0; i < count; i++) {
                System.out.print(elements[(front + i) % elements.length] + " ");
            }
            System.out.println();
        }
    }

    static class CircularQueue {
        private final int[] elements;
        private int front = 0;
        private int rear = -1;
        private int count = 0;

        CircularQueue(int capacity) {
            elements = new int[capacity];
        }

        void enqueue(int value) {
            if (count == elements.length) {
                System.out.println("Circular Queue Overflow!");
                return;
            }

            rear = (rear + 1) % elements.length;
            elements[rear] = value;
            count++;
            System.out.println(value + " inserted");
        }

        void dequeue() {
            if (count == 0) {
                System.out.println("Circular Queue Underflow!");
                return;
            }

            System.out.println(elements[front] + " deleted");
            front = (front + 1) % elements.length;
            count--;
        }

        void display() {
            if (count == 0) {
                System.out.println("Circular Queue is empty.");
                return;
            }

            System.out.print("Circular Queue: ");
            for (int i = 0; i < count; i++) {
                System.out.print(elements[(front + i) % elements.length] + " ");
            }
            System.out.println();
        }
    }

    static class PriorityQueue {
        private final int[] elements;
        private int count = 0;

        PriorityQueue(int capacity) {
            elements = new int[capacity];
        }

        void enqueue(int value) {
            if (count == elements.length) {
                System.out.println("Priority Queue Overflow!");
                return;
            }

            int i = count - 1;
            while (i >= 0 && elements[i] > value) {
                elements[i + 1] = elements[i];
                i--;
            }
            elements[i + 1] = value;
            count++;
            System.out.println(value + " inserted");
        }

        void dequeue() {
            if (count == 0) {
                System.out.println("Priority Queue Underflow!");
                return;
            }

            System.out.println(elements[0] + " deleted (highest priority)");
            for (int i = 1; i < count; i++) {
                elements[i - 1] = elements[i];
            }
            count--;
        }

        void display() {
            if (count == 0) {
                System.out.println("Priority Queue is empty.");
                return;
            }

            System.out.print("Priority Queue (lowest value = highest priority): ");
            for (int i = 0; i < count; i++) {
                System.out.print(elements[i] + " ");
            }
            System.out.println();
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
            System.out.println("3. Deque Operations");
            System.out.println("4. Back to Main Menu");
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
                    dequeOperationsMenu(sc);
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);
    }

    // ======================
    // DEQUE OPERATIONS MENU
    // ======================

    static void dequeOperationsMenu(Scanner sc) {
        System.out.print("Enter deque size: ");
        int size = sc.nextInt();

        if (size <= 0) {
            System.out.println("Deque size must be greater than zero.");
            return;
        }

        Deque deque = new Deque(size);
        int choice;

        do {
            System.out.println("\n===== DEQUE =====");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Display");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("1. Insert Front");
                    System.out.println("2. Insert Rear");
                    System.out.print("Enter your choice: ");
                    int insertChoice = sc.nextInt();

                    if (insertChoice == 1 || insertChoice == 2) {
                        System.out.print("Enter value: ");
                        int value = sc.nextInt();
                        if (insertChoice == 1) {
                            deque.insertFront(value);
                        } else {
                            deque.insertRear(value);
                        }
                    } else {
                        System.out.println("Invalid Choice!");
                    }
                    break;

                case 2:
                    System.out.println("1. Delete Front");
                    System.out.println("2. Delete Rear");
                    System.out.print("Enter your choice: ");
                    int deleteChoice = sc.nextInt();

                    if (deleteChoice == 1) {
                        deque.deleteFront();
                    } else if (deleteChoice == 2) {
                        deque.deleteRear();
                    } else {
                        System.out.println("Invalid Choice!");
                    }
                    break;

                case 3:
                    deque.display();
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 4);
    }

    static void linearSearchOperation(Scanner sc) {
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter element to search: ");
        int result = linearSearch(arr, sc.nextInt());
        System.out.println(result == -1 ? "Element not found."
                : "Element found at position " + (result + 1));
    }

    static void binarySearchOperation(Scanner sc) {
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter elements in sorted ascending order:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter element to search: ");
        int result = binarySearch(arr, sc.nextInt());
        System.out.println(result == -1 ? "Element not found."
                : "Element found at position " + (result + 1));
    }

    static Stack getMainStack(Scanner sc, Stack stack) {
        if (stack != null) {
            return stack;
        }

        System.out.print("Enter stack size: ");
        int size = sc.nextInt();
        if (size <= 0) {
            System.out.println("Stack size must be greater than zero.");
            return null;
        }
        return new Stack(size);
    }

    static Stack pushStackOperation(Scanner sc, Stack stack) {
        stack = getMainStack(sc, stack);
        if (stack != null) {
            System.out.print("Enter element: ");
            stack.push(sc.nextInt());
        }
        return stack;
    }

    static Stack popStackOperation(Scanner sc, Stack stack) {
        if (stack == null) {
            System.out.println("Stack is not initialized. Select 2.1.1 first.");
            return null;
        }

        int popped = stack.pop();
        if (popped != -1) {
            System.out.println("Element popped: " + popped);
        }
        return stack;
    }

    static void displayStackOperation(Stack stack) {
        if (stack == null) {
            System.out.println("Stack is not initialized. Select 2.1.1 first.");
        } else {
            stack.display();
        }
    }

    static void linearQueueMenu(Scanner sc) {
        System.out.print("Enter queue size: ");
        int size = sc.nextInt();
        if (size <= 0) {
            System.out.println("Queue size must be greater than zero.");
            return;
        }

        Queue queue = new Queue(size, true);
        int choice;
        do {
            System.out.println("\n===== LINEAR QUEUE =====");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Display");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    queue.enqueue(sc.nextInt());
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.display();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 4);
    }

    static void circularQueueMenu(Scanner sc) {
        System.out.print("Enter circular queue size: ");
        int size = sc.nextInt();
        if (size <= 0) {
            System.out.println("Queue size must be greater than zero.");
            return;
        }

        CircularQueue queue = new CircularQueue(size);
        queueMenu(sc, queue);
    }

    static void priorityQueueMenu(Scanner sc) {
        System.out.print("Enter priority queue size: ");
        int size = sc.nextInt();
        if (size <= 0) {
            System.out.println("Queue size must be greater than zero.");
            return;
        }

        PriorityQueue queue = new PriorityQueue(size);
        int choice;
        do {
            System.out.println("\n===== PRIORITY QUEUE =====");
            System.out.println("1. Insert");
            System.out.println("2. Delete Highest Priority");
            System.out.println("3. Display");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value (smaller value = higher priority): ");
                    queue.enqueue(sc.nextInt());
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.display();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 4);
    }

    static void queueMenu(Scanner sc, CircularQueue queue) {
        int choice;
        do {
            System.out.println("\n===== CIRCULAR QUEUE =====");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Display");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    queue.enqueue(sc.nextInt());
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.display();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 4);
    }

    // ==========================
    // DOUBLY LINKED LIST
    // ==========================

    static class SinglyLinkedList {
        private static class Node {
            int data;
            Node next;
            Node(int data) { this.data = data; }
        }

        private static class List {
            Node head;

            void create(Scanner sc) {
                System.out.print("Enter number of nodes: ");
                int n = sc.nextInt();
                if (n < 0) { System.out.println("Number of nodes cannot be negative."); return; }
                head = null;
                Node tail = null;
                for (int i = 1; i <= n; i++) {
                    System.out.print("Enter data for node " + i + ": ");
                    Node node = new Node(sc.nextInt());
                    if (head == null) head = node;
                    else tail.next = node;
                    tail = node;
                }
                System.out.println("List created successfully.");
            }

            void display() {
                if (head == null) { System.out.println("List is empty."); return; }
                for (Node node = head; node != null; node = node.next) System.out.print(node.data + " ---> ");
                System.out.println("NULL");
            }

            void append(List other) {
                if (other.head == null) return;
                if (head == null) { head = other.head; return; }
                Node tail = head;
                while (tail.next != null) tail = tail.next;
                tail.next = other.head;
            }
        }

        private final List first = new List();
        private final List second = new List();
        private boolean combined;

        void menu(Scanner sc) {
            int choice;
            do {
                System.out.println("\n===== SINGLY LINKED LIST =====");
                System.out.println("1. Create first list\n2. Display first list\n3. Create second list\n4. Display second list\n5. Combine lists\n0. Back");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1: first.create(sc); combined = false; break;
                    case 2: first.display(); break;
                    case 3: second.create(sc); combined = false; break;
                    case 4: second.display(); break;
                    case 5:
                        if (combined) System.out.println("The lists have already been combined.");
                        else {
                            first.append(second);
                            combined = true;
                            System.out.println("Combined list:");
                            first.display();
                        }
                        break;
                    case 0: break;
                    default: System.out.println("Invalid choice.");
                }
            } while (choice != 0);
        }
    }

    static class DoublyLinkedList {
        private static class Node {
            int data;
            Node prev;
            Node next;

            Node(int data) {
                this.data = data;
            }
        }

        private Node head;
        private Node tail;
        private boolean circular;

        void create(Scanner sc) {
            System.out.print("Enter the number of nodes to create: ");
            int n = sc.nextInt();
            if (n < 0) {
                System.out.println("Number of nodes cannot be negative.");
                return;
            }
            head = tail = null;
            circular = false;
            for (int i = 1; i <= n; i++) {
                System.out.print("Enter data for node " + i + ": ");
                insertEnd(sc.nextInt());
            }
            System.out.println("Doubly linked list created.");
        }

        private void insertBeg(int value) {
            Node node = new Node(value);
            if (head == null) head = tail = node;
            else {
                node.next = head;
                head.prev = node;
                head = node;
            }
            relinkCircular();
        }

        private void insertEnd(int value) {
            Node node = new Node(value);
            if (tail == null) head = tail = node;
            else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            relinkCircular();
        }

        private void insertAt(Scanner sc) {
            System.out.print("Enter position (1-based): ");
            int pos = sc.nextInt();
            if (pos <= 1 || head == null) {
                System.out.print("Enter value: ");
                insertBeg(sc.nextInt());
                return;
            }
            Node current = head;
            for (int i = 1; i < pos - 1 && current.next != null; i++) current = current.next;
            System.out.print("Enter value: ");
            Node node = new Node(sc.nextInt());
            node.prev = current;
            node.next = current.next;
            if (current.next == null) tail = node;
            else current.next.prev = node;
            current.next = node;
            relinkCircular();
        }

        void delete(Scanner sc) {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            System.out.println("1. Delete beginning  2. Delete end  3. Delete position");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            Node node;
            if (choice == 1) node = head;
            else if (choice == 2) node = tail;
            else if (choice == 3) {
                System.out.print("Enter position (1-based): ");
                int pos = sc.nextInt();
                if (pos < 1) { System.out.println("Invalid position."); return; }
                node = head;
                for (int i = 1; i < pos && node != null; i++) node = node.next;
                if (node == null) { System.out.println("Position out of bounds."); return; }
            } else { System.out.println("Invalid choice."); return; }
            if (node.prev == null) head = node.next;
            else node.prev.next = node.next;
            if (node.next == null) tail = node.prev;
            else node.next.prev = node.prev;
            if (head == null) circular = false;
            relinkCircular();
            System.out.println("Deleted node with value: " + node.data);
        }

        void display() {
            if (head == null) { System.out.println("List is empty."); return; }
            Node current = head;
            System.out.print("null <-> ");
            while (current != null) {
                System.out.print(current.data + " <-> ");
                if (current == tail) break;
                current = current.next;
            }
            System.out.println(circular ? "(back to head)" : "null");
        }

        void makeCircular() {
            if (head == null) { System.out.println("List is empty."); return; }
            circular = true;
            relinkCircular();
            System.out.println("List converted to a doubly circular list.");
        }

        void count() {
            int count = 0;
            for (Node current = head; current != null; current = current.next) {
                count++;
                if (current == tail) break;
            }
            System.out.println("Number of nodes: " + count);
        }

        void displayAlternate() {
            if (head == null) { System.out.println("List is empty."); return; }
            System.out.print("Alternate nodes: ");
            for (Node current = head; current != null; current = current.next == null || current == tail ? null : current.next.next)
                System.out.print(current.data + " ");
            System.out.println();
        }

        private void relinkCircular() {
            if (head != null) {
                head.prev = circular ? tail : null;
                tail.next = circular ? head : null;
            }
        }

        void menu(Scanner sc) {
            int choice;
            do {
                System.out.println("\n===== DOUBLY LINKED LIST =====");
                System.out.println("1. Create list\n2. Display\n3. Insert\n4. Delete\n5. Make circular\n6. Count nodes\n7. Display alternate nodes\n0. Back");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1: create(sc); break;
                    case 2: display(); break;
                    case 3:
                        System.out.println("1. Beginning  2. End  3. Position");
                        System.out.print("Enter choice: ");
                        int insertChoice = sc.nextInt();
                        System.out.print("Enter value: ");
                        int value = sc.nextInt();
                        if (insertChoice == 1) insertBeg(value);
                        else if (insertChoice == 2) insertEnd(value);
                        else if (insertChoice == 3) {
                            // Reuse positional insertion while keeping input order consistent.
                            System.out.print("Enter position (1-based): ");
                            int pos = sc.nextInt();
                            if (pos <= 1 || head == null) insertBeg(value);
                            else {
                                Node current = head;
                                for (int i = 1; i < pos - 1 && current.next != null; i++) current = current.next;
                                Node node = new Node(value);
                                node.prev = current; node.next = current.next;
                                if (current.next == null) tail = node; else current.next.prev = node;
                                current.next = node; relinkCircular();
                            }
                        } else System.out.println("Invalid choice.");
                        break;
                    case 4: delete(sc); break;
                    case 5: makeCircular(); break;
                    case 6: count(); break;
                    case 7: displayAlternate(); break;
                    case 0: break;
                    default: System.out.println("Invalid choice.");
                }
            } while (choice != 0);
        }
    }

    // ============
    // MAIN METHOD
    // ============

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean authenticated = false;

        while (!authenticated) {
            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password (Regd No.): ");
            String password = sc.nextLine();

            if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
                authenticated = true;
            } else {
                System.out.println("Invalid credentials! Please try again.\n");
            }
        }

        System.out.println("Login successful.");

        String choice;
        Stack mainStack = null;
        String mainPostfix = "";
        DoublyLinkedList dll = new DoublyLinkedList();
        SinglyLinkedList sll = new SinglyLinkedList();

        do {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Search");
            System.out.println("   1.1 Linear Search");
            System.out.println("   1.2 Binary Search");
            System.out.println("2. Stack Operations");
            System.out.println("   2.1 Stack using Array");
            System.out.println("       2.1.1 Push");
            System.out.println("       2.1.2 Pop");
            System.out.println("       2.1.3 Display");
            System.out.println("   2.2 Infix to Postfix Conversion");
            System.out.println("   2.3 Postfix Expression Evaluation");
            System.out.println("3. Queue Operations");
            System.out.println("   3.1 Linear Queue");
            System.out.println("   3.2 Circular Queue");
            System.out.println("   3.3 Priority Queue");
            System.out.println("   3.4 Deque");
            System.out.println("4. Linked List");
            System.out.println("   4.1 Singly Linked List");
            System.out.println("   4.2 Doubly Linked List");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (for example, 1.1): ");

            do {
                choice = sc.nextLine().trim();
            } while (choice.isEmpty());

            switch (choice) {

                case "1":
                    // A plain 1 is accepted as the first Search operation.
                    linearSearchOperation(sc);
                    break;

                case "1.1":
                    linearSearchOperation(sc);
                    break;

                case "1.2":
                    binarySearchOperation(sc);
                    break;

                case "2":
                    stackOperationsMenu(sc);
                    break;

                case "2.1":
                    stackOperationsMenu(sc);
                    break;

                case "2.1.1":
                    mainStack = pushStackOperation(sc, mainStack);
                    break;

                case "2.1.2":
                    mainStack = popStackOperation(sc, mainStack);
                    break;

                case "2.1.3":
                    displayStackOperation(mainStack);
                    break;

                case "2.2":
                    System.out.print("Enter infix expression: ");
                    mainPostfix = infixToPostfix(sc.nextLine());
                    System.out.println("Postfix expression: " + mainPostfix);
                    break;

                case "2.3":
                    if (mainPostfix.isEmpty()) {
                        System.out.println("First convert an infix expression to postfix using 2.2.");
                    } else {
                        System.out.println("Postfix expression: " + mainPostfix);
                        System.out.println("Result: " + evaluatePostfix(mainPostfix));
                    }
                    break;

                case "3.1":
                    linearQueueMenu(sc);
                    break;

                case "3.2":
                    circularQueueMenu(sc);
                    break;

                case "3.3":
                    priorityQueueMenu(sc);
                    break;

                case "3.4":
                    dequeOperationsMenu(sc);
                    break;

                case "3":
                    // A plain 3 is accepted as the first Queue operation.
                    linearQueueMenu(sc);
                    break;

                case "4":
                case "4.1":
                    sll.menu(sc);
                    break;

                case "4.2":
                    dll.menu(sc);
                    break;

                case "5":
                    System.out.println("Program ended.");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (!choice.equals("5"));

        sc.close();
    }
}
