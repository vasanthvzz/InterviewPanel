package com.vasanthvz.interviewpanel.interviewmenu;

public class InterviewMenuModel {
    private InterviewMenuView interviewMenuView;
    public InterviewMenuModel(InterviewMenuView interviewMenuView) {
        this.interviewMenuView = interviewMenuView;
    }

    public void redirectChoice(String choice) {
        //                1.Manage Candidate
        //                2.Manage User
        //                3.Update Interview
        //                4.Logout
        //                5.Exit
        switch (choice){
            case "1":{
                interviewMenuView.redirectManageCandidate();
                break;
            }
            case "2":{
                interviewMenuView.redirectManageInterviewer();
                break;
            }
            case "3":{
                interviewMenuView.redirectManageInterview();
                break;
            }
            case "4":{
                interviewMenuView.onLogout();
                break;
            }
            case "5":{
                interviewMenuView.onExit();
                break;
            }
            default:{
                if(interviewMenuView.tryAgain()){
                    interviewMenuView.showInterviewMenu();
                }else{
                    redirectChoice("5");
                }
                break;
            }
        }
    }
}
