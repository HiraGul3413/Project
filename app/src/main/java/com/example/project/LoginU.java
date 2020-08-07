package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.project.DatabaseContract.User.TABLE2_NAME;

public class LoginU extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor c;
    String n,p,nn,pp;
    EditText t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_u);
        t1=(EditText)findViewById(R.id.user);
        t2=(EditText)findViewById(R.id.user_password);
        dbHelper = new DatabaseHelper(this);
        Button SignupButton = (Button)findViewById(R.id.SignupButton);
        Button LoginButton = (Button)findViewById(R.id.LoginButton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {
                n= String.valueOf(t1.getText());
                p= String.valueOf(t2.getText());
                if(!n.isEmpty() && !p.isEmpty())
                {
                    int i=0;
                    db = dbHelper.getReadableDatabase();
                    c = db.query(TABLE2_NAME, null, null, null, null, null, null);
                    while (c.moveToNext()) {
                        nn= c.getString(1);
                        pp= c.getString(4);
                        if (nn.equals(n) && pp.equals(p)) {
                            i=1;
                            GlobalVariable gl=new GlobalVariable();
                            gl.setG(nn);
                            Intent intent = new Intent(LoginU.this, Chatbot.class);
                            startActivity(intent);
                            break;
                        }
                    }
                    if (i==0)
                    {
                        ShapeDrawable s=new ShapeDrawable(new RectShape());
                        s.getPaint().setColor(Color.RED);
                        s.getPaint().setStyle(Paint.Style.STROKE);
                        s.getPaint().setStrokeWidth(7);
                        t1.setBackground(s);
                        t2.setBackground(s);
                    }
                }
                else
                {
                    ShapeDrawable s=new ShapeDrawable(new RectShape());
                    s.getPaint().setColor(Color.RED);
                    s.getPaint().setStyle(Paint.Style.STROKE);
                    s.getPaint().setStrokeWidth(7);
                    t1.setBackground(s);
                    t2.setBackground(s);
                }
            }
        });

        SignupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginU.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}
