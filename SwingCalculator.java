import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingCalculator extends JFrame {
    private JTextField textField;
    private JButton[] buttons;
    private String[] buttonLabels = {
        "1", "2", "3", "+",
        "4", "5", "6", "-",
        "7", "8", "9", "*",
        "C", "0", "=", "/"
    };

    public SwingCalculator() {
        textField = new JTextField(20);
        buttons = new JButton[buttonLabels.length];

        // Set layout
        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);

        // Create panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        // Create buttons and add ActionListener
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(new ButtonClickListener());
            panel.add(buttons[i]);
        }

        // Add panel to the frame
        add(panel, BorderLayout.CENTER);

        // Set frame properties
        setTitle("Simple Calculator");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "C":
                    textField.setText("");
                    break;
                case "=":
                    String expression = textField.getText();
                    try {
                        // Evaluate the expression
                        double result = evaluateExpression(expression);
                        textField.setText(String.valueOf(result));
                    } catch (NumberFormatException ex) {
                        textField.setText("Error");
                    }
                    break;
                default:
                    textField.setText(textField.getText() + command);
                    break;
            }
        }
    }

    private double evaluateExpression(String expression) {
        String[] tokens = expression.split("\\s+");
        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    result /= operand;
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleCalculator();
            }
        });
    }
}
