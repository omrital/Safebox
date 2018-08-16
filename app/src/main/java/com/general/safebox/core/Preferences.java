package com.general.safebox.core;

import android.content.Context;
import android.content.SharedPreferences;
import com.general.safebox.model.PasswordInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class Preferences {

    private static Preferences instance;
    private final SharedPreferences sharedPreferences;
    private final String SHARED_PREFERENCES_FILE_NAME = "SafeBoxPreferences";

    private static final String USER_PASSWORD = "user_password";
    private static final String USER_SAVED_PASSWORDS = "user_saved_passwords";
    private static final String USER_NAME = "user_name";
    private static final String USER_LAST_NAME= "user_last_name";
    private static final String USER_LATITUDE= "user_latitude";
    private static final String USER_LONGITUDE= "user_longitude";

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

    public void saveUserPassword(String password) {
        sharedPreferences.edit().putString(USER_PASSWORD, password).apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }

    public void saveUserName(String name) {
        sharedPreferences.edit().putString(USER_NAME, name).apply();
    }

    public String getUserLastName() {
        return sharedPreferences.getString(USER_LAST_NAME, "");
    }

    public void saveUserLastName(String lastName) {
        sharedPreferences.edit().putString(USER_LAST_NAME, lastName).apply();
    }

    public String getUserLongitude() {
        return sharedPreferences.getString(USER_LATITUDE, "0.0");
    }

    public void saveUserLongitude(String longitude) {
        sharedPreferences.edit().putString(USER_LATITUDE, longitude).apply();
    }

    public String getUserLatitude() {
        return sharedPreferences.getString(USER_LATITUDE, "0.0");
    }

    public void saveUserLatitude(String latitude) {
        sharedPreferences.edit().putString(USER_LATITUDE, latitude).apply();
    }

    public void savePasswords(ArrayList<PasswordInfo> passwords) {
        String passwordsJson = new Gson().toJson(passwords);
        sharedPreferences.edit().putString(USER_SAVED_PASSWORDS, passwordsJson).apply();
    }

    public ArrayList<PasswordInfo> getPasswords() {
        String passwordsJson = sharedPreferences.getString(USER_SAVED_PASSWORDS, "");
        if(!passwordsJson.isEmpty()) {
            return new Gson().fromJson(passwordsJson, new TypeToken<ArrayList<PasswordInfo>>(){}.getType());
        }
        return new ArrayList<>();
    }
}
