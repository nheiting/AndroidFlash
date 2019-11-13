package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateNotecards extends AppCompatActivity {

    public Context createNotecardContext;

    private String EMPTY_STRING = "";

    private static final String TAG = CreateNotecards.class.getSimpleName();

    private EditText addTopicText;
    private List<String> list;
    private NoteCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notecards);

        createNotecardContext = getApplicationContext();
        list = PersistentData.loadPersistantData(this);
        addTopicText = findViewById(R.id.editText);
        addTopicText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                addTopicText.setText("");
            }
        });


        adapter = new NoteCardAdapter(list, this);
        RecyclerView.LayoutManager mgr = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        RecyclerView rV = findViewById(R.id.flashList);
        rV.setAdapter(adapter);
        rV.setLayoutManager(mgr);

        //Removes text after input
        adapter.setOnItemClickListener(new NoteCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                System.out.println("hello world123");
            }
        });

    }


    public void addCategory(View view) {


        if(addTopicText.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Value for topic can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

      //  adapter.notifyItemRangeRemoved(0, list.size());
        adapter.flashcardTopics.add(0, addTopicText.getText().toString());
    //    list.add(0, "hello world");
        //adapter.notifyItemRangeInserted(0, list.size());
        adapter.notifyItemInserted(0);
        PersistentData.savePersistantData(this, adapter.flashcardTopics);


    }
}
