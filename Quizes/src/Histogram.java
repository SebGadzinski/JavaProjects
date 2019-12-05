import java.util.Random;

public class Histogram {
    public static void main(String[] arg)
    {
        Random randomnum = new Random(); // Generates random number
        int[] list = {0,0,0,0,0,0,0,0,0,0}; // making a list for the 10 digits
        String star = "";
        for (int i = 0; i < 100; i = i + 1) // loops 100 times
        {
            int rn = randomnum.nextInt(10); // generates a nummber between 0 and 10 and then adds it to the place in the list
            list[rn] += 1;
        }

        for (int l = 0; l < list.length; l = l + 1) // loop for displaying the list with '*'
        {
            for (int e = 0; e < list[l]; e = e +1) // loop for gathering amount of * in the list
            {
                star += "*";
            }
            System.out.println(l + " | " + star); // prints list with '*'
            star = ""; // reset stars in order to use next list index
        }

    }
}
