package com.organiseme;

/**
 * Created by Piotrek on 01.01.2017.
 */

public class Note {
    private String topic;
    private String noteContent;

    Note(){
        this.topic = topic;
        this.noteContent = noteContent;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
