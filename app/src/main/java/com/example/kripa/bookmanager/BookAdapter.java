package com.example.kripa.bookmanager;

/**
 * Created by Kripa on 25/8/2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> booklist;
    private ArrayList<String> authorlist;

    public BookAdapter(Context context,  ArrayList<String> booklist, ArrayList<String> authorlist) {
        this.context = context;
        this.booklist = booklist;
        this.authorlist = authorlist;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.gridview_layout, null);

            TextView books = (TextView) gridView.findViewById(R.id.textView1);

            books.setText(booklist.get(position));

            TextView authors = (TextView) gridView.findViewById(R.id.textView2);

            authors.setText(authorlist.get(position));


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return booklist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}