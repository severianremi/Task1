package com.anja.task1.app.view;

import com.anja.task1.app.view.impl.OrderPageItem;

import java.util.List;

import it.neokree.materialtabs.MaterialTabListener;

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
    String getDoneTabName();
    String getInWorkTabName();
    String getWaitTabName();
    void createTabs(List<OrderPageItem> orderPageItems, MaterialTabListener materialTabListener);
    void showTabPage(int tabPosition);




}
