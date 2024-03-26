package com.vasanthvz.interviewpanel.managecandidate;

import com.vasanthvz.interviewpanel.datalayer.PanelDatabase;
import com.vasanthvz.interviewpanel.model.Candidate;

import java.util.List;
import java.util.Scanner;

public class ManageCandidateModel {

    private ManageCandidateView manageCandidateView;
    private PanelDatabase panelDatabase;
    public ManageCandidateModel(ManageCandidateView manageCandidateView) {
        this.manageCandidateView = manageCandidateView;
        panelDatabase = PanelDatabase.getInstance();

    }

    //        showText("1.Add Candidate");
    //        showText("2.Search Candidate");
    //        showText("3.View all Candidate");
    //        showText("4.Edit Candidate");
    //        showText("5.Remove Candidate");
    //        showText("0.Go back");
    public void redirectChoice(String choice) {
        switch (choice){
            case "1":{//add candidate COMPLETE
                manageCandidateView.getCandidateDetails();
                break;
            }
            case "2":{//search candidate    COMPLETE
                searchCandidate();
                break;
            }
            case "3":{//view all candidate  COMPLETE
                viewAllCandidate();
                break;
            }
            case "4":{//edit candidate  COMPLETE
                editCandidate();
                break;
            }
            case "5":{//remove candidate    COMPLETE
                removeCandidate();
                break;
            }
            case "0":{
                manageCandidateView.goBack();
                break;
            }
            default:{
                if(manageCandidateView.tryAgain()){
                    manageCandidateView.showMenu();
                }else{
                    redirectChoice("0");
                }
                break;
            }
        }
    }

    private void removeCandidate() {
        int id = manageCandidateView.getId();
        Candidate candidate = panelDatabase.getCandidate(id);
        if(candidate == null){
            System.out.println("Candidate not found");
        }else{
            panelDatabase.getCandidateList().remove(candidate);
            System.out.println("Candidate has been removed successfully");
        }
        manageCandidateView.showMenu();
    }

    private void editCandidate() {
        int id = manageCandidateView.getId();
        Candidate candidate = panelDatabase.getCandidate(id);
        if(candidate == null){
            manageCandidateView.showAlert("Candidate not found");
        }else {
            manageCandidateView.showText(candidate.getName() +"\t\t"+
                    "\t\t"+candidate.getEmailId());
            Scanner sc = new Scanner(System.in);
            manageCandidateView.showText("Enter candidate name :(press '0' if you don't want to change) ");
            String name = sc.next();
            manageCandidateView.showText("Enter the candidate email :(press '0' if you don't want to change)");
            String email = sc.next();
            if(! name.equals("0")){
                candidate.setName(name);
            }
            if(! email.equals("0")){
                if(panelDatabase.isCandidateEmailAvailable(email)){
                    candidate.setEmailId(email);
                }else{
                    manageCandidateView.showText("Email already present");
                }
            }
            manageCandidateView.showText("Candidate updated successfully");
        }
        manageCandidateView.showMenu();
    }

    private void viewAllCandidate() {
        System.out.println("Candidate ID\t\tCandidate Name\t\tEmail ID");
        for (Candidate candidate : panelDatabase.getCandidateList()){
            manageCandidateView.showText(candidate.getId()+"\t\t"+
                    candidate.getName() + "\t\t"+ candidate.getEmailId());
        }
        manageCandidateView.showMenu();
    }

    private void searchCandidate() {
        String name = manageCandidateView.getName();
         List<Candidate> list = panelDatabase.searchCandidate(name);
         for (Candidate candidate : list){
             manageCandidateView.showText(candidate.getId()+"\t\t"+
                     candidate.getName() + "\t\t"+ candidate.getEmailId());
         }
         manageCandidateView.showMenu();
    }


    public void addCandidate(String name, String emaiLId) {
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setEmailId(emaiLId);
        boolean candidateAdded = panelDatabase.insertCandidate(candidate);
        if(candidateAdded){
            manageCandidateView.showText("Candidate added successfully with id :"+candidate.getId());
        }else{
            manageCandidateView.showAlert("Email id already present!");
        }
        manageCandidateView.showMenu();
    }
}
