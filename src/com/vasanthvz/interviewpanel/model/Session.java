package com.vasanthvz.interviewpanel.model;


import com.vasanthvz.interviewpanel.enums.state;

public class Session {
    private int id;
    private Candidate candidate;
    private Interviewer interviewer;

    private state status = state.NOT_YET_STARTED;

    public state getStatus() {
        return status;
    }

    public void setStatus(state status) {
        this.status = status;
    }



    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
