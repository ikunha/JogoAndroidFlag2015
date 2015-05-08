package flag.jogo.DB;

import android.content.ContentValues;

import flag.jogo.modulo.Question;

public class dbManager {

    public void save(Question question){
        ContentValues values = new ContentValues();
        values.put(dbContract.MyConstants.ID_PERGUNTAS, question.getIdentifier());
        values.put(dbContract.MyConstants.PERGUNTA, question.getQuestion());
        values.put(dbContract.MyConstants.OPCOES, question.getAnswers());
    }
}
