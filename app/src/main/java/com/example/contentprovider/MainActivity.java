package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

/*import android.annotation.NonNull;
import android.annotation.Nullable;*/
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchContacts();
    }
    private void fetchContacts()
    {
        ArrayList<String> contacts=new ArrayList<>();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection=null;
        String[] selectionArgs=null;
        String sortOrder=null;
        ContentResolver resolver=getContentResolver();
        Cursor cursor=resolver.query(uri,projection,selection,selectionArgs,sortOrder);
        while (cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.i("vivek", "Name- "+name+" Num- "+num);
            contacts.add(name+"\n"+num);
        }
        ((ListView)findViewById(R.id.listContact)).setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts));
    }
}