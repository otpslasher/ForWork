import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task4 {

    static int ToOneNumber (List<Integer> x){

        Integer [] nums = x.toArray(new Integer[0]);

        int counter = 0;
        int avr;

        Arrays.sort(nums);
        int n = nums.length;
        if (n % 2 == 0) {
            avr = (nums[n / 2 - 1] + nums[n / 2]) / 2;
        } else {
            avr = nums[n / 2];
        }

        for(int i=0; i< nums.length; i++){
            while (nums[i]!= avr) {
                if (nums[i] < avr) {
                    nums[i]++;
                    counter++;
                }
                else if (nums[i] > avr) {
                    nums[i]--;
                    counter++;
                }
                else nums[i] = avr;
            }
        }
        return counter;
    }

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        try {
            String fileName = args[0];
            FileReader fileReader = new FileReader(fileName);

            Scanner sc = new Scanner(fileReader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                int i = Integer.parseInt (line);
                numbers.add(i);
                }
        }
            catch(Exception ex){
                ex.printStackTrace();
            }

        System.out.println("Minimum moves: " + ToOneNumber(numbers));

        }
    }
