package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginA extends AppCompatActivity {
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_a);
        Button b1=(Button)findViewById(R.id.LoginAButton);
        e1=(EditText)findViewById(R.id.adminName);
        e2=(EditText)findViewById(R.id.adminPassword);
        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {
                String name= String.valueOf(e1.getText());
                String password= String.valueOf(e2.getText());
                if(name.equals("admin") && password.equals("admin"))
                {
                    Intent intent = new Intent(LoginA.this, Questions.class);
                    startActivity(intent);
                }
                else
                {
                    ShapeDrawable s=new ShapeDrawable(new RectShape());
                    s.getPaint().setColor(Color.RED);
                    s.getPaint().setStyle(Paint.Style.STROKE);
                    s.getPaint().setStrokeWidth(7);
                    e1.setBackground(s);
                    e2.setBackground(s);
                }
            }
        });
    }
}