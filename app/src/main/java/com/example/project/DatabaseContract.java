package com.example.project;

import android.provider.BaseColumns;

public class DatabaseContract {
    public DatabaseContract() {}
    public static abstract class Data implements BaseColumns {
        public static final String TABLE1_NAME = "data";
        public static final String COL_HINT1="hint1";
        public static final String COL_HINT2="hint2";
        public static final String COL_HINT3="hint3";
        public static final String COL_HINT4="hint4";
        public static final String COL_HINT5="hint5";
        public static final String COL_HINT6="hint6";
        public static final String COL_REPLY="reply";
    }
    public static abstract class User implements BaseColumns {
        public static final String TABLE2_NAME = "user";
        public static final String COL_NAME="name";
        public static final String COL_EMAIL="email";
        public static final String COL_PHONENO="phoneno";
        public static final String COL_PASSWORD="password";
    }
    public static abstract class QTBA implements BaseColumns {
        public static final String TABLE3_NAME = "qtba";
        public static final String COL_QUESTION="question";
    }
    public static abstract class Feedback implements BaseColumns {
        public static final String TABLE4_NAME = "feedback";
        public static final String COL_UNAME="uname";
        public static final String COL_UEMAIL="uemail";
        public static final String COL_fEEDBACK="feedback";
    }
}
