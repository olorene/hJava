package FlachCards;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


}
