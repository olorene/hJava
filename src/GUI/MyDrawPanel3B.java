package GUI;

import javax.swing.*;
import java.awt.*;

public class MyDrawPanel3B extends JPanel{
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(70, 70, Color.BLUE, 150, 150, Color.ORANGE);

        g2d.setPaint(gradient);
        g2d.fillOval(70, 70, 100, 100);
    }
}
