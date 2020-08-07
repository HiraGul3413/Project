package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.project.DatabaseContract.Data.TABLE1_NAME;
import static com.example.project.DatabaseContract.Feedback.TABLE4_NAME;
import static com.example.project.DatabaseContract.QTBA.TABLE3_NAME;
import static com.example.project.DatabaseContract.User.TABLE2_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "ChatbotData.db";
    private static final String CREATE_TBL_DATA = "CREATE TABLE "
            + TABLE1_NAME + " ("
            + DatabaseContract.Data._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Data.COL_HINT1 + " TEXT NOT NULL, "
            + DatabaseContract.Data.COL_HINT2 + " TEXT NOT NULL, "
            + DatabaseContract.Data.COL_HINT3 + " TEXT NOT NULL, "
            + DatabaseContract.Data.COL_HINT4 + " TEXT NOT NULL, "
            + DatabaseContract.Data.COL_HINT5 + " TEXT NOT NULL, "
            + DatabaseContract.Data.COL_HINT6 + " TEXT NOT NULL, "
            + DatabaseContract.Data.COL_REPLY + " TEXT NOT NULL)";
    private static final String DROP_TABLE1="DROP TABLE IF EXISTS "+ TABLE1_NAME;

    private static final String CREATE_TBL_USER = "CREATE TABLE "
            + TABLE2_NAME + " ("
            + DatabaseContract.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.User.COL_NAME + " TEXT NOT NULL, "
            + DatabaseContract.User.COL_EMAIL + " TEXT NOT NULL, "
            + DatabaseContract.User.COL_PHONENO + " TEXT NOT NULL, "
            + DatabaseContract.User.COL_PASSWORD + " TEXT NOT NULL)";
    private static final String DROP_TABLE2="DROP TABLE IF EXISTS "+ TABLE2_NAME;

    private static final String CREATE_TBL_QTBA = "CREATE TABLE "
            + TABLE3_NAME + " ("
            + DatabaseContract.QTBA._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.QTBA.COL_QUESTION + " TEXT NOT NULL)";
    private static final String DROP_TABLE3="DROP TABLE IF EXISTS "+ TABLE3_NAME;

    private static final String CREATE_TBL_FEEDBACK = "CREATE TABLE "
            + TABLE4_NAME + " ("
            + DatabaseContract.Feedback._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Feedback.COL_UNAME + " TEXT NOT NULL, "
            + DatabaseContract.Feedback.COL_UEMAIL + " TEXT NOT NULL, "
            + DatabaseContract.Feedback.COL_fEEDBACK + " TEXT NOT NULL)";
    private static final String DROP_TABLE4="DROP TABLE IF EXISTS "+ TABLE4_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_USER);
        db.execSQL(CREATE_TBL_QTBA);
        db.execSQL(CREATE_TBL_DATA);
        db.execSQL(CREATE_TBL_FEEDBACK);
        insertData(db,"introduce yourself","who are you","what is your name","are you","about yourself","explain yourself","I am your chatbot. How can I help you?");
        insertData(db,"NULL","how are you","are you ok","are you healthy","your health","how are you feeling","I am fine, thankyou for asking. Hope you and your loved ones are safe and healthy. Let me know if I can help you with some thing.");
        insertData(db,"Hi","Hello","Hey","what's up","Listen","whats up","Hi. How can I help you?");
        insertData(db,"make me laugh","i am bored","tell me a joke","getting bore","feeling sad","joke","Here's a joke for u. Why did the school kids eat their homework? Because there teacher told them it was a piece of cake :D");
        insertData(db,"Riddle","Random fun","tell me a riddle","any riddle","ask me a riddle","null","If you have one, you don't share it,if u share it, you don't have it...What is it? A SECRET :)");
        insertData(db,"What is java","about java","java","java introduction","introduction to java","define java","Java is an Object Oriented programming language. It was developed by James Gosling and 1st released by Sun Microsystems in 1995.");
        insertData(db,"Bye","Bye Bye","Good Bye","Take care","Byeee","Null","Take care :)");
        insertData(db,"Adult human has how many teeth","How many teeth","human teeth","how many teeth do i have","how many teeth do we have","How many teeth adult human has","32 teeth");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE1);
        db.execSQL(DROP_TABLE2);
        db.execSQL(DROP_TABLE3);
        db.execSQL(DROP_TABLE4);
        onCreate(db);
    }
    public void insertData(SQLiteDatabase db,String h1,String h2,String h3,String h4,String h5,String h6,String r)
    {
        ContentValues values = new ContentValues();
        long newRowId;
        values.put(DatabaseContract.Data.COL_HINT1, h1);
        values.put(DatabaseContract.Data.COL_HINT2, h2);
        values.put(DatabaseContract.Data.COL_HINT3, h3);
        values.put(DatabaseContract.Data.COL_HINT4, h4);
        values.put(DatabaseContract.Data.COL_HINT5, h5);
        values.put(DatabaseContract.Data.COL_HINT6, h6);
        values.put(DatabaseContract.Data.COL_REPLY, r);
        db.insert(TABLE1_NAME, null, values);
    }
}