package com.myfraternity.entity;

import java.util.Date;
import java.util.Vector;

public class Event {
    private int event_id;
    private String name;
    private java.sql.Date date;
    private String location;
    private String description;
    private int chapter_id;

    public Event(int event_id, String name, java.sql.Date date, String location, String description, int chapter_id) {
        this.event_id = event_id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
        this.chapter_id = chapter_id;
    }

    public Event() {

    }

    public Vector<Object> getVector() {
        Vector<Object> vector = new Vector<>();
        vector.add(event_id);
        vector.add(name);
        vector.add(date);
        vector.add(location);
        vector.add(description);
        vector.add(chapter_id);
        return vector;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", chapter_id=" + chapter_id +
                '}';
    }
}
