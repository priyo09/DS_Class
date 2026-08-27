import java.util.Scanner;  
  
class Dequeue {  
    int dq[];  
    int front, rear, size;  
  
    Dequeue(int size) {  
        this.size = size;  
        dq = new int[size];  
        front = -1;  
        rear = -1;  
    }  
  
    void DqInsFront(int val) {  
        if (front == 0 && rear == size - 1) {  
            System.out.println("Deque Overflow");  
        }  
        else if (front == -1) {  
            front = rear = 0;  
            dq[front] = val;  
        }  
        else if (front == 0) {  
            for (int i = rear; i >= front; i--) {  
                dq[i + 1] = dq[i];  
            }  
            rear++;  
            dq[front] = val;  
        }  
        else {  
            front--;  
            dq[front] = val;  
        }  
    }  
  
    void DqInsRear(int val) {  
        if (front == 0 && rear == size - 1) {  
            System.out.println("Deque Overflow");  
        }  
        else if (front == -1) {  
            front = rear = 0;  
            dq[rear] = val;  
        }  
        else if (rear == size - 1) {  
            for (int i = front; i <= rear; i++) {  
                dq[i - 1] = dq[i];  
            }  
            front--;  
            dq[rear] = val;  
        }  
        else {  
            rear++;  
            dq[rear] = val;  
        }  
    }  
  
    void DqDelFront() {  
        if (front == -1) {  
            System.out.println("Deque Underflow");  
        }  
        else if (front == rear) {  
            front = rear = -1;  
        }  
        else {  
            front++;  
        }  
    }  
  
    void DqDelRear() {  
        if (front == -1) {  
            System.out.println("Deque Underflow");  
        }  
        else if (front == rear) {  
            front = rear = -1;  
        }  
        else {  
            rear--;  
        }  
    }  
  
    void DqDisplay() {  
        if (front == -1) {  
            System.out.println("Deque Empty");  
        }  
        else {  
            System.out.println("Elements of Deque:");  
  
            for (int i = front; i <= rear; i++) {  
                System.out.print(dq[i] + " ");  
            }  
  
            System.out.println();  
        }  
    }  
}  
  
  
public class DequeDemo {  
    public static void main(String args[]) {  
  
        Scanner sc = new Scanner(System.in);  
  
        System.out.print("Enter size: ");  
        int size = sc.nextInt();  
  
        Dequeue d = new Dequeue(size);  
  
        int ch, subch, val;  
  
        do {  
            System.out.println("\n----- DEQUE MENU -----");  
            System.out.println("1. Insert");  
            System.out.println("2. Delete");  
            System.out.println("3. Display");  
            System.out.println("4. Exit");  
  
            System.out.print("Enter your choice: ");  
            ch = sc.nextInt();  
  
            switch (ch) {  
  
                case 1:  
                    System.out.println("\n1. Insert Front");  
                    System.out.println("2. Insert Rear");  
  
                    System.out.print("Enter your choice: ");  
                    subch = sc.nextInt();  
  
                    if (subch == 1 || subch == 2) {  
                        System.out.print("Enter value: ");  
                        val = sc.nextInt();  
  
                        if (subch == 1) {  
                            d.DqInsFront(val);  
                        }  
                        else {  
                            d.DqInsRear(val);  
                        }  
                    }  
                    else {  
                        System.out.println("Invalid Choice");  
                    }  
                    break;  
  
                case 2:  
                    System.out.println("\n1. Delete Front");  
                    System.out.println("2. Delete Rear");  
  
                    System.out.print("Enter your choice: ");  
                    subch = sc.nextInt();  
  
                    if (subch == 1) {  
                        d.DqDelFront();  
                    }  
                    else if (subch == 2) {  
                        d.DqDelRear();  
                    }  
                    else {  
                        System.out.println("Invalid Choice");  
                    }  
                    break;  
  
                case 3:  
                    d.DqDisplay();  
                    break;  
  
                case 4:  
                    System.out.println("Program Ended");  
                    break;  
  
                default:  
                    System.out.println("Invalid Choice");  
            }  
  
        } while (ch != 4);  
  
        sc.close();  
    }  
}