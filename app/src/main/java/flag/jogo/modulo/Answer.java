package flag.jogo.modulo;

public class Answer {

    private final String _identifier;
    private final String _text;
    private final boolean _isCorrect;

    public Answer(String id, String text, boolean isCorrect){
        _identifier = id;
        _text = text;
        _isCorrect = isCorrect;
    }

    public String getIdentifier() {
        return _identifier;
    }

    public String getText() {
        return _text;
    }

    public boolean isCorrect() {
        return _isCorrect;
    }
}
