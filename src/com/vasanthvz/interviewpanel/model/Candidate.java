package com.vasanthvz.interviewpanel.model;

public class Candidate {
    private String name;
    private int id;
    private String emailId;
    private int quePosition;
    private int mark;
    private String remarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuePosition() {
        return quePosition;
    }

    public void setQuePosition(int quePosition) {
        this.quePosition = quePosition;
    }


    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
