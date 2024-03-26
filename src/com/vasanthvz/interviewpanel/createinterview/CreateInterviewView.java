package com.vasanthvz.interviewpanel.createinterview;

import com.vasanthvz.interviewpanel.View;
import com.vasanthvz.interviewpanel.interviewmenu.InterviewMenuView;
import com.vasanthvz.interviewpanel.model.Interview;

public class CreateInterviewView extends View {
    private CreteInterviewModel creteInterviewModel;
    public CreateInterviewView(){
        creteInterviewModel = new CreteInterviewModel(this);
    }
    public void init(){
        creteInterviewModel.startSetup();
    }
    public void initiateSetup() {
        showText("\n\n Enter the Interview details  : " );
        showText("Enter the Interview name : ");
        String interviewName = scanner.next();
        showText("Enter the number of candidates : ");
        int candidatesCount = scanner.nextInt();
        creteInterviewModel.createInterview(interviewName,candidatesCount);
    }

    public void onSetupComplete() {
            showText("\n\nInterview setup completed");
            showText("\n interview Name : "+
                    creteInterviewModel.interview.getName());
            new InterviewMenuView().init();
    }
}
