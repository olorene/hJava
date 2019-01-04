package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui3B1 implements ActionListener {
    JFrame frame;

    public static void main(String[] args) {
        SimpleGui3B1 gui = new SimpleGui3B1();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Change");
        button.addActionListener(this);
        MyDrawPanel3B1 drawPanel3B1 = new MyDrawPanel3B1();

        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel3B1);
        frame.setSize(300, 300);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.repaint();
    }
}
