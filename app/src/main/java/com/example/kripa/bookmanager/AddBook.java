package com.example.kripa.bookmanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class AddBook extends ActionBarActivity {
    private GridView gridView;
    private ArrayList<String> booklist = new ArrayList<String>();
    private ArrayList<String> authorlist = new ArrayList<String>();
    private BookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Button button = (Button) findViewById(R.id.add);
        final Books.BookDbHelper mDbHelper;
        mDbHelper = new Books.BookDbHelper(getApplicationContext());
        //Read the DB contents
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        final String[] projection = {
                Books.BooksEntry._ID,
                Books.BooksEntry.COLUMN_NAME_BOOK_ID,
                Books.BooksEntry.COLUMN_NAME_BOOK_TITLE,
                Books.BooksEntry.COLUMN_NAME_BOOK_AUTHOR
        };

/*
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Books.BooksEntry.COLUMN_NAME_BOOK_AUTHOR + " DESC";
        String selection = Books.BooksEntry.COLUMN_NAME_BOOK_AUTHOR + " LIKE ?";
        String[] selectionArgs = { "JK Rowling" };
*/
        Cursor c = db.query(
                Books.BooksEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null ,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        c.moveToFirst();
        int entries = c.getCount();
        TableLayout manager = (TableLayout) findViewById(R.id.book_list);
        gridView=(GridView) findViewById(R.id.gridView1);
        //ArrayList
        //list=new ArrayList<String>();
        //adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,list);
        adapter = new BookAdapter(getApplicationContext(),booklist, authorlist);
        for(int i=0;i<entries;i++){
            //TableRow row = new TableRow(this);
            //row.setBackgroundColor(Color.GRAY);
            //row.setLayoutParams(new ViewGroup.LayoutParams(
            //        ViewGroup.LayoutParams.FILL_PARENT,
            //        ViewGroup.LayoutParams.WRAP_CONTENT));
            //add Layouts to your new row
            //TextView btxt = new TextView(this);
            //TextView atxt = new TextView(this);
            String bookname= c.getString(c.getColumnIndex(Books.BooksEntry.COLUMN_NAME_BOOK_TITLE));
            String authorname= c.getString(c.getColumnIndex(Books.BooksEntry.COLUMN_NAME_BOOK_AUTHOR));
            booklist.add(bookname);
            authorlist.add(authorname);
            gridView.setAdapter(adapter);
            //atxt.setText(authorname);
            //btxt.setText(bookname);
            //row.addView(btxt);
            //row.addView(atxt);

            //manager.addView(row);
            c.moveToNext();
        }
        Log.i("ITEM",  "f");


        button.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TableLayout manager = (TableLayout) findViewById(R.id.book_list);
                        TableRow row = new TableRow(v.getContext());
                        row.setBackgroundColor(Color.GRAY);
                        row.setLayoutParams(new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.FILL_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        //add Layouts to your new row
                        TextView btxt = new TextView(v.getContext());
                        TextView atxt = new TextView(v.getContext());
                        String bookname= ((EditText) findViewById(R.id.book_name_txt)).getText().toString();
                        String authorname= ((EditText) findViewById(R.id.author_name_txt)).getText().toString();
                        atxt.setText(authorname);
                        btxt.setText(bookname);
                        row.addView(btxt);
                        row.addView(atxt);

                        manager.addView(row);
                        SQLiteDatabase db = mDbHelper.getWritableDatabase();

                        // Create a new map of values, where column names are the keys
                        ContentValues values = new ContentValues();
                        values.put(Books.BooksEntry.COLUMN_NAME_BOOK_ID, "1");
                        values.put(Books.BooksEntry.COLUMN_NAME_BOOK_TITLE,bookname );
                        values.put(Books.BooksEntry.COLUMN_NAME_BOOK_AUTHOR, authorname);

                        // Insert the new row, returning the primary key value of the new row
                        long bookId;
                        bookId = db.insert(
                                Books.BooksEntry.TABLE_NAME,"",
                                values);
                        Log.i("BOOK","added in db");

                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
