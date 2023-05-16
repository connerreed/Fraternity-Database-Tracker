package org.myfraternity.entity;

import java.sql.Date;
import java.util.Vector;

public class Officer {
    private int officerId;
    private String position;
    private Date startDate;
    private Date endDate; // NULL if still in position
    private int memberId;

    public Officer(int officerId, String position, Date startDate, Date endDate, int memberId) {
        this.officerId = officerId;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberId = memberId;
    }

    public Officer() {

    }

    public int getOfficerId() {
        return officerId;
    }

    public void setOfficerId(int officerId) {
        this.officerId = officerId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Officer{" +
                "officer_id=" + officerId +
                ", position='" + position + '\'' +
                ", start_date='" + startDate + '\'' +
                ", end_date='" + endDate + '\'' +
                ", member_id=" + memberId +
                '}';
    }

    public Vector<Object> getVector() {
        Vector<Object> vector = new Vector<>();
        vector.add(officerId);
        vector.add(position);
        vector.add(startDate);
        vector.add(endDate);
        vector.add(memberId);
        return vector;
    }
}
