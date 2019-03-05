package Swing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Widgets_My implements ActionListener, ItemListener, ListSelectionListener {
    JFrame frame = new JFrame();
    JButton button = new JButton("Click my");
    JCheckBox checkBox = new JCheckBox("Choose me");
    JTextField textField = new JTextField("This is a text field");
    JTextArea textArea;
    JLabel label = new JLabel("My names");
    JPanel panelLeft = new JPanel();
    JPanel panelRight = new JPanel();
    JPanel panelSouth = new JPanel();
    JPanel panel = new JPanel();
    JButton buttonGo4;
    JList list;
//    JCheckBox checkBox = new JCheckBox("Goes to 11");


    public static void main(String[] args) {
        Widgets_My widgets_my = new Widgets_My();
        widgets_my.go9();
    }

    public void go() {
        goStart();

        frame.getContentPane().add(BorderLayout.EAST, panelRight);
        frame.getContentPane().add(BorderLayout.WEST, panelLeft);
        frame.getContentPane().add(BorderLayout.SOUTH, panelSouth);
        panelLeft.add(BorderLayout.CENTER, checkBox );
        panelRight.add(BorderLayout.CENTER, button );
        panelSouth.add(BorderLayout.CENTER, textField);

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

    public void go3(){
        Font bigFont = new Font("serif", Font.BOLD, 28);
        button.setFont(bigFont);

        goStart();
        frame.getContentPane().add(BorderLayout.NORTH, button);
        goEnd();
    }

    public void go4() {
        JFrame frameGo4 = new JFrame();
        JPanel panelGo4 = new JPanel();
        buttonGo4 = new JButton("It's my button");

        frameGo4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonGo4.addActionListener(this);

        panelGo4.setBackground(Color.RED);
        panelGo4.add(buttonGo4);
        frameGo4.getContentPane().add(BorderLayout.EAST, panelGo4);

        frameGo4.setSize(250, 200);
        frameGo4.setVisible(true);
    }

    public void go5() {
        goStart();
        panel.setBackground(Color.PINK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        buttonGo4 = new JButton("Button 4");
        panel.add(button);
        panel.add(buttonGo4);
        frame.getContentPane().add(BorderLayout.EAST, panel);
        goEnd();
    }

    public void go6() {
        goStart();
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        goEnd();
    }

    public void go7() {
        goStart();

        textArea = new JTextArea(10, 20);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        goEnd();
    }

    public void go8() { //Check Box
        goStart();
        checkBox.addItemListener(this);
        checkBox.setSelected(true);
        panel.add(checkBox);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        goEnd();
    }

    public void go9() { //JList
        goStart();
        String[] listEntries = {"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta"};
        list = new JList(listEntries);

        list.setVisibleRowCount(4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        goEnd();
    }

    private void goStart() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(this);
    }

    private void goEnd() {
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        button.setText("I've been clicked");
//        textField.setText("  Aloha!!!  ");
        textArea.append("button clicked \n");

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String onOrOff = "off";
        if (checkBox.isSelected()) onOrOff = "on";
        System.out.println("Check box is " + onOrOff);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            String selection = (String) list.getSelectedValue();
            System.out.println(selection);
        }
    }
}
