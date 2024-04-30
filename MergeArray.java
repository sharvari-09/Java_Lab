import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MergeArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create the first ArrayList
        ArrayList<String> list1 = new ArrayList<>();
        System.out.println("Enter elements for the first ArrayList (enter 'done' to stop):");
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("done")) {
            list1.add(input);
        }

        // Create the second ArrayList
        ArrayList<String> list2 = new ArrayList<>();
        System.out.println("Enter elements for the second ArrayList (enter 'done' to stop):");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("done")) {
            list2.add(input);
        }

        // Merge the two ArrayLists
        ArrayList<String> mergedList = new ArrayList<>(list1);
        mergedList.addAll(list2);

        // Sort the merged list alphabetically
        Collections.sort(mergedList);

        // Display the sorted merged list
        System.out.println("Sorted merged list:");
        for (String item : mergedList) {
            System.out.println(item);
        }
    }
}
