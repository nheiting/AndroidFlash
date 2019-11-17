package com.example.flashcards;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteCardGivenTopicAdapter extends RecyclerView.Adapter<NoteCardGivenTopicAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private Activity activity;
    private OnItemClickListener mListener;
    public ArrayList<Flashcard> flashcardsGivenTopic = new ArrayList<Flashcard>();
    private String topic;
    private HashMap<String, ArrayList<Flashcard>> map;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView flashTopic;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            flashTopic = itemView.findViewById(R.id.flashTopic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null) {
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }

                    }

                }
            });

        }
    }




    public NoteCardGivenTopicAdapter(String topic, Activity activity) {

        this.topic = topic;
        //Load data for topic
        map = PersistentData.persistenceLoadCardsGivenTopic(activity);

        //Create new array list if the one for the topic is null
        if(map.get(topic) == null) {
            map.put(topic, new ArrayList<Flashcard>());
        }

        this.flashcardsGivenTopic.addAll(map.get(topic));
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = activity.getLayoutInflater().inflate(R.layout.notecard_topic, parent, false);
        ViewHolder vh = new ViewHolder(v, mListener);


        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //Remove this soon
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {


                removeItem(position);
                return true;

            }

        });

        Flashcard card = flashcardsGivenTopic.get(position);
        holder.flashTopic.setText(card.getFrontText());

        //Alternate background colors for even and odd items in the list
        int bgColor = position % 2 == 0 ? 230 : 255;
        holder.itemView.setBackgroundColor(Color.rgb(bgColor, bgColor, bgColor));

    }

    @Override
    public int getItemCount() {
        return flashcardsGivenTopic.size();
    }

    //This removes the data from our dataset
    private void removeItem(int position) {



        flashcardsGivenTopic.remove(position);
        notifyItemRemoved(position);


        map.put(topic, flashcardsGivenTopic);
        PersistentData.persistenceSaveCardsForATopic(this.activity, map);

    }



}
