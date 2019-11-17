package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.HashMap;

public class FlipperViewFrontBackFlash extends AppCompatActivity {

    ViewFlipper flipper;
    TextView frontText;
    TextView backText;

    private String topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper_view_front_back_flash);

        flipper = findViewById(R.id.viewFlipper);


        frontText = findViewById(R.id.flashFrontText);
        backText = findViewById(R.id.flashBackText);



        Intent intent = getIntent();
        topic = intent.getStringExtra(CreateNotecards.TOPIC_PASSED);

    }

    public void swapSides(View view) {
        flipper.showNext();
    }

    public void  saveCard(View view) {

        if(frontText.getText().length() == 0 || backText.getText().length() == 0) {

            Toast.makeText(getApplicationContext(), "flashcard must have text for front and back", Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            //Create new flashcard and add it to persistent data
            Flashcard flash = new Flashcard(frontText.getText().toString(), backText.getText().toString());

            HashMap<String, ArrayList<Flashcard>> map = PersistentData.persistenceLoadCardsGivenTopic(this);

            //Find list for current topic and add flashcard
            ArrayList<Flashcard> list = map.get(topic);

            //Check if list is null
            if(list == null) {
                list = new ArrayList<>();
            }

            if(list.contains(flash)) {
                Toast.makeText(getApplicationContext(), "Already have that question in this category. To delete it, go back and tap and hold category for 2 seconds.", Toast.LENGTH_SHORT).show();
                return;
            }

            list.add(flash);
            map.put(topic, list);
            PersistentData.persistenceSaveCardsForATopic(this, map);

            startActivity(new Intent(getApplicationContext(), CardsForGivenTopic.class).putExtra(CreateNotecards.TOPIC_PASSED, topic));





        }

    }

    @Override
    public void onBackPressed() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",topic);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}
