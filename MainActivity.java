package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.notes.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button noteButton = findViewById(R.id.noteButton);
        Button showButton = findViewById(R.id.showButton);

        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNote();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "show all notes", Toast.LENGTH_SHORT).show();
                Intent showAllIntent = new Intent(MainActivity.this, ShowNotesActivity.class);
                showAllIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(showAllIntent);
            }
        });
    }

    public void newNote() {
        Intent newNoteIntent = new Intent(this, NewNote.class);
        startActivity(newNoteIntent);
    }

}