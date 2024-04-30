import java.util.Scanner;

class starThread extends Thread {
    private int numberOfLines;

    // Constructor to initialize the number of lines
    public starThread(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    // Method to print the star pattern
    @Override
    public void run() {
        for (int i = 1; i <= numberOfLines; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
            try {
                Thread.sleep(1000); // Delay of 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class starThreadMain{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of lines in the star pattern: ");
        int numberOfLines = scanner.nextInt();

        // Create and start the thread with the specified number of lines
        starThread starThread = new starThread(numberOfLines);
        starThread.start();
    }
}
