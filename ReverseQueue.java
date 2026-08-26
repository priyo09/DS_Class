import java.util.Scanner;

public class ReverseQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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

        sc.close();
    }
}
