package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateNotecards extends AppCompatActivity {


    //topic passed when going to the cards for given topic activity
    public static final String TOPIC_PASSED = "com.example.flashcards.TOPIC_PASSED";


    private EditText addTopicText;
    private ArrayList<String> list;
    private NoteCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notecards);

        list = PersistentData.persistenceLoadTopics(this);
        addTopicText = findViewById(R.id.editText);




        adapter = new NoteCardAdapter(list, this, true);
        RecyclerView.LayoutManager mgr = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        RecyclerView rV = findViewById(R.id.flashList);
        rV.setAdapter(adapter);
        rV.setLayoutManager(mgr);

        //Removes text after input
        adapter.setOnItemClickListener(new NoteCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                //Pass the string name of the topic to the activity so that it knows
                //the key to store data in hashmap

                String topic = adapter.flashcardTopics.get(position);
                startActivity(new Intent(getApplicationContext(), CardsForGivenTopic.class).putExtra(TOPIC_PASSED, topic));


            }
        });

    }


    public void addCategory(View view) {


        if(addTopicText.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Value for topic can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        if(adapter.flashcardTopics.contains(addTopicText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Already have that category. To delete it, tap and hold category for 2 seconds.", Toast.LENGTH_SHORT).show();
            return;

        }

        adapter.flashcardTopics.add(0, addTopicText.getText().toString());
        Toast.makeText(getApplicationContext(), "Added " + addTopicText.getText().toString() + "to table.", Toast.LENGTH_SHORT).show();


        adapter.notifyItemInserted(0);
        PersistentData.persistenceSaveTopics(this, adapter.flashcardTopics);


    }
}
