package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class FlipperViewFrontBackFlash extends AppCompatActivity {

    ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper_view_front_back_flash);

        flipper = findViewById(R.id.viewFlipper);


    }

    public void swapSides(View view) {
        flipper.showNext();
    }

    public void  saveCard(View view) {

    }
}
