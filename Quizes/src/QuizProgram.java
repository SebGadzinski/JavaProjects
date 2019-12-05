import java.util.Random;
import java.util.Scanner;

public class QuizProgram
{
    public static void main(String[] arg)
    {

        int user_input, answer;                 // Initializing variables
        int max = 99;
        int min = 0;
        Random randomnumber = new Random();     // Creating a random
        int final_grade = 0;
        for (int i = 0; i<10; i = i + +1 ) {    // Creating for loop for that 10 questions come out


            int n1 = randomnumber.nextInt(max); // Getting 2 random numbers for the addition or subtraction
            int n2 = randomnumber.nextInt(n1);
            int operator = 1 + randomnumber.nextInt(2); // Random generator  for a subtraction or a addition
            if (operator == 1) // Subtraction
            {
                System.out.println("What is the answer to " + n1 + " - " + n2); // Question
                answer = n1 - n2;
                user_input = new Scanner(System.in).nextInt(); //Grabs users answer
                if (user_input == answer)  //Rf correct give them a +1
                {
                    System.out.println("You are correct!");
                    final_grade += 1;
                }
                else // If not correct dont give them anything
                {
                    System.out.println("You are not correct, the answer is " + answer);
                }
            }
            if (operator == 2) // Addition
            {
                System.out.println("What is the answer to " + n1 + " + " + n2);
                answer = n1 + n2;
                user_input = new Scanner(System.in).nextInt();
                if (user_input == answer) {
                    System.out.println("You are correct!");
                    final_grade += 1;
                } else {
                    System.out.println("You are not correct, the answer is " + answer);
                }
            }
        }
        int percent = final_grade * 10; // Gives final grade
        System.out.println("You scored " + percent + "% on the quiz"); // Tells the user what they got on the test


    }
}
