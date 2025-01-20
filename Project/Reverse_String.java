package Project;
import java.util.Scanner;

public class Reverse_String {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input from user
        System.out.print("Enter a string to reverse: ");
        String input = scanner.nextLine();

        // Logic to reverse the string
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }

        // Output the reversed string
        System.out.println("Reversed string: " + reversed);
        scanner.close();
    }
}


