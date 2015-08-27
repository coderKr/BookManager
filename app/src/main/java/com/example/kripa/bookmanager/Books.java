package com.example.kripa.bookmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Kripa on 14/3/2015.
 */
public class Books {
    public Books(){}
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BooksEntry.TABLE_NAME + " (" +
                    BooksEntry._ID + " INTEGER PRIMARY KEY," +
                    BooksEntry.COLUMN_NAME_BOOK_ID + TEXT_TYPE + COMMA_SEP +
                    BooksEntry.COLUMN_NAME_BOOK_TITLE + TEXT_TYPE + COMMA_SEP +
                    BooksEntry.COLUMN_NAME_BOOK_AUTHOR + TEXT_TYPE +" )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BooksEntry.TABLE_NAME;

    /* Inner class that defines the table contents */
    public static abstract class BooksEntry implements BaseColumns {
        public static final String TABLE_NAME = "Books";
        public static final String COLUMN_NAME_BOOK_ID = "BookId";
        public static final String COLUMN_NAME_BOOK_TITLE = "Name";
        public static final String COLUMN_NAME_BOOK_AUTHOR= "Author";
    }

    public static class BookDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Books.db";

        public BookDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
