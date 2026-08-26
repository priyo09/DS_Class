public class Queue_imp {

    int size;
    int front;
    int rear;
    int[] queue;

    // Constructor
    public Queue_imp(int capacity) {
        size = capacity;
        queue = new int[size];
        front = -1;
        rear = -1;
    }
    public void qinsert(int val) {

        if (rear == size - 1) {
            System.out.println("Overflow");
        }
        else {

            if (front == -1 && rear == -1) {
                front = 0;
                rear = 0;
                queue[rear] = val;
            }
            else {
                rear++;
                queue[rear] = val;
            }
        }
    }
    public void qdelete() {

        if (front == -1) {
            System.out.println("Underflow");
        }
        else {

            if (front == rear) {
                System.out.println("Deleted " + queue[front]);
                front = -1;
                rear = -1;
            }
            else {
                System.out.println("Deleted " + queue[front]);
                front++;
            }
        }
    }

    // Display
    public void qdisplay() {

        if (front == -1) {
            System.out.println("Queue is empty");
        }
        else {
            System.out.println("Queue elements are:");

            for (int i = front; i <= rear; i++) {
                System.out.println(queue[i]);
            }
        }
    }
}