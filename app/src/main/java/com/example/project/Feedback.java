package com.example.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.project.DatabaseContract.Feedback.TABLE4_NAME;
import static com.example.project.DatabaseContract.User.TABLE2_NAME;

public class Feedback extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText e1;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor c;
    String name,email,feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        e1=(EditText)findViewById(R.id.feedback);
        Button button = (Button)findViewById(R.id.submit);
        GlobalVariable gl=new GlobalVariable();
        name= gl.getG();
        dbHelper = new DatabaseHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db = dbHelper.getReadableDatabase();
                c = db.query(TABLE2_NAME, null, null, null, null, null, null);
                while (c.moveToNext()) {
                    String nn= c.getString(1);
                    if (nn.equals(name)) {
                        email=c.getString(2);
                        break;
                    }
                }
                db = dbHelper.getWritableDatabase();
                feedback= String.valueOf(e1.getText());

                if(!feedback.isEmpty())
                {
                    ContentValues values = new ContentValues();
                    long newRowId;
                    values.put(DatabaseContract.Feedback.COL_UNAME, name);
                    values.put(DatabaseContract.Feedback.COL_UEMAIL, email);
                    values.put(DatabaseContract.Feedback.COL_fEEDBACK, feedback);
                    newRowId=db.insert(TABLE4_NAME, null, values);
                    if(newRowId > 0)
                    {
                        Toast.makeText(getApplicationContext(),"Thanks For Your Feedback", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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
            Intent intent = new Intent(Feedback.this, ViewProfile.class);
            startActivity(intent);
        } else if (id == R.id.nav_chatbot) {
            Intent intent = new Intent(Feedback.this, Chatbot.class);
            startActivity(intent);
        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(Feedback.this, Feedback.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(Feedback.this, MainPage.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
