package com.anja.task1.app.presenter;

import android.content.Intent;

/**
 * Created by Anna on 20.05.2016.
 */
public interface MainPresenter {

    void onFacebookLogInButtonPress();
    void onCreate();
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onNavigationButtonPress();
    void onFinishNavigationItemSelected();
    void onBackPressed();

    void onRefreshButtonPress();
}
