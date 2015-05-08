package flag.jogo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import flag.jogo.modulo.Answer;
import flag.jogo.modulo.Question;

public class buscarPerguntas extends Service {

    private static final String SERVICE_LOG = "SERVICE_LOG";

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(SERVICE_LOG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    URL url = new URL("http://54.187.166.51:81/questions");

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    String res = "";
                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = "";
                    while ((line = rd.readLine()) != null)
                        res += line;

                    JSONArray response = new JSONArray(res);
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject row = response.getJSONObject(i);
                        int id = row.getInt("id");
                        String question = row.getString("question");
                        JSONArray answers = row.getJSONArray("answers");

                        Question q = new Question(id, question);

                        for (int j = 0; j < answers.length(); j++){
                            JSONObject answersRow = answers.getJSONObject(j);
                            String answersID = answersRow.getString("id");
                            String answer = answersRow.getString("answer");
                            boolean correct = answersRow.getBoolean("correct");

                            Answer a = new Answer(answersID, answer, correct);
                            q.addAnswer(a);
                        }

                    }








                    stopSelf();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    Log.i(SERVICE_LOG, "Cannot get questions.");
                }
                }
                }).start();
        return START_NOT_STICKY;
            }
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(SERVICE_LOG, "onDestroy");
    }
}

