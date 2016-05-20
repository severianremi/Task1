package com.anja.task1.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;

import it.neokree.materialtabs.MaterialTabHost;

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
}
