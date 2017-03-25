package com.example.raunak.contactlist;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by raunak on 25/3/17.
 */
public class CursorAdap extends CursorAdapter {
    public CursorAdap(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView no = (TextView) view.findViewById(R.id.no);
        TextView name = (TextView) view.findViewById(R.id.name);
        ImageView im= (ImageView) view.findViewById(R.id.imageView);
        // Extract properties from cursor
        String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String name1 = cursor.getString(cursor.getColumnIndexOrThrow("display_name"));
        String photo_id = cursor.getString(cursor.getColumnIndexOrThrow("photo_id"));
        String number = cursor.getString(cursor.getColumnIndexOrThrow("data1"));

        no.setText(number);
        name.setText(name1);
        if(photo_id!=null) {
            Uri photoUri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, Long.parseLong(photo_id));
            im.setImageURI(photoUri);
        }
        else
        {
            im.setImageResource(R.mipmap.ic_launcher);
        }
        // Populate fields with extracted properties
/*        tvBody.setText(body);
        tvPriority.setText(String.valueOf(priority));
    */
    }


}