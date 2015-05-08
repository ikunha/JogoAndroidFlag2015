package flag.jogo.modulo;


public class Question {
    private static final int Max_Answers = 4;

    private final int _identifier;
    private final String _question;
    private final Answer[] answers;
    private int _count = 0;

    public Question(int id, String question){
        _identifier = id;
        _question = question;
        answers = new Answer[Max_Answers];
    }

    public void addAnswer(Answer answer){
        answers[_count++] = answer;
    }


    public Answer[] getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return _question;
    }

    public int getIdentifier() {
        return _identifier;
    }
}
