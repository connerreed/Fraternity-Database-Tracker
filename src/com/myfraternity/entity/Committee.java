package com.myfraternity.entity;

import java.util.Vector;

public class Committee {
    private int committeeId;
    private String name;
    private String description;
    private int chapterId;

    public Committee(int committeeId, String name, String description, int chapterId) {
        this.committeeId = committeeId;
        this.name = name;
        this.description = description;
        this.chapterId = chapterId;
    }

    public Vector<Object> getVector() {
        Vector<Object> vector = new Vector<>();
        vector.add(committeeId);
        vector.add(name);
        vector.add(description);
        vector.add(chapterId);
        return vector;
    }

    public Committee() {

    }

    public int getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(int committeeId) {
        this.committeeId = committeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    @Override
    public String toString() {
        return "Committee{" +
                "committee_id=" + committeeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", chapter_id=" + chapterId +
                '}';
    }
}
