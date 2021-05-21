package com.example.notes.model;

public class Notes {

    private int note_id;
    private String content;

    public Notes(String content) {
        this.content = content;
    }

    public Notes() {
    }


    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
