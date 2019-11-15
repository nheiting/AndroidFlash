package com.example.flashcards;

public class Flashcard {

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

        return this.getFrontText().equals(((Flashcard) obj).getFrontText());

    }

}
