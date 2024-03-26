package com.vasanthvz.interviewpanel.login;

import com.vasanthvz.interviewpanel.InterviewPanel;
import com.vasanthvz.interviewpanel.View;
import com.vasanthvz.interviewpanel.createinterview.CreateInterviewView;

public class LoginView extends View {
    private LoginModel loginModel;
    public LoginView(){
        loginModel = new LoginModel(this);
    }
    public void init() {
        showTitle(InterviewPanel.getInstance().getAppName());
        showText("version:"+InterviewPanel.getInstance().getAppVersion());
        showText("Please login to proceed....");
        proceedLogin();
    }

    private void proceedLogin() {
        showText("Enter your user name : ");
        String userName = scanner.next();
        showText("Enter your password : ");
        String password =scanner.next();
        loginModel.validateUser(userName , password);
    }

    public void onLoginFail(){
        checkForLogin();
    }
    private void checkForLogin(){
        if(tryAgain()){
            proceedLogin();
        }else {
            showTitle("Thank you");
        }
    }
    public void onSuccess() {
        showText("Login successful.......");
        showTitle("Welcome to "+InterviewPanel.getInstance().getAppName());
        new CreateInterviewView().init();
    }
}
