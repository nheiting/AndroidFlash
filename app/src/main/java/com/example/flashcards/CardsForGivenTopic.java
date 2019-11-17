package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

//This class shows the notecards for a given topic. The CreateNotecards class must pass an
//array list of items so this class knows what to populate
public class CardsForGivenTopic extends AppCompatActivity {

    private final int REQUEST_CODE = 1;

    TextView topicText;
    HashMap<String, ArrayList<Flashcard>> map;
    private NoteCardGivenTopicAdapter adapter;
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_for_given_topic);


        Intent intent = getIntent();

        if(topic == null) {

            topic = intent.getStringExtra(CreateNotecards.TOPIC_PASSED);
        }
        topicText = findViewById(R.id.topicName);
        topicText.setText(topic);

        adapter = new NoteCardGivenTopicAdapter(topic, this);


        map = PersistentData.persistenceLoadCardsGivenTopic(this);
        RecyclerView.LayoutManager mgr = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView rV = findViewById(R.id.flashListGivenTopic);
        rV.setAdapter(adapter);
        rV.setLayoutManager(mgr);



    }



    public void createFrontAndBackView(View view) {



        Intent i = new Intent(this, FlipperViewFrontBackFlash.class);
        i.putExtra(CreateNotecards.TOPIC_PASSED, topicText.getText().toString());
        startActivityForResult(i, REQUEST_CODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                topic = data.getStringExtra("result");
            }

        }
    }
}
