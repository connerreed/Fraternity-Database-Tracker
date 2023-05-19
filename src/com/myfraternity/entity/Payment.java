package com.myfraternity.entity;

import java.util.Vector;

public class Payment {
    private int payment_id;
    private int member_id;
    private float amount_due;
    private float amount_initial;
    private java.sql.Date payment_date;
    private String description;

    public Payment(int payment_id, int member_id, float amount_due, float amount_initial, java.sql.Date payment_date, String description) {
        this.payment_id = payment_id;
        this.member_id = member_id;
        this.amount_due = amount_due;
        this.amount_initial = amount_initial;
        this.payment_date = payment_date;
        this.description = description;
    }

    public Vector<Object> getVector() {
        Vector<Object> vector = new Vector<>();
        vector.add(payment_id);
        vector.add(member_id);
        vector.add(amount_due);
        vector.add(amount_initial);
        vector.add(payment_date);
        vector.add(description);
        return vector;
    }

    public Payment() {

    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public float getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(float amount_due) {
        this.amount_due = amount_due;
    }

    public float getAmount_initial() {
        return amount_initial;
    }

    public void setAmount_initial(float amount_initial) {
        this.amount_initial = amount_initial;
    }

    public java.sql.Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(java.sql.Date payment_date) {
        this.payment_date = payment_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_id=" + payment_id +
                ", member_id=" + member_id +
                ", amount_due=" + amount_due +
                ", amount_initial=" + amount_initial +
                ", payment_date='" + payment_date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
