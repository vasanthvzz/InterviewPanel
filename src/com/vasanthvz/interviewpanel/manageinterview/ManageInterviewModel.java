package com.vasanthvz.interviewpanel.manageinterview;

import com.vasanthvz.interviewpanel.datalayer.PanelDatabase;
import com.vasanthvz.interviewpanel.enums.state;
import com.vasanthvz.interviewpanel.interviewmenu.InterviewMenuView;
import com.vasanthvz.interviewpanel.model.Candidate;
import com.vasanthvz.interviewpanel.model.Interviewer;
import com.vasanthvz.interviewpanel.model.Session;

import java.awt.*;
import java.util.Scanner;

public class ManageInterviewModel {
    private ManageInterviewView manageInterviewView;
    private PanelDatabase panelDatabase;
    public ManageInterviewModel(ManageInterviewView manageInterviewView) {
    this.manageInterviewView = manageInterviewView;
    this.panelDatabase = PanelDatabase.getInstance();
    }

    public void redirectChoice(String choice) {
        switch (choice){
            case "1":{
                manageInterviewView.getSessionDetails();
                break;
            }
            case "2":{
                viewAllSession();
                break;
            }
            case "3":{
                setSessionStatus();
                break;
            }
            case "4":{
                new InterviewMenuView().init();
                break;
            }
            default: {
                if(manageInterviewView.tryAgain()){
                    manageInterviewView.showMenu();
                }else{
                    redirectChoice("4");
                }
            }

        }
    }

    private void setSessionStatus() {
        int id = manageInterviewView.getSessionId();
        Session session = panelDatabase.getSession(id);
        if(session == null){
            System.out.println("Session not found !");
        }else{
            System.out.println("The current status is "+session.getStatus());
            System.out.println("1.not started\n2.Started\n3.Completed");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice){
                case "1":{
                    session.setStatus(state.NOT_YET_STARTED);
                    break;
                }
                case "2":{
                    session.setStatus(state.STARTED);
                    break;
                }
                case "3":{
                    session.setStatus(state.COMPLETED);
                    break;
                }
                default:{
                    System.out.println("Please enter a valid option");
                }
            }
            manageInterviewView.showMenu();
        }
    }

    private void viewAllSession() {
        System.out.println("Session ID \t\t Candidate Name \t\t" +
                "Interviewer Name \t\t Session Status");
        for (Session session : panelDatabase.getSessionList()){
            System.out.println(session.getId()+"\t\t\t"+session.getCandidate().getName()+"\t\t\t"+
                    session.getInterviewer().getName()+"\t\t\t"+session.getStatus());
        }
        manageInterviewView.showMenu();
    }

    public void createSession(int candidateId, int interviewerId) {
        Interviewer interviewer = panelDatabase.getInterviewer(interviewerId);
        Candidate candidate = panelDatabase.getCandidate(candidateId);
        Session session = new Session();
        session.setInterviewer(interviewer);
        session.setCandidate(candidate);
        panelDatabase.insertSession(session);
        manageInterviewView.showMenu();
    }
}
