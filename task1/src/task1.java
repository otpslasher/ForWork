import java.util.Arrays;

public class task1 {

    public static void main(String[] args) {

            int [] nums = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            nums[i] = Integer.parseInt(args[i]);
        }

            int n = nums[0];
            int m = nums[1];

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

