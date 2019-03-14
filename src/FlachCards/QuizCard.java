package FlachCards;

public class QuizCard {
    private String question;
    private String answer;

    public QuizCard(String aQuestion, String anAnswer) {
        question = aQuestion;
        answer = anAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
