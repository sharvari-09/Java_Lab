import java.io.*;
import java.util.*;

public class ReverseArray{
    public static void main(String[] args) {
        String sourceFileName = "source.txt";
        String outputFileName = "output.txt";

        // Read content from source file into ArrayList
        List<String> contentList = readContentFromFile(sourceFileName);

        // Write content in reverse order to output file
        writeContentToFile(contentList, outputFileName);
    }

    public static List<String> readContentFromFile(String fileName) {
        List<String> contentList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentList;
    }

    public static void writeContentToFile(List<String> contentList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write content in reverse order
            for (int i = contentList.size() - 1; i >= 0; i--) {
                writer.write(contentList.get(i));
                writer.newLine();
            }
            System.out.println("Content written to " + fileName + " in reverse order successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
