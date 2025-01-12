import java.util.Scanner;

public class DigitalLock {

    // Predefined PIN for the lock
    private static final String CORRECT_PIN = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxAttempts = 3;
        boolean isUnlocked = false;

        System.out.println("Welcome to the Digital Lock System.");

        // Allow the user to attempt unlocking up to maxAttempts times
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.nextLine();

            if (enteredPin.equals(CORRECT_PIN)) {
                isUnlocked = true;
                break;
            } else {
                System.out.println("Incorrect PIN. Attempts left: " + (maxAttempts - attempt));
            }
        }

        if (isUnlocked) {
            System.out.println("Lock unlocked successfully!");
        } else {
            System.out.println("Too many failed attempts. Lock remains locked.");
        }

        scanner.close();
    }
}

