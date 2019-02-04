package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Widgets_My implements ActionListener {
    JFrame frame = new JFrame();
    JButton button = new JButton("Click my");
    JCheckBox checkBox = new JCheckBox("Choose me");
    JTextField textField = new JTextField("This is a text field");
    JPanel panelLeft = new JPanel();
    JPanel panelRight = new JPanel();

    public static void main(String[] args) {
        Widgets_My widgets_my = new Widgets_My();
        widgets_my.go2();
    }

    public void go() {
        goStart();

        frame.getContentPane().add(BorderLayout.EAST, panelRight);
        frame.getContentPane().add(BorderLayout.WEST, panelLeft);
        panelLeft.add(BorderLayout.CENTER, checkBox );
        panelRight.add(BorderLayout.CENTER, button );

        goEnd();
    }

    public void go2() {
        goStart();

        JPanel panelA = new JPanel();
        JPanel panelB = new JPanel();
        panelB.add(new JButton("Button 1"));
        panelB.add(new JButton("Button 2"));
        panelB.add(new JButton("Button 3"));
        panelA.add(panelB);
        frame.add(panelA);

        goEnd();

    }

    private void goEnd() {
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private void goStart() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setText("I've been clicked");
    }
}
