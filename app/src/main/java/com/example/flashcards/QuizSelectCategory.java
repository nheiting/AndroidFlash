package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.flashcards.CreateNotecards.TOPIC_PASSED;

public class QuizSelectCategory extends AppCompatActivity {

    TextView categoryText;
    private ArrayList<String> list;
    private NoteCardAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_select_category);

        categoryText = findViewById(R.id.categoryText);



        list = PersistentData.persistenceLoadTopics(this);

        if(list == null || list.size() == 0) {
            categoryText.setText("You must make a deck of flashcards before continuing!");
        }

        adapter = new NoteCardAdapter(list, this, false);
        RecyclerView.LayoutManager mgr = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView rV = findViewById(R.id.quizList);
        rV.setAdapter(adapter);
        rV.setLayoutManager(mgr);


        //Removes text after input
        adapter.setOnItemClickListener(new NoteCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                //Pass the string name of the topic to the activity so that it knows
                //the key to store data in hashmap

                String topic = adapter.flashcardTopics.get(position);
                ArrayList<Flashcard> cards = PersistentData.persistenceLoadCardsGivenTopic(QuizSelectCategory.this).get(topic);

                if(cards == null || cards.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Must have at least one card before you quiz yourself!", Toast.LENGTH_SHORT).show();
                    return;

                }

                startActivity(new Intent(getApplicationContext(), QuizScreen.class).putExtra("cardList", cards));

            }
        });

    }

    }

