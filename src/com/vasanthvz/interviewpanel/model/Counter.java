package com.vasanthvz.interviewpanel.model;

public class Counter {
    private int interviewerCounter;
    private int sessionCounter;
    private int candidateCounter;

    public Counter(int candidateCounter,int interviewerCounter,int sessionCounter){
        this.interviewerCounter = interviewerCounter;
        this.sessionCounter = sessionCounter;
        this.candidateCounter = candidateCounter;
    }

    public int getInterviewerCounter() {
        return interviewerCounter;
    }

    public void setInterviewerCounter(int interviewerCounter) {
        this.interviewerCounter = interviewerCounter;
    }
    public void incrementInterviewerCounter(){
        interviewerCounter++;
    }
    public void decrementInterviewerCounter(){
        interviewerCounter--;
    }
    public void incrementCandidateCounter(){
        candidateCounter++;
    }
    public void decrementCandidateCounter(){
        candidateCounter--;
    }
    public void incrementSessionCounter(){
        interviewerCounter++;
    }
    public void decrementSessionCounter(){
        interviewerCounter--;
    }

    public int getSessionCounter() {
        return sessionCounter;
    }

    public void setSessionCounter(int sessionCounter) {
        this.sessionCounter = sessionCounter;
    }

    public int getCandidateCounter() {
        return candidateCounter;
    }

    public void setCandidateCounter(int candidateCounter) {
        this.candidateCounter = candidateCounter;
    }
}
