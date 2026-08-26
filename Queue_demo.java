import java.util.Scanner;

public class Queue_demo {

    public static void main(String[] args) {

     
        Queue_imp q = new Queue_imp(5);

        int ch, val;
        char choice;
        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("QUEUE OPERATIONS1");
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

                    q.qinsert(val);
                    break;

                case 2:
                    q.qdelete();
                    break;

                case 3:
                    q.qdisplay();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("OUTSIDE CASE VALUE");
            }

            System.out.println("DO YOU WANT TO CONTINUE [Y/N]");
            choice = sc.next().charAt(0);

        } while (choice == 'Y' || choice == 'y');

        sc.close();
    }
}