package com.example.project;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.project.DatabaseContract.Feedback.TABLE4_NAME;
import static com.example.project.DatabaseContract.User.TABLE2_NAME;

public class aFeedback extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    ArrayAdapter<String> adapter;
    List<String> countryList = new ArrayList();
    Cursor c;
    String name,email, feedback,res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_feedback);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        c = db.query(TABLE4_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {
            name= c.getString(1);
            email=c.getString(2);
            feedback=c.getString(3);
            //res=name+"\n"+email+"\n"+"\n"+feedback+"\n";
            res="Feedback By: "+name+"\n"+"\n          "+feedback+"\n";
            countryList.add(res);
        }
        ListView lv=(ListView) findViewById(R.id.listViewF);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, countryList);
        lv.setAdapter(adapter);
        c.close();

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

        if (id == R.id.nav_viewusers) {
            Intent intent = new Intent(aFeedback.this, Users.class);
            startActivity(intent);
        } else if (id == R.id.nav_questions) {
            Intent intent = new Intent(aFeedback.this, Questions.class);
            startActivity(intent);
        } else if (id == R.id.nav_afeedback) {
            Intent intent = new Intent(aFeedback.this, aFeedback.class);
            startActivity(intent);
        } else if (id == R.id.nav_alogout) {
            Intent intent = new Intent(aFeedback.this, MainPage.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
