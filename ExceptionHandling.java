import java.util.Scanner;

public class ExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number (x): ");
        int x = scanner.nextInt();

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        try {
            // Check if x is less than 0 or greater than the array size
            if (x < 0) {
                throw new IllegalArgumentException("Number (x) cannot be less than 0.");
            }
            if (x > size) {
                throw new IllegalArgumentException("Number (x) cannot be greater than the array size.");
            }

            // Calculate factorial
            long factorial = calculateFactorial(x);
            System.out.println("Factorial of " + x + " is: " + factorial);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to calculate factorial
    private static long calculateFactorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * calculateFactorial(n - 1);
    }
}
