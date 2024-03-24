package com.vasanthvz.interviewpanel.manageiterviewer;

import com.vasanthvz.interviewpanel.View;
import com.vasanthvz.interviewpanel.interviewmenu.InterviewMenuView;




import com.vasanthvz.interviewpanel.View;
import com.vasanthvz.interviewpanel.interviewmenu.InterviewMenuView;

public class ManageInterviewerView extends View {
    private ManageInterviewerModel manageInterviewerModel;
    public ManageInterviewerView() {
        manageInterviewerModel = new ManageInterviewerModel(this);
    }
    public void init(){
        showTitle("Manage Interviewer Menu");
        showMenu();
    }
    void showMenu() {
        showText("1.Add Interviewer");
        showText("2.Search Interviewer");
        showText("3.View all Interviewer");
        showText("4.Edit Interviewer");
        showText("5.Remove Interviewer");
        showText("0.Go back");
        manageInterviewerModel.redirectChoice(scanner.next());
    }


    public void goBack() {
        new InterviewMenuView().init();
    }
    public String getName(){
        showText("Enter the name of the interviewer : ");
        return scanner.next();
    }
    public int getId(){
        showAlert("Enter the id of the interviewer : ");
        return scanner.nextInt();
    }

    public void getInterviewerDetails() {
        showTitle("Add interviewer menu");
        showText("Enter the name of the interviewer : ");
        String name = scanner.next();
        showText("Enter the email id of the interviewer : ");
        String emailId = scanner.next();
        manageInterviewerModel.addInterviewer(name,emailId);
    }
}
