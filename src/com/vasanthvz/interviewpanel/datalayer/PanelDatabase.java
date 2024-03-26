package com.vasanthvz.interviewpanel.datalayer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vasanthvz.interviewpanel.model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PanelDatabase {
    private static PanelDatabase panelDatabase;
    private Interview interview;
    private static Gson gson;
    private List<Candidate> candidateList = new ArrayList<>();
    private List<Interviewer> interviewerList = new ArrayList<>();
    private List<Session> sessionList = new ArrayList<>();
    private static Counter counter;
    public static PanelDatabase getInstance() {
        if(panelDatabase == null){
            panelDatabase = new PanelDatabase();
        }
        if(gson == null){
            gson = new Gson();
        }
        return panelDatabase;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public List<Interviewer> getInterviewerList() {
        return interviewerList;
    }

    public Interview getInterview(){
        return interview;
    }

    public Candidate getCandidate(int id){
        for(Candidate candidate : candidateList){
            if(candidate.getId()==id){
                return candidate;
            }
        }
        return null;
    }
    public List<Candidate> searchCandidate(String name){
        List<Candidate> searchResult = new ArrayList<>();
        for(Candidate candidate : candidateList){
            if(candidate.getName().contains(name)){
                searchResult.add(candidate);
            }
        }
        return searchResult;
    }
    public boolean isCandidateEmailAvailable(String email){
        for(Candidate candidate : candidateList){
            if(candidate.getEmailId().equals(email)){
                return false;
            }
        }
        return true;
    }
    public boolean isInterviewerEmailAvailable(String email){
        for (Interviewer interviewer: interviewerList){
            if(interviewer.getEmailId().equals(email)){
                return false;
            }
        }
        return true;
    }

    public boolean insertSession(Session session){
        boolean hasCandidate = false;
        for(Session session1 : sessionList){
            if(session.getCandidate().getEmailId().equals(session1.getCandidate().getEmailId())){
                hasCandidate = true;
                break;
            }
        }
        if(hasCandidate){
            System.out.println("Candidate is already assigned in another session!");
            return false;
        }else {
            session.setId(getSessionCounter());
            sessionList.add(session);
            return true;
        }
    }
    public List<Session> getSessionList(){
        return sessionList;
    }
    public Session getSession(int id){
        for(Session session : sessionList){
            if(session.getId() == id){
                return session;
            }
        }
        return null;
    }

    public boolean insertCandidate(Candidate candidate){
        boolean hasCandidate = false;
        for(Candidate candidate1 : candidateList){
            if(candidate1.getEmailId().equals(candidate.getEmailId())){
                hasCandidate = true;
                break;
            }
        }
        if(hasCandidate){
            return false;
        }else{
            candidate.setId(getCandidateCounter());
            candidateList.add(candidate);
        }
        return true;
    }


    private int getCandidateCounter() {
        counter.incrementCandidateCounter();
        return counter.getCandidateCounter();
    }

    private int getInterviewerCounter(){
        counter.incrementInterviewerCounter();
        return counter.getInterviewerCounter();
    }
    private int getSessionCounter(){
        counter.incrementSessionCounter();
        return counter.getSessionCounter();
    }

    public Interviewer getInterviewer(int id) {
        for(Interviewer interviewer : interviewerList){
            if(interviewer.getId()==id){
                return interviewer;
            }
        }
        return null;
    }

    public List<Interviewer> searchInterviewer(String name) {
        List<Interviewer> searchResult = new ArrayList<>();
        for (Interviewer interviewer : interviewerList){
            if(interviewer.getName().contains(name)){
                searchResult.add(interviewer);
            }
        }
        return searchResult;
    }

    public boolean insertInterviewer(Interviewer interviewer) {
        boolean hasInteviewer = false;
        for (Interviewer interviewer1 : interviewerList){
            if(interviewer.getEmailId().equals(interviewer1.getEmailId())){
                hasInteviewer = true;
                break;
            }
        }
        if (hasInteviewer){
            return false;
        }else {
            interviewer.setId(getInterviewerCounter());
            interviewerList.add(interviewer);
            return true;
        }
    }


    //FETCHING DATA
    public void loadData(){
        String json = readData("candidates");
        Type listType = new TypeToken<List<Candidate>>(){}.getType();
        candidateList = gson.fromJson(json, listType);
        json = readData("interviewers");
        listType = new TypeToken<List<Interviewer>>(){}.getType();
        interviewerList = gson.fromJson(json,listType);
        json = readData("sessions");
        listType = new TypeToken<List<Session>>(){}.getType();
        sessionList = gson.fromJson(json,listType);
        counter = readCountersFromFile();
        nullCheck();

    }
    private void nullCheck(){
        if(candidateList==null){candidateList=new ArrayList<>();};
        if(interviewerList==null){interviewerList=new ArrayList<>();};
        if(sessionList==null){sessionList=new ArrayList<>();};
    }

    private String readData(String name){
        String json = "";
        try (Reader reader = new FileReader("data/"+name+".json")) {
            StringBuilder stringBuilder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            json = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    //STORING DATA
    public void saveData(){
        String json = gson.toJson(candidateList);
        writeJson(json,"candidates");
        json = gson.toJson(interviewerList);
        writeJson(json,"interviewers");
        json = gson.toJson(sessionList);
        writeJson(json,"sessions");
        json = gson.toJson(counter);
        writeJson(json,"counters");
    }


    public void writeJson(String json,String name){
        try (FileWriter writer = new FileWriter("data/"+name+".json")) {
            writer.write(json);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Counter readCountersFromFile() {
        try {
            File file = new File("data/counters.json");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();

                String jsonString = new String(data);
                Gson gson = new Gson();
                return gson.fromJson(jsonString, Counter.class);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new Counter(0,0,0);
    }
}
