package com.example.raunak.contactlist;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Cursor phones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
               TextView no= (TextView) view.findViewById(R.id.no);

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + no.getText()));
                startActivity(intent);
            }
        });
        ContentResolver contentResolver = getContentResolver();


        final String[] projection = {ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_ID};
        phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");

        for (int i = 0; i < phones.getColumnCount(); i++)
        {
            Log.i("name1",phones.getColumnName(i));
        }
        CursorAdap cursorAdap=new CursorAdap(this,phones);
        list.setAdapter(cursorAdap);
    }
}
