import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 100;
        int rounds = 3;
        int maxAttempts = 5;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        for (int round = 1; round <= rounds; round++) {
            int numberToGuess = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean isGuessed = false;

            System.out.println("\nRound " + round + " of " + rounds);
            System.out.println("I'm thinking of a number between " + min + " and " + max + ". Can you guess what it is?");

            while (attempts < maxAttempts && !isGuessed) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score++;
                    isGuessed = true;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                if (attempts == maxAttempts && !isGuessed) {
                    System.out.println("You've reached the maximum number of attempts. The correct number was " + numberToGuess + ".");
                }
            }
        }

        System.out.println("\nGame Over! Your final score is: " + score + " out of " + rounds);
        scanner.close();
    }
}
