package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.data.DatabaseHelper;
import com.example.notes.model.Notes;

public class NewNote extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        EditText Body = findViewById(R.id.Body);
        Button saveNote = findViewById(R.id.saveNote);
        db = new DatabaseHelper(this);

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = Body.getText().toString();
                if (content.length() != 0) {
                    long result = db.insertNote(new Notes(content));
                    if (result > 0) {
                        Toast.makeText(NewNote.this, "Creation Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(NewNote.this, "Creation Error", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(NewNote.this, "Please fill in note", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}