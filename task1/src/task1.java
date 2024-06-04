import java.util.Arrays;
import java.util.Scanner;

public class task1 {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

            System.out.print("Enter array elements: ");
            int n = sc.nextInt();
            System.out.print("Enter interval length: ");
            int m = sc.nextInt();

            int[] array = new int[n];
            Arrays.setAll(array, i -> ++i);

            int i = 0;
            System.out.print("Path: ");
            do {
            System.out.print(array[i]);
            i = (i + m - 1) % n;
            }
            while (i != 0);

        }
    }

