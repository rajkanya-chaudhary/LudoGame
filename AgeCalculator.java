import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class AgeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the user's date of birth
        System.out.print("Enter your year of birth (e.g., 1990): ");
        int year = scanner.nextInt();
        System.out.print("Enter your month of birth (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter your day of birth (1-31): ");
        int day = scanner.nextInt();

        // Current date
        LocalDate currentDate = LocalDate.now();

        // Date of birth
        LocalDate birthDate = LocalDate.of(year, month, day);

        // Calculate age
        if (birthDate.isAfter(currentDate)) {
            System.out.println("The date of birth is invalid (it's in the future).");
        } else {
            Period age = Period.between(birthDate, currentDate);
            System.out.println("You are " + age.getYears() + " years, " 
                               + age.getMonths() + " months, and " 
                               + age.getDays() + " days old.");
        }

        scanner.close();
    }
}

