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

import static com.example.project.DatabaseContract.Data.TABLE1_NAME;
import static com.example.project.DatabaseContract.QTBA.TABLE3_NAME;
import static com.example.project.DatabaseContract.User.TABLE2_NAME;

public class AddQuestion extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    EditText t1,t2,t3,t4,t5,t6,t7;
    String mq,k1,k2,k3,k4,k5,ans,s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        Intent intent=new Intent();
        intent=getIntent();
        s=intent.getStringExtra("q");

        t1=(EditText)findViewById(R.id.MK);
        t2=(EditText)findViewById(R.id.k1);
        t3=(EditText)findViewById(R.id.k2);
        t4=(EditText)findViewById(R.id.k3);
        t5=(EditText)findViewById(R.id.k4);
        t6=(EditText)findViewById(R.id.k5);
        t7=(EditText)findViewById(R.id.ans);
        t1.setText(s);

        dbHelper = new DatabaseHelper(this);
        Button button = (Button)findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db = dbHelper.getWritableDatabase();
                mq= String.valueOf(t1.getText());
                k1= String.valueOf(t2.getText());
                k2= String.valueOf(t3.getText());
                k3= String.valueOf(t4.getText());
                k4= String.valueOf(t5.getText());
                k5= String.valueOf(t6.getText());
                ans= String.valueOf(t7.getText());
                if(!mq.isEmpty() && !k1.isEmpty() && !k2.isEmpty() && !k3.isEmpty() && !k4.isEmpty()  && !k5.isEmpty() && !ans.isEmpty())
                {
                    ContentValues values = new ContentValues();
                    long newRowId;
                    values.put(DatabaseContract.Data.COL_HINT1, mq);
                    values.put(DatabaseContract.Data.COL_HINT2, k1);
                    values.put(DatabaseContract.Data.COL_HINT3, k2);
                    values.put(DatabaseContract.Data.COL_HINT4, k3);
                    values.put(DatabaseContract.Data.COL_HINT5, k4);
                    values.put(DatabaseContract.Data.COL_HINT6, k5);
                    values.put(DatabaseContract.Data.COL_REPLY, ans);
                    newRowId=db.insert(TABLE1_NAME, null, values);
                    db.close();
                    if(newRowId > 0)
                    {
                        db = dbHelper.getWritableDatabase();
                        String val1 = s;
                        Integer i1= db.delete(TABLE3_NAME, "question= ?",new String[] {val1});
                        if (i1 > 0) {
                            Toast.makeText(getApplicationContext(), i1+"  Records deleted: " , Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                        //Toast.makeText(getApplicationContext(),"Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddQuestion.this, Questions.class);
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All fields are required" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}