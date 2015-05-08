package flag.jogo.DB;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.security.Provider;


public class dbProviderPerguntas extends ContentProvider{

    // provider identifier
    private static final String AUTHORITY = "android.flag.pt.flag2015.jogo.provider.perguntasprovider";

    // The content: scheme identifies the URI as a content URI pointing to an Android content provider.
    public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);

    // Matcher for see if the type is one element or all elements.
    private static UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);

            private static final int PERGUNTAS_ID  = 1;
            private static final int PERGUNTAS_ALL = 2;

    private static final String MIME_ALL = "vnd.android.cursor.dir/vnd.android.flag.pt.flag2015.jogo.provider." + dbContract.MyConstants.DATABASE_TABLE;
    private static final String MIME_ONE = "vnd.android.cursor.item/vnd.android.flag.pt.flag2015.jogo.provider." + dbContract.MyConstants.DATABASE_TABLE;


    private SQLiteOpenHelper helper;

    static
    {
        URIMATCHER.addURI(AUTHORITY, dbContract.MyConstants.DATABASE_TABLE+"/#", PERGUNTAS_ID);
        URIMATCHER.addURI(AUTHORITY, dbContract.MyConstants.DATABASE_TABLE, PERGUNTAS_ALL);
    }

    @Override
    public boolean onCreate() {
        helper = new dbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri)
    {
        return URIMATCHER.match(uri) == PERGUNTAS_ALL ? MIME_ALL : MIME_ONE;
    }


    // falta inserir a parte da tabela das respostas

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        try
        {
            long row = db.insert(dbContract.MyConstants.DATABASE_TABLE, null, values);
            return (row != -1) ? null : ContentUris.withAppendedId(uri, row);
        }
        finally
        {
            db.close();
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        return db.query(dbContract.MyConstants.DATABASE_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        try
        {
            return db.update(dbContract.MyConstants.DATABASE_TABLE, values, selection, selectionArgs);
        }
        finally
        {
            db.close();
        }
    }

    // falta inserir a parte da tabela das respostas tambem

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        try
        {
            return db.delete(dbContract.MyConstants.DATABASE_TABLE, selection, selectionArgs);
        }
        finally
        {
            db.close();
        }
    }



}
