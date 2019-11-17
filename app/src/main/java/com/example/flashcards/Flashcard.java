package com.example.flashcards;

import java.io.Serializable;

public class Flashcard implements Serializable {

    private String front;
    private String back;

    public Flashcard(String front, String back) {

        this.front = front;
        this.back = back;
    }

    public String getFrontText() {
        return this.front;
    }

    public String getBackText() {
        return this.back;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == null) {
            return  false;
        }

        return this.getFrontText().equals(((Flashcard) obj).getFrontText());

    }

}
