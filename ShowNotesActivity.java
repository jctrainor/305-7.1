package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.notes.data.DatabaseHelper;
import com.example.notes.model.Notes;

import java.util.ArrayList;
import java.util.List;

public class ShowNotesActivity extends AppCompatActivity {

    ListView notesListView;
    ArrayList<String> notesArrayList;
    ArrayAdapter<String> adapter;
    List<Notes> notesList;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        notesListView = findViewById(R.id.notesListView);
        notesArrayList = new ArrayList<>();
        db = new DatabaseHelper(ShowNotesActivity.this);
        notesList = db.fetchAllNotes();
        for (Notes note :notesList) {
            notesArrayList.add(note.getContent());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesArrayList);
        notesListView.setAdapter(adapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer key = notesList.get(position).getNote_id();
                Bundle bundle = new Bundle();
                bundle.putInt("ID", key);
                Intent updateIntent = new Intent(ShowNotesActivity.this, UpdateActivity.class);
                updateIntent.putExtra("ID", key);
                startActivity(updateIntent);
            }
        });

    }

    protected void onRestart() {
        super.onRestart();
        notesArrayList.clear();
        notesList = db.fetchAllNotes();
        for (Notes note : notesList) {
            notesArrayList.add(note.getContent());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesArrayList);
        notesListView.setAdapter(adapter);
    }
}