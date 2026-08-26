package BasicsOfJava;
import java.util.Scanner;

public class TakeInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        System.out.print("What is your GPA? ");
        double gpa = sc.nextDouble();

        System.out.print("Are you a student (true/false)? ");
        boolean isStudent = sc.nextBoolean();

        System.out.println("Your GPA is: " + gpa);
        System.out.println("Hello, " + name + "! You are " + age + " years old.");

        System.out.println("Is Student: " + isStudent);
        

        sc.close();
    }
}
