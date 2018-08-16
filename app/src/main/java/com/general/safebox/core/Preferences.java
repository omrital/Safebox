package com.general.safebox.core;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static Preferences instance;
    private final SharedPreferences sharedPreferences;
    private final String SHARED_PREFERENCES_FILE_NAME = "SafeBoxPreferences";

    private static final String USER_PASSWORD = "user_password";


    public static Preferences getInstance(Context context) {
        if(instance == null) {
            instance = new Preferences(context);
        }
        return instance;
    }

    private Preferences(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getUserPassword() {
        return sharedPreferences.getString(USER_PASSWORD, "");
    }

    public void setUserPassword(String password) {
        sharedPreferences.edit().putString(USER_PASSWORD, password).apply();
    }
}
