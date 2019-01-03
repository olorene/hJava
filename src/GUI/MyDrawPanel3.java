package GUI;

import javax.swing.*;
import java.awt.*;

public class MyDrawPanel3 extends JPanel {
    public void paintComponent(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(20, 50, 100, 100);
    }

}
