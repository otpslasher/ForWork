import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task2 {


    public static void main(String[] args) {

        //Accepts files path from console
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Circle data path name: ");
        String cPathName = input.nextLine();
        System.out.println("Enter the Dot data path name: ");
        String dPathName = input.nextLine();
        Scanner reader;
        Scanner readerTwo;
        try {
            reader = new Scanner(new File(cPathName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            readerTwo = new Scanner(new File(dPathName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Reads coordinates of circle
        List<Float> xyr = new ArrayList<>();
        float line;
        while (reader.hasNextFloat()) {
            line = reader.nextFloat();
            xyr.add(line);
        }
        input.close();
        //define circle coordinates
        float cx = xyr.get(0);
        float cy = xyr.get(1);
        float rad = xyr.get(2);


        //Reads coordinates of dot
        List<Float> xy = new ArrayList<>();
        float lineTwo;
        while (readerTwo.hasNextFloat()) {
            lineTwo = readerTwo.nextFloat();
            xy.add(lineTwo);
        }
        input.close();
        //define dot coordinates
        float dx;
        float dy;
        for (int i=0; i < xy.size();i++){
             dx = xy.get(i);
             dy = xy.get(++i);

            //calculations
            float formula = ((cx - dx)*(cx - dx)) + ((cy - dy)*(cy - dy));
            if (Math.round(formula) < rad*rad){
                System.out.println(1);
            }
            else if (Math.round(formula) == rad*rad) {
                System.out.println(0);
            }
            else {
                System.out.println(2);
            }

        }


    }

    }

