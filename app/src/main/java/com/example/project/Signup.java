package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.project.DatabaseContract.User.TABLE2_NAME;

public class Signup extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String name, email, phone, password, cpass;
    EditText t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        t1=(EditText)findViewById(R.id.name);
        t2=(EditText)findViewById(R.id.email);
        t3=(EditText)findViewById(R.id.phone);
        t4=(EditText)findViewById(R.id.password);
        t5=(EditText)findViewById(R.id.confirm_password);
        dbHelper = new DatabaseHelper(this);
        Button button = (Button)findViewById(R.id.SignupButtonGreen);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db = dbHelper.getWritableDatabase();
                name= String.valueOf(t1.getText());
                email= String.valueOf(t2.getText());
                phone= String.valueOf(t3.getText());
                password= String.valueOf(t4.getText());
                cpass= String.valueOf(t5.getText());
                if(!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !password.isEmpty() && !cpass.isEmpty() && password.equals(cpass))
                {
                    ContentValues values = new ContentValues();
                    long newRowId;
                    values.put(DatabaseContract.User.COL_NAME, name);
                    values.put(DatabaseContract.User.COL_EMAIL, email);
                    values.put(DatabaseContract.User.COL_PHONENO, phone);
                    values.put(DatabaseContract.User.COL_PASSWORD, password);
                    newRowId=db.insert(TABLE2_NAME, null, values);
                    if(newRowId > 0)
                    {
                        Toast.makeText(getApplicationContext(),"Account created successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Signup.this, LoginU.class);
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect inputs. Unable to create account." , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
