package com.vasanthvz.interviewpanel.managecandidate;

import com.vasanthvz.interviewpanel.View;
import com.vasanthvz.interviewpanel.interviewmenu.InterviewMenuView;

public class ManageCandidateView extends View {
    private ManageCandidateModel manageCandidateModel;
    public ManageCandidateView() {
        manageCandidateModel = new ManageCandidateModel(this);
    }
    public void init(){
        showTitle("Manage Candidate Menu");
        showMenu();
    }
    void showMenu() {
        showText("1.Add Candidate");
        showText("2.Search Candidate");
        showText("3.View all Candidate");
        showText("4.Edit Candidate");
        showText("5.Remove Candidate");
        showText("0.Go back");
        manageCandidateModel.redirectChoice(scanner.next());
    }


    public void goBack() {
        new InterviewMenuView().init();
    }
    public String getName(){
        showText("Enter the name of the candidate : ");
        return scanner.next();
    }
    public int getId(){
        showAlert("Enter the id of the candidate : ");
        return scanner.nextInt();
    }

    public void getCandidateDetails() {
        showTitle("Add candidate menu");
        showText("Enter the name of the candidate : ");
        String name = scanner.next();
        showText("Enter the email id of the candidate : ");
        String emailId = scanner.next();
        manageCandidateModel.addCandidate(name,emailId);
    }
}
