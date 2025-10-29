package com.example.ngay29thang10;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String PREF_NAME = "contacts_pref";
    private static final String KEY_CONTACTS = "contacts";

    public static List<Contact> getContacts(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_CONTACTS, "[]");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Contact>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public static void saveContacts(Context context, List<Contact> contacts) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(contacts);
        editor.putString(KEY_CONTACTS, json);
        editor.apply();
    }
}
