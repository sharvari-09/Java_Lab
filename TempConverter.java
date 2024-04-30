import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends JFrame {
    private JTextField inputField;
    private JButton convertButton;
    private JLabel resultLabel;

    public TempConverter() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        inputField = new JTextField(10);
        convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        // Set layout
        setLayout(new FlowLayout());

        // Add components to the frame
        add(new JLabel("Enter temperature in Celsius:"));
        add(inputField);
        add(convertButton);
        add(resultLabel);

        // Add ActionListener to the convertButton
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        // Set frame properties
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void convertTemperature() {
        String inputText = inputField.getText().trim();
        try {
            double celsius = Double.parseDouble(inputText);
            double fahrenheit = (celsius * 9 / 5) + 32;
            resultLabel.setText("Temperature in Fahrenheit: " + fahrenheit);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TempConverter();
            }
        });
    }
}
