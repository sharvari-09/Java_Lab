import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorChangeFrame extends JFrame implements ActionListener {
    JButton redButton, blueButton, yellowButton, greenButton;

    public ColorChangeFrame() {
        setTitle("Color Change Frame");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        redButton = new JButton("Red");
        redButton.setBackground(Color.RED);
        redButton.addActionListener(this);
        add(redButton);

        blueButton = new JButton("Blue");
        blueButton.setBackground(Color.BLUE);
        blueButton.addActionListener(this);
        add(blueButton);

        yellowButton = new JButton("Yellow");
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.addActionListener(this);
        add(yellowButton);

        greenButton = new JButton("Green");
        greenButton.setBackground(Color.GREEN);
        greenButton.addActionListener(this);
        add(greenButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        Color currentColor = clickedButton.getBackground();

        // Randomly select a color that is not the current color
        Color newColor;
        do {
            newColor = getRandomColor();
        } while (newColor.equals(currentColor));

        clickedButton.setBackground(newColor);
    }

    // Helper method to get a random color
    private Color getRandomColor() {
        Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
        return colors[(int)(Math.random() * colors.length)];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ColorChangeFrame();
            }
        });
    }
}
