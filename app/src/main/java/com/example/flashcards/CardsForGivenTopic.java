package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//This class shows the notecards for a given topic. The CreateNotecards class must pass an
//array list of items so this class knows what to populate
public class CardsForGivenTopic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_for_given_topic);
    }

    public void createFrontAndBackView(View view) {
        startActivity(new Intent(getApplicationContext(), FlipperViewFrontBackFlash.class));

    }
}
