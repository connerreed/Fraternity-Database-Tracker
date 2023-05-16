package org.myfraternity.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private String status;
    private int chapterId;
    private List<Committee> committees;

    // Constructor
    public Member(String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, String status, int chapterId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.chapterId = chapterId;
    }

    public Member() {

    }

    public Vector<Object> getVector() {
        Vector<Object> vector = new Vector<>();
        vector.add(memberId);
        vector.add(firstName);
        vector.add(lastName);
        vector.add(email);
        vector.add(phoneNumber);
        vector.add(dateOfBirth);
        vector.add(status);
        vector.add(chapterId);
        return vector;
    }

    // Getters and setters
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public List<Committee> getCommittees() {
        return committees;
    }

    public void setCommittees(List<Committee> committees) {
        this.committees = committees;
    }

    // toString method
    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", status='" + status + '\'' +
                ", chapterId=" + chapterId +
                '}';
    }
}

