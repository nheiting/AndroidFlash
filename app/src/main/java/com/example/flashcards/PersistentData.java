package com.example.flashcards;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PersistentData {

    private static final String SHARED_PREFERENCES = "shared preferences";
    private static final String FLASH_TOPICS = "topic list";


    public static ArrayList<String> flashTopics;

    public static void savePersistantData(Activity act, ArrayList<String> list) {


         SharedPreferences sharedPref = act.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPref.edit();
         Gson gson = new Gson();
         String json = gson.toJson(list);
         editor.putString(FLASH_TOPICS, json);
         editor.apply();



    }

    public static ArrayList<String> loadPersistantData(Activity act) {

        ArrayList<String> topics;

        SharedPreferences sharedPref = act.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(FLASH_TOPICS, null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();

        topics =  gson.fromJson(json, type);

        if(topics == null) {
            return new ArrayList<String>();
        }
        else  {
            return topics;
        }


    }

}
