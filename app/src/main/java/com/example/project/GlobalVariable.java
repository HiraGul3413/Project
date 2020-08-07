package com.example.project;

import android.app.Application;

public class GlobalVariable extends Application {
    private static String g;
    public String getG()
    {
        return g;
    }
    public void setG(String s)
    {
        g=s;
    }
}
