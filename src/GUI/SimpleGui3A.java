package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui3A implements ActionListener {
    JFrame frame;

    public static void main(String[] args) {
        SimpleGui3A gui = new SimpleGui3A();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button3A = new JButton("Change colors");
        button3A.addActionListener(this);
        MyDrawPanel3A drawPanel = new MyDrawPanel3A();

        frame.getContentPane().add(BorderLayout.SOUTH, button3A);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.repaint();
    }
}
