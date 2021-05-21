package com.example.notes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notes.model.Notes;
import com.example.notes.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Util.DATABASE_NAME, factory, Util.DATABASE_VERSION);
    }

    public DatabaseHelper(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ( " + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Util.CONTENT + " TEXT)";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(db);
    }

    public long insertNote(Notes notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.CONTENT, notes.getContent());
        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    public boolean fetchNote(String content) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.CONTENT}, Util.CONTENT + "=?", new String[] {content}, null, null, null);
        int numberOfRows = cursor.getCount();
        db.close();

        if (numberOfRows > 0)
        {
            return true;
        }
        else
            return true;

    }

    public String displayNote(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectItem = "SELECT " + Util.CONTENT + " FROM " + Util.TABLE_NAME + " WHERE rowid=" + id;
        Cursor cursor = db.rawQuery(selectItem, null);
        if (cursor != null)
            cursor.moveToFirst();
        String itemContents = cursor.getString(0);
        return itemContents;
    }

    public List<Notes> fetchAllNotes() {
        List<Notes> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Notes note = new Notes();
                note.setNote_id(cursor.getInt(0));
                note.setContent(cursor.getString(1));
                notesList.add(note);
            } while (cursor.moveToNext());
        }
        return notesList;
    }

    public long updateNote(int id, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.CONTENT, content);
        return db.update(Util.TABLE_NAME, contentValues, Util.NOTE_ID + "=" + id, null);
    }

    public long deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Util.TABLE_NAME, Util.NOTE_ID + "=" + id,null);
    }

}
