package com.vasanthvz.interviewpanel.manageiterviewer;

import com.vasanthvz.interviewpanel.datalayer.PanelDatabase;
import com.vasanthvz.interviewpanel.model.Interviewer;

import java.util.List;
import java.util.Scanner;

public class ManageInterviewerModel {


    private ManageInterviewerView manageInterviewerView;
    private PanelDatabase panelDatabase;
    public ManageInterviewerModel(ManageInterviewerView manageInterviewerView) {
        this.manageInterviewerView = manageInterviewerView;
        panelDatabase = PanelDatabase.getInstance();

    }

    //        showText("1.Add Interviewer");
    //        showText("2.Search Interviewer");
    //        showText("3.View all Interviewer");
    //        showText("4.Edit Interviewer");
    //        showText("5.Remove Interviewer");
    //        showText("0.Go back");
    public void redirectChoice(String choice) {
        switch (choice){
            case "1":{//add interviewer COMPLETE
                manageInterviewerView.getInterviewerDetails();
                break;
            }
            case "2":{//search interviewer    COMPLETE
                searchInterviewer();
                break;
            }
            case "3":{//view all interviewer  COMPLETE
                viewAllInterviewer();
                break;
            }
            case "4":{//edit interviewer  COMPLETE
                editInterviewer();
                break;
            }
            case "5":{//remove interviewer    COMPLETE
                removeInterviewer();
                break;
            }
            case "0":{
                manageInterviewerView.goBack();
                break;
            }
            default:{
                if(manageInterviewerView.tryAgain()){
                    manageInterviewerView.showMenu();
                }else{
                    redirectChoice("0");
                }
                break;
            }
        }
    }

    private void removeInterviewer() {
        int id = manageInterviewerView.getId();
        Interviewer interviewer = panelDatabase.getInterviewer(id);
        if(interviewer == null){
            System.out.println("Interviewer not found");
        }else{
            panelDatabase.getInterviewerList().remove(interviewer);
            System.out.println("Interviewer has been removed successfully");
        }
        manageInterviewerView.showMenu();
    }

    private void editInterviewer() {
        int id = manageInterviewerView.getId();
        Interviewer interviewer = panelDatabase.getInterviewer(id);
        if(interviewer == null){
            manageInterviewerView.showAlert("Interviewer not found");
        }else {
            manageInterviewerView.showText(interviewer.getName() +"\t\t");
            Scanner sc = new Scanner(System.in);
            manageInterviewerView.showText("Enter interviewer name :(press '0' if you don't want to change) ");
            String name = sc.next();
            manageInterviewerView.showText("Enter interviewer email :(press '0' if you don't want to change) ");
            String email = sc.next();
            if(! name.equals("0")){
                interviewer.setName(name);
            }
            if(! email.equals(")")){
                if(panelDatabase.isInteriviewerEmailAvailable(email)){
                    interviewer.setEmailId(email);
                }else {
                    System.out.println("Email id already present");
                }
            }
            manageInterviewerView.showText("Interviewer updated successfully");
        }
        manageInterviewerView.showMenu();
    }

    private void viewAllInterviewer() {
        for (Interviewer interviewer : panelDatabase.getInterviewerList()){
            manageInterviewerView.showText(interviewer.getId()+"\t\t"+
                    interviewer.getName() + "\t\t"+interviewer.getEmailId());
        }
        manageInterviewerView.showMenu();
    }

    private void searchInterviewer() {
        String name = manageInterviewerView.getName();
        List<Interviewer> list = panelDatabase.searchInterviewer(name);
        for (Interviewer interviewer : list){
            manageInterviewerView.showText(interviewer.getId()+"\t\t"+
                    interviewer.getName() + "\t\t"+ interviewer.getEmailId());
        }
        manageInterviewerView.showMenu();
    }


    public void addInterviewer(String name, String emaiLId) {
        Interviewer interviewer = new Interviewer();
        interviewer.setName(name);
        interviewer.setEmailId(emaiLId);
        boolean interviewerAdded = panelDatabase.insertInterviewer(interviewer);
        if(interviewerAdded){
            manageInterviewerView.showText("Interviewer added successfully with id :"+interviewer.getId());
        }else{
            manageInterviewerView.showAlert("Email id already present!");
        }
        manageInterviewerView.showMenu();
    }
}
