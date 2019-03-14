package FlachCards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class QuizCardsPlayer {

    private JTextArea display;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private QuizCard currentCard;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;

    public static void main(String[] args) {
        QuizCardsPlayer reader = new QuizCardsPlayer();
        reader.go();
    }

    public void go(){
//        Формируем GUI
        frame = new JFrame("Quiz Card Player");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

    }

    public class NextCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class OpenMenuList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private void loadFile(File file) {

    }

    private void makeCard(String lineToParse) {

    }

    private void showNextCard() {

    }


}
