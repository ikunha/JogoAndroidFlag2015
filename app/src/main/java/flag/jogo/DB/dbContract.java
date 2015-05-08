package flag.jogo.DB;

import android.net.Uri;

public class dbContract {

    public static class MyConstants {

        public static final String DATABASE_NAME = "jogo_db.sql";
        public static final String DATABASE_TABLE = "perguntas_table";
        public static final String DATABASE_TABLE2 = "respostas_table";
        public static final int DATABASE_VERSION = 1;
        public static final String KEY_ID_PERGUNTAS = "_id";
        public static final String KEY_ID_RESPOSTAS = "_id";
        public static final String PERGUNTA = "pergunta";
        public static final String OPCOES = "opcoes";
        public static final String CORRECTO = "correcto";


        public static Uri CONTENT_PROVIDER = Uri.withAppendedPath(dbProviderPerguntas.CONTENT_URI, DATABASE_TABLE);

    }
}