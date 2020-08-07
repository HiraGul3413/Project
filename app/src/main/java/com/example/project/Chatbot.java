package com.example.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.project.DatabaseContract.Data.TABLE1_NAME;
import static com.example.project.DatabaseContract.QTBA.TABLE3_NAME;

public class Chatbot extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ArrayList<String> linkedList = new ArrayList<>();
    GridView gridview;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor c;
    int me;
    String h1, h2, h3, h4, h5, h6, r,s,size;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridview = (GridView) findViewById(R.id.l1);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        editText= (EditText) findViewById(R.id.messageEditText);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void fun(View view) {

        s = editText.getText().toString();
        if(!s.isEmpty()) {
            linkedList.add(s);
            linkedList.add("");
            linkedList.add("");
            int i = 0;
            me=1;
            c = db.query(TABLE1_NAME, null, null, null, null, null, null);
            s.trim();
            while (c.moveToNext()) {
                h1 = c.getString(1);
                h2 = c.getString(2);
                h3 = c.getString(3);
                h4 = c.getString(4);
                h5 = c.getString(5);
                h6 = c.getString(6);
                r = c.getString(7);
                if (h1.equalsIgnoreCase(s) || h2.equalsIgnoreCase(s) || h3.equalsIgnoreCase(s) || h4.equalsIgnoreCase(s) || h5.equalsIgnoreCase(s) || h6.equalsIgnoreCase(s) || s.startsWith(h1) || s.startsWith(h2) || s.startsWith(h3) || s.startsWith(h4) || s.startsWith(h5) || s.startsWith(h6)) {
                    linkedList.add(r);
                    i = 1;
                    break;
                }
            }
            if (i == 0) {
                db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                long newRowId;
                values.put(DatabaseContract.QTBA.COL_QUESTION, s);
                newRowId = db.insert(TABLE3_NAME, null, values);
                if (newRowId > 0) {
                    //Toast.makeText(getApplicationContext(), "Qadded " + newRowId, Toast.LENGTH_SHORT).show();
                }
                linkedList.add("no result found\nSEARCH ON GOOGLE\n");
                me=2;
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, linkedList);
        gridview.setAdapter(adapter);
        if(me==2)
        {
            int m=gridview.getCount()-1;
            size= String.valueOf(m);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    String a = String.valueOf(position);
                    if(a.equals(size)){
                        String url = "http://google.com/#q=" + s;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                    else {
                        //Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        editText.getText().clear();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_viewprofile) {
            Intent intent = new Intent(Chatbot.this, ViewProfile.class);
            startActivity(intent);
        } else if (id == R.id.nav_chatbot) {
            Intent intent = new Intent(Chatbot.this, Chatbot.class);
            startActivity(intent);
        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(Chatbot.this, Feedback.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(Chatbot.this, MainPage.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}