package com.vasanthvz.interviewpanel.manageinterview;

import com.vasanthvz.interviewpanel.View;

public class ManageInterviewView extends View {
    private ManageInterviewModel manageInterviewModel;
    public ManageInterviewView(){
        manageInterviewModel = new ManageInterviewModel(this);
    }
    public void init(){
        showMenu();
    }
    public void showMenu(){
        showText("""
                1. Create Session
                2. View all Session
                3. Set Session Status
                4. Go back
                """);
        manageInterviewModel.redirectChoice(scanner.next());
    }


    public void getSessionDetails() {
        showText("Enter the candidate Id : ");
        int candidateId = scanner.nextInt();
        showText("Enter the interviewer id : ");
        int interviewerId = scanner.nextInt();
        manageInterviewModel.createSession(candidateId,interviewerId);
    }

    public int getSessionId() {
        showText("Enter session id  :");
        return scanner.nextInt();
    }
}
