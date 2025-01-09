import java.util.Scanner;
import java.util.Random;

public class simple_game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Generate a random number between 1 and 100
        int numberToGuess = random.nextInt(100) + 1;
        int numberOfTries = 0;
        boolean hasWon = false;
        int guess;

        System.out.println("Welcome to the Guess the Number Game!");
        System.out.println("I have picked a number between 1 and 100. Can you guess what it is?");

        while (!hasWon) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            numberOfTries++;

            if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (guess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                hasWon = true;
                System.out.println("Congratulations! You guessed the number in " + numberOfTries + " tries.");
            }
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}

        
    

