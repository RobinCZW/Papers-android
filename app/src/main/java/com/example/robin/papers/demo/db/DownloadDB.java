package com.example.robin.papers.demo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.robin.papers.demo.model.PaperFile;
import com.example.robin.papers.demo.util.PaperFileUtils;

/**
 * Created by Administrator on 16/4/25.
 */
public class DownloadDB extends SQLiteOpenHelper{
    private volatile static DownloadDB downloadDB;

    private SQLiteDatabase db;

    public static final String TABLE_NAME = "download_info";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String COURSE = "course";
    public static final String SIZE = "size";
    public static final String TYPE = "type";
    public static final String URL = "url";
    public static final String TIME = "time";

    private static final String QUERY_DOWNLOADED = "SELECT * FROM " + TABLE_NAME
            + " WHERE " + URL + " = ?";
    private static final String ADD_DOWNLOADED = "INSERT INTO " + TABLE_NAME
            + "(" + NAME + ", " + COURSE + ", " + SIZE + ", " + TYPE + ", "
            + URL + ", " + TIME + ")"
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_DOWNLOADED = "DELETE FROM " + TABLE_NAME + " WHERE "
            + URL + " = ?";

    private DownloadDB(Context context) {
        super(context, "Downloaded_Info.db", null, 1);
        db = getWritableDatabase();
    }

    public static DownloadDB getInstance(Context context) {
        if (downloadDB == null) {
            synchronized (DownloadDB.class) {
                if (downloadDB == null) {
                    downloadDB = new DownloadDB(context);
                }
            }
        }
        return downloadDB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME +" TEXT NOT NULL,"
                + COURSE +" TEXT NOT NULL,"
                + SIZE + " TEXT NOT NULL,"
                + TYPE + " TEXT NOT NULL,"
                + URL + " TEXT NOT NULL,"
                + TIME + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean isDownloaded(String url) {
        Cursor cursor = db.rawQuery(QUERY_DOWNLOADED, new String[]{ url });
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    public void addDownloadInfo(PaperFile paperFile) {
        db.execSQL(ADD_DOWNLOADED, new String[] {
                paperFile.getName(),
                paperFile.getCourse(),
                paperFile.getSize(),
                paperFile.getType(),
                paperFile.getUrl(),
                PaperFileUtils.getCurrentTime()
        });
    }

    public void removeDownloadInfo(String url) {
        db.execSQL(REMOVE_DOWNLOADED, new String[] { url });
    }

}
