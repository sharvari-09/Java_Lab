import java.io.*;
import java.util.*;

public class FileHandling {
    public static void main(String[] args) {
        // File path
        String filePath = "numbers.txt";

        // ArrayList to store numbers
        ArrayList<Integer> numbersList = new ArrayList<>();

        // Read numbers from the file and store them in the ArrayList
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                numbersList.add(num);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            return;
        }

        // Find maximum and minimum values
        if (!numbersList.isEmpty()) {
            int max = Collections.max(numbersList);
            int min = Collections.min(numbersList);

            // Display results
            System.out.println("Numbers in the file: " + numbersList);
            System.out.println("Maximum value: " + max);
            System.out.println("Minimum value: " + min);
        } else {
            System.out.println("No numbers found in the file.");
        }
    }
}
