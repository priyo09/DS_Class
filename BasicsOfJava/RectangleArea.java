package BasicsOfJava;
import java.util.Scanner;
public class RectangleArea {
    public static void main(String [] args){
// calculate rectangle area

        double width = 0;
        double height = 0;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the width of the rectangle: ");
        width = sc.nextDouble();

        System.out.print("Enter the height of the rectangle: ");
        height = sc.nextDouble();

        double area = width * height; // formula = width * height

        System.out.println("the area of the Rectangle is: " +area+ "cm^2");

        sc.close();

    }
}
