package com.example.njxfuture.API;
import android.content.Context;
import android.content.SharedPreferences;

public class UserCredentialsManager {
    private static final String PREF_NAME = "user_credentials";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_UUID = "uuid";
    private final SharedPreferences sharedPreferences;

    public UserCredentialsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveCredentials(String username, String password,String uuid) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_UUID, uuid);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, null);
    }

    public void clearCredentials() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_PASSWORD);
        editor.apply();
    }
}
