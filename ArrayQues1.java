import java.util.Scanner;

public class ArrayQues1{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("ENTER NUMBER OF ELEMENTS:");
        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i=0;i<=n;i++){
            System.out.println("Enter element:");
            arr[i]=sc.nextInt();

        }
    
        System.out.println("THE ENTERED ELEMENTS ARE:");
        for(int j=0;j<=n;j++){
            System.out.println(arr[j]);
        }
        sc.close();
    }
}