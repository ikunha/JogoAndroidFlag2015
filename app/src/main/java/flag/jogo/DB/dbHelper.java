package flag.jogo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class dbHelper extends SQLiteOpenHelper {

    public dbHelper(Context context){
        super(context, dbContract.MyConstants.DATABASE_NAME, null, dbContract.MyConstants.DATABASE_VERSION);
    }

    private static final String DATABASE_CREATE_PERGUNTAS = "create table" +
            dbContract.MyConstants.DATABASE_TABLE + " ("
            + dbContract.MyConstants.KEY_ID_PERGUNTAS +
            " integer primary key autoincrement, " +
            dbContract.MyConstants.PERGUNTA + " text not null;";

    private static final String DATABASE_CREATE_RESPOSTAS = "create table" +
            dbContract.MyConstants.DATABASE_TABLE2 + " ("
            + dbContract.MyConstants.KEY_ID_RESPOSTAS +
            " integer primary key autoincrement, " +
            dbContract.MyConstants.OPCOES + " text not null, " +
            dbContract.MyConstants.CORRECTO +" text not null;";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_PERGUNTAS);
        db.execSQL(DATABASE_CREATE_RESPOSTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbContract.MyConstants.DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

}
