package com.anja.task1.app.view;

/**
 * Created by Anna on 19.05.2016.
 */
public interface MainView {

    void showLogInFacebook();
    void showUserProfileInformation(String userName, String photoUrl);
    void showMessage(String message);
    void openNavigation();
    void closeNavigation();
    boolean isNavigationOpen();
    void defaultOnBackPressed();




}
