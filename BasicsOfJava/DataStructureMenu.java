package BasicsOfJava;

import java.util.Scanner;

public class DataStructureMenu {

    static Scanner input = new Scanner(System.in);

    // SEARCHING//

    // Linear Search
    static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

    // Binary Search
    static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }
 //                  STACK USING ARRAY
   static class ArrayStack {

        int[] data;
        int top = -1;

        ArrayStack(int size) {
            data = new int[size];
        }

        void push(int value) {

            if (top == data.length - 1) {
                System.out.println("Stack Overflow!");
                return;
            }

            data[++top] = value;
            System.out.println("Element pushed: " + value);
        }

        void pop() {

            if (top == -1) {
                System.out.println("Stack Underflow!");
                return;
            }

            System.out.println("Element popped: " + data[top--]);
        }

        void display() {

            if (top == -1) {
                System.out.println("Stack is empty.");
                return;
            }

            System.out.println("Stack elements:");

            for (int i = top; i >= 0; i--) {
                System.out.println(data[i]);
            }
        }
    }
    //                  INFIX TO POSTFIX
    static int precedence(char ch) {

        if (ch == '+' || ch == '-')
            return 1;

        if (ch == '*' || ch == '/' || ch == '%')
            return 2;

        if (ch == '^')
            return 3;

        return 0;
    }

    static String infixToPostfix(String expression) {

        char[] stack = new char[expression.length()];
        int top = -1;

        StringBuilder postfix = new StringBuilder();

        for (char ch : expression.toCharArray()) {

            // Operand
            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            }

            // Opening bracket
            else if (ch == '(') {
                stack[++top] = ch;
            }

            // Closing bracket
            else if (ch == ')') {

                while (top >= 0 && stack[top] != '(') {
                    postfix.append(stack[top--]);
                }

                if (top >= 0) {
                    top--;
                }
            }

            // Operator
            else if (ch == '+' || ch == '-' ||
                     ch == '*' || ch == '/' ||
                     ch == '%' || ch == '^') {

                while (top >= 0 &&
                       stack[top] != '(' &&
                       precedence(stack[top]) >= precedence(ch)) {

                    postfix.append(stack[top--]);
                }

                stack[++top] = ch;
            }
        }

        // Remaining operators
        while (top >= 0) {
            postfix.append(stack[top--]);
        }

        return postfix.toString();
    }

    // =========================================================
    //                  POSTFIX EVALUATION
    // =========================================================

    static int evaluatePostfix(String expression) {

        int[] stack = new int[expression.length()];
        int top = -1;

        for (char ch : expression.toCharArray()) {

            // Ignore spaces
            if (ch == ' ')
                continue;

            // Operand
            if (Character.isDigit(ch)) {

                // Prevent array overflow
                if (top == stack.length - 1) {
                    System.out.println("Expression is too large!");
                    return Integer.MIN_VALUE;
                }

                stack[++top] = ch - '0';
            }

            // Operator
            else if (ch == '+' || ch == '-' ||
                     ch == '*' || ch == '/' ||
                     ch == '%' || ch == '^') {

                // Need at least two operands
                if (top < 1) {
                    System.out.println("Invalid postfix expression!");
                    return Integer.MIN_VALUE;
                }

                int b = stack[top--];
                int a = stack[top--];

                switch (ch) {

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
                        if (b == 0) {
                            System.out.println("Cannot divide by zero!");
                            return Integer.MIN_VALUE;
                        }
                        stack[++top] = a / b;
                        break;

                    case '%':
                        if (b == 0) {
                            System.out.println("Cannot divide by zero!");
                            return Integer.MIN_VALUE;
                        }
                        stack[++top] = a % b;
                        break;

                    case '^':
                        stack[++top] = (int) Math.pow(a, b);
                        break;
                }
            }

            else {
                System.out.println("Invalid character: " + ch);
                return Integer.MIN_VALUE;
            }
        }

        // Exactly one value must remain
        if (top != 0) {
            System.out.println("Invalid postfix expression!");
            return Integer.MIN_VALUE;
        }

        return stack[top];
    }

    // =========================================================
    //               STACK APPLICATIONS
    // =========================================================

    // 3(a) Matching Parentheses

    static void matchingParentheses() {

        System.out.print("Enter expression: ");
        String expression = input.nextLine();

        char[] stack = new char[expression.length()];
        int top = -1;

        boolean balanced = true;

        for (char ch : expression.toCharArray()) {

            // Opening brackets
            if (ch == '(' || ch == '[' || ch == '{') {
                stack[++top] = ch;
            }

            // Closing brackets
            else if (ch == ')' || ch == ']' || ch == '}') {

                if (top == -1) {
                    balanced = false;
                    break;
                }

                char open = stack[top--];

                if ((ch == ')' && open != '(') ||
                    (ch == ']' && open != '[') ||
                    (ch == '}' && open != '{')) {

                    balanced = false;
                    break;
                }
            }
        }

        if (top != -1)
            balanced = false;

        if (balanced)
            System.out.println("Parentheses are Balanced.");
        else
            System.out.println("Parentheses are Not Balanced.");
    }

    // 3(b) Evaluation of Expression

    static void expressionEvaluation() {

        System.out.print("Enter postfix expression: ");
        String expression = input.nextLine();

        int result = evaluatePostfix(expression);

        if (result != Integer.MIN_VALUE) {
            System.out.println("Result = " + result);
        }
    }

    // 3(c) Stack used in Function Call

    static void functionCallDemo() {

        System.out.println("\n--- Function Call Stack ---");

        System.out.println("main() called");

        firstFunction();

        System.out.println("main() resumed");
    }

    static void firstFunction() {

        System.out.println("firstFunction() called");

        secondFunction();

        System.out.println("firstFunction() resumed");
    }

    static void secondFunction() {

        System.out.println("secondFunction() called");
        System.out.println("secondFunction() completed");
    }

    // 3(d) Stack used in Recursion

    static void recursion(int n) {

        if (n == 0)
            return;

        System.out.println("Calling function with n = " + n);

        recursion(n - 1);

        System.out.println("Returning from n = " + n);
    }

    static void recursionDemo() {

        System.out.print("Enter number: ");
        int n = input.nextInt();
        input.nextLine();

        if (n < 0) {
            System.out.println("Please enter a positive number.");
            return;
        }

        System.out.println("\n--- Recursion Stack ---");

        recursion(n);

        System.out.println("Recursion completed.");
    }

    // =========================================================
    //                    SEARCH MENU
    // =========================================================

    static void searchMenu() {

        int choice;

        do {

            System.out.println("\n========== SEARCH ==========");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter number of elements: ");
                    int n1 = input.nextInt();

                    int[] arr1 = new int[n1];

                    System.out.println("Enter elements:");

                    for (int i = 0; i < n1; i++) {
                        arr1[i] = input.nextInt();
                    }

                    System.out.print("Enter element to search: ");
                    int key1 = input.nextInt();

                    int result1 = linearSearch(arr1, key1);

                    if (result1 != -1)
                        System.out.println(
                                "Element found at position " + (result1 + 1));
                    else
                        System.out.println("Element not found.");

                    break;

                case 2:

                    System.out.print("Enter number of elements: ");
                    int n2 = input.nextInt();

                    int[] arr2 = new int[n2];

                    System.out.println(
                            "Enter elements in ascending sorted order:");

                    for (int i = 0; i < n2; i++) {
                        arr2[i] = input.nextInt();
                    }

                    System.out.print("Enter element to search: ");
                    int key2 = input.nextInt();

                    int result2 = binarySearch(arr2, key2);

                    if (result2 != -1)
                        System.out.println(
                                "Element found at position " + (result2 + 1));
                    else
                        System.out.println("Element not found.");

                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);
    }

    // =========================================================
    //                    STACK MENU
    // =========================================================

    static void stackMenu() {

        int choice;
        String postfix = "";

        do {

            System.out.println("\n======= STACK MENU =======");
            System.out.println("1. Stack Using Array");
            System.out.println("2. Stack Using Linked List (Coming Soon)");
            System.out.println("3. Infix to Postfix");
            System.out.println("4. Evaluation of Postfix");
            System.out.println("5. Stack Applications");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter stack size: ");
                    int size = input.nextInt();

                    if (size <= 0) {
                        System.out.println("Invalid stack size.");
                        break;
                    }

                    ArrayStack stack = new ArrayStack(size);

                    int operation;

                    do {

                        System.out.println("\n------ ARRAY STACK ------");
                        System.out.println("1. Push");
                        System.out.println("2. Pop");
                        System.out.println("3. Display");
                        System.out.println("4. Back");
                        System.out.print("Enter choice: ");

                        operation = input.nextInt();

                        switch (operation) {

                            case 1:
                                System.out.print("Enter element: ");
                                int value = input.nextInt();
                                stack.push(value);
                                break;

                            case 2:
                                stack.pop();
                                break;

                            case 3:
                                stack.display();
                                break;

                            case 4:
                                break;

                            default:
                                System.out.println("Invalid choice!");
                        }

                    } while (operation != 4);

                    break;

                case 2:

                    System.out.println(
                            "Stack using Linked List - Coming Soon!");

                    break;

                case 3:

                    System.out.print("Enter infix expression: ");
                    String infix = input.nextLine();

                    postfix = infixToPostfix(infix);

                    System.out.println(
                            "Postfix expression: " + postfix);

                    break;

                case 4:

                    if (postfix.isEmpty()) {

                        System.out.println(
                                "First convert an infix expression to postfix.");

                    } else {

                        System.out.println(
                                "Postfix expression: " + postfix);

                        int answer = evaluatePostfix(postfix);

                        if (answer != Integer.MIN_VALUE)
                            System.out.println("Result = " + answer);
                    }

                    break;

                case 5:

                    stackApplicationMenu();

                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    // =========================================================
    //                 STACK APPLICATION MENU
    // =========================================================

    static void stackApplicationMenu() {

        int choice;

        do {

            System.out.println("\n===== STACK APPLICATIONS =====");
            System.out.println("1. Matching of Parentheses");
            System.out.println("2. Evaluation of Expression");
            System.out.println("3. Stack in Function Call");
            System.out.println("4. Stack in Recursion");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    matchingParentheses();
                    break;

                case 2:
                    expressionEvaluation();
                    break;

                case 3:
                    functionCallDemo();
                    break;

                case 4:
                    recursionDemo();
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // =========================================================
    //                       MAIN
    // =========================================================

    public static void main(String[] args) {

        System.out.println("========== LOGIN ==========");

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password (Regd No.): ");
        String password = input.nextLine();

        int choice;

        do {

            System.out.println("\n==============================");
            System.out.println("       DATA STRUCTURE MENU");
            System.out.println("==============================");
            System.out.println("1. Search");
            System.out.println("2. Stack");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    searchMenu();
                    break;

                case 2:
                    stackMenu();
                    break;

                case 3:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);

        input.close();
    }
}