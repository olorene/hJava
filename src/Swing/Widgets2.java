package Swing;

import javax.swing.*;
import java.awt.*;

public class Widgets2 {

    JFrame frame;
    JPanel panel;
    JPanel panel2;
    JButton buttonStart;
    JButton buttonStop;

    public static void main(String[] args) {
        Widgets2 widgets = new Widgets2();
        widgets.go2();
    }

    public void go() {
        startGo();

        BoxLayout boxLayout =  new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setLayout(boxLayout);

        buttonStart = new JButton("Start");
        buttonStop = new JButton("Stop");

        panel.add(buttonStart);
        panel.add(buttonStop);

        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(250, 200);
        frame.setVisible(true);
    }

    public void go2() {
        JFrame myFrame = new JFrame();
        JPanel myPanel = new JPanel();
        JButton buttonFirst = new JButton("First");
        JButton buttonSecond = new JButton("Second");

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myPanel.setBackground(Color.GRAY);
        myFrame.getContentPane().add(BorderLayout.NORTH, myPanel);
        myPanel.add(buttonSecond);
        myFrame.getContentPane().add(BorderLayout.CENTER, buttonFirst);

        myFrame.setSize(300, 300);
        myFrame.setVisible(true);

    }

    private void startGo() {
        frame = new JFrame("This is my frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
