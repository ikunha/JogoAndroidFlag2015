package flag.jogo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import flag.jogo.modulo.Question;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Question[] questions;
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(getApplicationContext(), buscarPerguntas.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perguntas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view)
    {
        if ((boolean)view.getTag())
        {
            this.currentQuestionIndex++;
            changeToNextQuestion();
            //testar se chegou ao final
            //toast a dizer o que ganhou

            if (currentQuestionIndex < 15)
            {

            }
            else
            {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_won_game), Toast.LENGTH_SHORT).show();
                finish();
            }

        }
        else
        {
            //patamares de segurança
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_over), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void changeToNextQuestion()
    {
        Question question = this.questions[this.currentQuestionIndex];

        TextView txtQuestion = (TextView)findViewById(R.id.textView);
        txtQuestion.setText(question.getIdentifier() + " - " + question.getText());

        for (Question.Answer answer : question.getAnswers())
        {
            switch (answer.getIdentifier())
            {
                case 'A':
                    Button btnA = (Button)findViewById(R.id.button3);
                    btnA.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnA.setOnClickListener(this);
                    btnA.setTag(answer.isCorrect());
                    break;
                case 'B':
                    Button btnB = (Button)findViewById(R.id.button4);
                    btnB.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnB.setOnClickListener(this);
                    btnB.setTag(answer.isCorrect());
                    break;
                case 'C':
                    Button btnC = (Button)findViewById(R.id.button5);
                    btnC.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnC.setOnClickListener(this);
                    btnC.setTag(answer.isCorrect());
                    break;
                case 'D':
                    Button btnD = (Button)findViewById(R.id.button6);
                    btnD.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnD.setOnClickListener(this);
                    btnD.setTag(answer.isCorrect());
                    break;

                default:
                    break;
            }
        }
    }

    public class GetQuestionsTask extends AsyncTask<Void, Void, Question[]>
    {
        @Override
        protected Question[] doInBackground(Void... voids)
        {
            return GetQuestionsManager.getAllTemp();

            /*
             * tentar buscar da "BD"
             * se tiver:
             *   usa :-)
             * se não tiver:
             *   - ir buscar via HTTP ao servidor a 1a vez
             *   - guardar na "BD"
             * */
        }

        @Override
        protected void onPostExecute(Question[] questions)
        {
            GameActivity.this.questions = questions;
            GameActivity.this.currentQuestionIndex = 0;
            changeToNextQuestion();
        }
    }

}
