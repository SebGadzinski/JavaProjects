import java.util.*;

public class ImageProgram {

    public static void main(String[] arg)
    {
        int num = 0;        //initializing variables
        int record = 0;
        boolean[][] list2 = new boolean[10][10]; // creating a 10 by 10 grid
        Random lol = new Random(); // creating a new random
        String line = "";
        for (int length = 0; 10 > length; length += 1) // for loop to fill the 10 by 10 array
        {
            boolean[] list1 = new boolean[10];
            for (int width = 0; 10 > width; width += 1) // for loop for filling booleans in
            {
                boolean rn = lol.nextBoolean(); // generating a random boolean
                list1[width] = rn;
            }
            list2[length] = list1;
        }
        for (int length = 0; 10 > length; length += 1) // for loop to display the 10 by 10 array in string form
        {
            num = 0;
            line = "";
            for (int width = 0; 10 > width; width += 1) // for loop to see if the it needs to print a O or a .
            {
                if (list2[length][width] == true)
                {
                    line += "O";
                    num += 1;
                    if (record <= num) // keeps the record of how many there are in a row as its going
                    {
                        record = num;
                    }
                } else // if its false
                {
                    line += ".";
                    num = 0;
                }
            }
            System.out.println(line); // prints the line of code
        }
        System.out.println("The longest horizontal line of O is: " + record); // tells what the record is
        record = 0; // reusing variable
        for (int length = 0; 10 > length; length += 1) // for loop to find out how many O's in a vertical row
        {
            num = 0; // resets num
            for (int width = 0; 10 > width; width += 1) // checks and sees how many are in a row
            {
                if (list2[width][length])
                {
                    num += 1;

                } else
                {
                    if (record < num)
                    {
                        record = num;
                    }
                    num = 0;
                }
            }
            if (record < num) // is added because at the end of a coloumb it tests if there is a new record
            {
                record = num;
            }


        }
        System.out.println("The longest vertical line of O is: " + record); // displays the record
    }
}