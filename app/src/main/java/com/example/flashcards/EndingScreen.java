package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndingScreen extends AppCompatActivity {

    TextView endScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending_screen);

        endScore = findViewById(R.id.endingScore);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        int numCorrect = extras.getInt("EXTRA_SCORE");
        int listSize = extras.getInt("EXTRA_LIST_SIZE");

        if(numCorrect == listSize) {
            endScore.setText("pefect score!!!");
        }
        else {
            endScore.setText(numCorrect + "/" + listSize);
        }




    }


    public void quizAgain(View view) {
        Intent intent = new Intent(this, QuizSelectCategory.class);
        startActivity(intent);
    }
}
