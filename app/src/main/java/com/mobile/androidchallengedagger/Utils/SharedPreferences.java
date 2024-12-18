package com.mobile.androidchallengedagger.Utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobile.androidchallengedagger.data.model.Article;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferences {

    private static Context context;

    public SharedPreferences(Context context){
        this.context = context;
    }

    public final static String PREFS_NAME = "appname_prefs";

    public boolean sharedPreferenceExist(String key)
    {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        if(!prefs.contains(key)){
            return true;
        }
        else {
            return false;
        }
    }

    public static void setInt( String key, int value) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, 0);
    }

    public static void setStr(String key, String value) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStr(String key) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key,"DNF");
    }

    public static void setBool(String key, boolean value) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBool(String key) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(key,false);
    }

    public static void saveList(List<Article> list, String key){
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();

    }

    public static List<Article> getList(String key){
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<List<Article>>() {}.getType();
        if(json.isEmpty()){
            return  new ArrayList<>();
        }
            return gson.fromJson(json, type);

    }
}