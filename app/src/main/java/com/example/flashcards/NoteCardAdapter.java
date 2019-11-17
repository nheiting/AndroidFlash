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

public class NoteCardAdapter extends RecyclerView.Adapter<NoteCardAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private Activity activity;
    private OnItemClickListener mListener;
    private boolean allowsRemoveItem;
    public ArrayList<String> flashcardTopics = new ArrayList<String>();

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



    public NoteCardAdapter(List<String> flashCardTopics, Activity activity, boolean allowsRemoveItem) {

        this.flashcardTopics.addAll(flashCardTopics);
        this.activity = activity;
        this.allowsRemoveItem = allowsRemoveItem;
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


                removeItem(flashcardTopics.get(position));
                return true;

            }

        });

        String topic = flashcardTopics.get(position);
        holder.flashTopic.setText(topic);

        //Alternate background colors for even and odd items in the list
        int bgColor = position % 2 == 0 ? 230 : 255;
        holder.itemView.setBackgroundColor(Color.rgb(bgColor, bgColor, bgColor));

    }

    @Override
    public int getItemCount() {
        return flashcardTopics.size();
    }

    //This removes the data from our dataset
    private void removeItem(String topic) {

        if(allowsRemoveItem) {

            int position = flashcardTopics.indexOf(topic);
            flashcardTopics.remove(position);
            notifyItemRemoved(position);
            PersistentData.persistenceSaveTopics(this.activity, flashcardTopics);

            //Remove the set of notecards for that topic
            HashMap<String, ArrayList<Flashcard>> map = PersistentData.persistenceLoadCardsGivenTopic(activity);
            map.put(topic, null);
            PersistentData.persistenceSaveCardsForATopic(activity, map);

        }
        else {
            return;
        }

    }



}
