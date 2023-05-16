package org.myfraternity.entity;

import java.sql.Date;
import java.util.Vector;

public class Chapter {
    private int chapter_id;
    private String name;
    private String location;
    private java.sql.Date charter_date;

    public Chapter(int chapter_id, String name, String location, Date charter_date) {
        this.chapter_id = chapter_id;
        this.name = name;
        this.location = location;
        this.charter_date = charter_date;
    }

    public Chapter() {

    }

    public Vector<Object> getVector() {
        Vector<Object> toReturn = new Vector<>();
        toReturn.add(chapter_id);
        toReturn.add(name);
        toReturn.add(location);
        toReturn.add(charter_date);
        return toReturn;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCharter_date() {
        return charter_date;
    }

    public void setCharter_date(Date charter_date) {
        this.charter_date = charter_date;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "chapter_id=" + chapter_id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", charter_date='" + charter_date + '\'' +
                '}';
    }
}
