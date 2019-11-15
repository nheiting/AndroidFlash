package com.example.flashcards;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class PersistentData {

    private static final String SHARED_PREFERENCES = "shared preferences";
    private static final String FLASH_TOPICS = "topic list";
    private static final String CARDS_GIVEN_TOPIC = "list given topic";



    public static void persistenceSaveTopics(Activity act, ArrayList<String> list) {


         SharedPreferences sharedPref = act.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPref.edit();
         Gson gson = new Gson();
         String json = gson.toJson(list);
         editor.putString(FLASH_TOPICS, json);
         editor.apply();



    }

    public static ArrayList<String> persistenceLoadTopics(Activity act) {

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


    public static void persistenceSaveCardsForATopic(Activity act, HashMap<String, ArrayList<Flashcard>> map) {


        SharedPreferences sharedPref = act.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(map);
        editor.putString(CARDS_GIVEN_TOPIC, json);
        editor.apply();



    }

    public static HashMap<String, ArrayList<Flashcard>> persistenceLoadCardsGivenTopic(Activity act) {

        HashMap<String, ArrayList<Flashcard>> map;

        SharedPreferences sharedPref = act.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(CARDS_GIVEN_TOPIC, null);
        Type type = new TypeToken<HashMap<String, ArrayList<Flashcard>>>(){}.getType();

        map =  gson.fromJson(json, type);

        if(map == null) {
            return new HashMap<String, ArrayList<Flashcard>>();
        }
        else  {
            return map;
        }


    }


}
