import java.util.Scanner;

class Queue {
    int[] queue;
    int front, rear, size;

    Queue(int size) {
        this.size = size;
        queue = new int[size];
        front = -1;
        rear = -1;
    }
    void enqueue(int value) {
        if ((rear + 1) % size == front) {
            System.out.println("Queue Overflow");
            return;
        }
        if (front == -1) {
            front = 0;
        }

        rear = (rear + 1) % size;
        queue[rear] = value;

        System.out.println(value + " inserted");
    }
    void dequeue() {

        // Queue is empty
        if (front == -1) {
            System.out.println("Queue Underflow");
            return;
        }

        System.out.println(queue[front] + " deleted");

        // Only one element is present
        if (front == rear) {
            front = -1;
            rear = -1;
        }
        else {
            front = (front + 1) % size;
        }
    }

    // Display elements
    void display() {

        if (front == -1) {
            System.out.println("Queue is Empty");
            return;
        }

        System.out.print("Queue: ");

        int i = front;

        while (true) {
            System.out.print(queue[i] + " ");

            if (i == rear)
                break;

            i = (i + 1) % size;
        }

        System.out.println();
    }
}

public class QueueStack {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter queue size: ");
        int size = sc.nextInt();

        Queue q = new Queue(size);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);

        q.display();

        q.dequeue();
        q.dequeue();

        q.display();

        q.enqueue(50);
        q.enqueue(60);

        q.display();

        sc.close();
    }
}