package com.vasanthvz.interviewpanel.interviewmenu;

import com.vasanthvz.interviewpanel.View;
import com.vasanthvz.interviewpanel.login.LoginView;
import com.vasanthvz.interviewpanel.managecandidate.ManageCandidateView;
import com.vasanthvz.interviewpanel.manageinterview.ManageInterviewView;
import com.vasanthvz.interviewpanel.manageinterviewer.ManageInterviewerView;


public class InterviewMenuView extends View {
    private InterviewMenuModel interviewMenuModel;
    public InterviewMenuView(){
        interviewMenuModel = new InterviewMenuModel(this);
    }
    public void init() {
        showTitle("Interview Menu");
        showInterviewMenu();
    }

    void showInterviewMenu() {
        showText("""
                1.Manage Candidate
                2.Manage Interviewer
                3.Manage Interview
                4.Logout
                5.Exit
                """);
        showText("Enter your choice : ");
        interviewMenuModel.redirectChoice(scanner.next());
    }

    public void redirectManageCandidate() {
        new ManageCandidateView().init();
    }

    public void redirectManageInterviewer() {
        new ManageInterviewerView().init();
    }

    public void redirectManageInterview() {
        new ManageInterviewView().init();
    }

    public void onLogout() {
        new LoginView().init();
    }
}
