package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           HashMap<String, ArrayList<Flashcard>> map =  PersistentData.persistenceLoadCardsGivenTopic(this);



    }


    public void goToCreateView(View view) {

        Intent intent = new Intent(this, CreateNotecards.class);
        startActivity(intent);

    }

    public void goToQuiz(View view) {

        Intent intent = new Intent(this, QuizSelectCategory.class);
        startActivity(intent);
    }

}
