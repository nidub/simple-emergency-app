package com.appdid.emergencyservice;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class sharepref extends Application {
    public static final String PREFERENCE_NAME = "misession";
    private static final String SESSION_KEY = "login_status";

    private static SharedPreferences miPreferences;
    private static SharedPreferences.Editor miEditor;
    private static Context context;
    
    @Override
    public void onCreate() {
        super.onCreate();
        miPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        miEditor = miPreferences.edit();
    }

    public static void setSession(boolean session) {
        miEditor.putBoolean(SESSION_KEY, session);
        miEditor.commit();
    }

    public static boolean getSession() {
        boolean value = miPreferences.getBoolean(SESSION_KEY, false);
        return value;
    }

    public static void clearData()
    {
        miPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
