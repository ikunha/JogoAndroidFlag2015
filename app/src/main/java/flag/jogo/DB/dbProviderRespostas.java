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

public class dbProviderRespostas extends ContentProvider {


    // provider identifier
    private static final String AUTHORITY = "android.flag.pt.flag2015.jogo.provider.respostasprovider";

    // The content: scheme identifies the URI as a content URI pointing to an Android content provider.
    public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);

    // Matcher for see if the type is one element or all elements.
    private static UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int RESPOSTAS_ID  = 1;
    private static final int RESPOSTAS_ALL = 2;

    private static final String MIME_ALL = "vnd.android.cursor.dir/vnd.android.flag.pt.flag2015.jogo.provider." + dbContract.MyConstants.DATABASE_TABLE2;
    private static final String MIME_ONE = "vnd.android.cursor.item/vnd.android.flag.pt.flag2015.jogo.provider." + dbContract.MyConstants.DATABASE_TABLE2;


    private SQLiteOpenHelper helper;

    static
    {
        URIMATCHER.addURI(AUTHORITY, dbContract.MyConstants.DATABASE_TABLE2+"/#", RESPOSTAS_ID);
        URIMATCHER.addURI(AUTHORITY, dbContract.MyConstants.DATABASE_TABLE2, RESPOSTAS_ALL);
    }

    @Override
    public boolean onCreate() {
        helper = new dbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri)
    {
        return URIMATCHER.match(uri) == RESPOSTAS_ALL ? MIME_ALL : MIME_ONE;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        return db.query(dbContract.MyConstants.DATABASE_TABLE2, projection, selection, selectionArgs, null, null, sortOrder);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs)
    {
       return 0;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return 0;
    }



}
