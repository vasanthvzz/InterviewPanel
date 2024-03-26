package com.vasanthvz.interviewpanel;

import com.vasanthvz.interviewpanel.datalayer.PanelDatabase;
import com.vasanthvz.interviewpanel.login.LoginView;

public class InterviewPanel {
    private static InterviewPanel interviewPanel;

    private String appName = "Interview Panel Application";
    private String appVersion = "1.0.0";

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    InterviewPanel() {

    }

    public static InterviewPanel getInstance() {
        if (interviewPanel == null) {
            interviewPanel = new InterviewPanel();
        }
        return interviewPanel;
    }

    private void create() {
        LoginView loginView = new LoginView();
        loginView.init();
    }

    public static void main(String[] args) {
        PanelDatabase.getInstance().loadData();
        InterviewPanel.getInstance().create();
        PanelDatabase.getInstance().saveData();
    }
}
