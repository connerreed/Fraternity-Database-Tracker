package com.myfraternity.entity;

import java.util.Vector;

public class Attendance {
    private int memberId;
    private int eventId;
    private String status;

    public Attendance(int memberId, int eventId, String status) {
        this.memberId = memberId;
        this.eventId = eventId;
        this.status = status;
    }

    public Attendance() {

    }

    public Vector<Object> getVector() {
        Vector<Object> vector = new Vector<>();
        vector.add(memberId);
        vector.add(eventId);
        vector.add(status);
        return vector;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Attendence{" +
                "member_id=" + memberId +
                ", event_id=" + eventId +
                ", status='" + status + '\'' +
                '}';
    }
}
