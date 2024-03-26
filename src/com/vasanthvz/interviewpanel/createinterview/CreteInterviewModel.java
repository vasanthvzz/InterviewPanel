package com.vasanthvz.interviewpanel.createinterview;

import com.vasanthvz.interviewpanel.InterviewPanel;
import com.vasanthvz.interviewpanel.datalayer.PanelDatabase;
import com.vasanthvz.interviewpanel.model.Interview;

public class CreteInterviewModel {
    private CreateInterviewView createInterviewView;
    Interview interview;
    public CreteInterviewModel(CreateInterviewView createInterviewView) {
        this.createInterviewView = createInterviewView;
        this.interview = PanelDatabase.getInstance().getInterview();
    }

    public void startSetup(){
        if(interview == null){
            createInterviewView.initiateSetup();
        }else{
            createInterviewView.onSetupComplete();
        }
    }

    public void createInterview(String libraryName, int candidatesCount) {
        interview = new Interview();
        this.interview.setName(libraryName);
        this.interview.setCandidateCount(candidatesCount);
        startSetup();
    }
}
