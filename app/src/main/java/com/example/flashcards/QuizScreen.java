package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class QuizScreen extends AppCompatActivity {

    TextView qAndAText;
    TextView scoreText;
    Button goToNextCard;
    ImageView flashImage;
    EditText answerTextField;

    private ArrayList<Flashcard> cards;
    private int currentCard = 0;
    private int answersCorrect = 0;



    SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);


        (new Handler()).postDelayed(null, 5000);


        cards = (ArrayList<Flashcard>) getIntent().getSerializableExtra("cardList");
        Collections.shuffle(cards);


        playShuffleSound();




        flashImage = findViewById(R.id.flashImage);
        qAndAText = findViewById(R.id.qAndAText);
        scoreText = findViewById(R.id.scoreText);
        goToNextCard = findViewById(R.id.buttonNextCard);
        answerTextField = findViewById(R.id.answerTextField);
        goToNextCard.setEnabled(false);


        qAndAText.setText(cards.get(currentCard).getFrontText());



        answerTextField.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {



                    if(answerTextField.getText().toString().toLowerCase().equals(cards.get(currentCard).getBackText().toLowerCase())) {
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();

                        ++answersCorrect;

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();

                        qAndAText.setText(cards.get(currentCard).getBackText());
                        flashImage.setImageResource(R.drawable.flashcardblank);

                    }

                    scoreText.setText(answersCorrect + "/" + cards.size());

                    currentCard++;
                    goToNextCard.setEnabled(true);



                    return true;
                }
                return false;
            }
        });



    }

    private void playShuffleSound() {

        MediaPlayer player = MediaPlayer.create(this, R.raw.shuffling);
        player.start();

    }

    public void buttonNextCard(View view) {

        if(currentCard == cards.size()) {

            //Pass answers correct and list size


            Intent intent = new Intent(this, EndingScreen.class);
            Bundle extras = new Bundle();

            extras.putInt("EXTRA_SCORE", answersCorrect);
            extras.putInt("EXTRA_LIST_SIZE", cards.size());
            intent.putExtras(extras);

            startActivity(intent);
            return;
        }
        goToNextCard.setEnabled(false);
        qAndAText.setText(cards.get(currentCard).getFrontText());
        flashImage.setImageResource(R.drawable.notecard);
        answerTextField.setText("");



    }


}
