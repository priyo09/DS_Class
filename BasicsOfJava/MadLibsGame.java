package BasicsOfJava;
import java.util.Scanner;
public class MadLibsGame {
    public static void main(String [] args){
        // Mad Libs Game

        String adjective1;
        String noun1;
        String adjective2;
        String verb1;
        String adjective3;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an adjective (description) :  ");
        adjective1 = sc.nextLine();

        System.out.print("Enter a noun (person, place, or thing): ");
        noun1 = sc.nextLine();

        System.out.print("Enter another adjective (description) :  ");
        adjective2 = sc.nextLine();

        System.out.print("Enter a verb (action) : ");
        verb1 =sc.nextLine();

        System.out.println("Enter one more adjective (description): ");
        adjective3 = sc.nextLine();

        System.out.println("Today I went to a " + adjective1 + " park.");
        System.out.println("In an exibit, I saw a "+ noun1);
        System.out.println("It was a very " + adjective2 + " day.");
        System.out.println("I " +verb1+ " all day long.");
        System.out.println("I had a " +adjective3+ " time!");

        sc.close();

    }
}
