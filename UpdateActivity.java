package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.data.DatabaseHelper;
import com.example.notes.model.Notes;
import com.example.notes.util.Util;

public class UpdateActivity extends AppCompatActivity {

    DatabaseHelper db;
    int id;
    String noteBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Button update = findViewById(R.id.update);
        Button delete = findViewById(R.id.delete);
        EditText updateEdit = findViewById(R.id.updateEdit);
        db = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            id = bundle.getInt("ID");
            noteBody = db.displayNote(id);
            updateEdit.setText(noteBody);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = updateEdit.getText().toString();
                long result = db.updateNote(id, content);
                if (result > 0) {
                    Toast.makeText(UpdateActivity.this, "Note updated!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Error updating note!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = updateEdit.getText().toString();
                long result = db.deleteNote(id);
                if (result > 0) {
                    Toast.makeText(UpdateActivity.this, "Note deleted!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Error deleting note!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}