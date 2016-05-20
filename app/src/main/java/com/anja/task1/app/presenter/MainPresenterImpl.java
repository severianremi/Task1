package com.anja.task1.app.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.anja.task1.app.view.MainView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

/**
 * Created by Anna on 19.05.2016.
 */
public class MainPresenterImpl implements MainPresenter, FacebookCallback<LoginResult> {

    private MainView mMainView;
    private CallbackManager mCallbackManager;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void onCreate() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onFacebookLogInButtonPress() {
        mMainView.showLogInFacebook();
    }

    @Override
    public void onNavigationButtonPress() {
        mMainView.openNavigation();
    }

    @Override
    public void onFinishNavigationItemSelected() {
        mMainView.closeNavigation();
    }

    @Override
    public void onBackPressed() {
        if (mMainView.isNavigationOpen()) {
            mMainView.closeNavigation();
        } else {
            mMainView.defaultOnBackPressed();
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        getAndShowUserProfileInformation();
    }

    private void getAndShowUserProfileInformation() {
        Bundle params = new Bundle();
        params.putString("fields", "id,name, email,gender,cover,picture.type(large)");
        new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        if (response != null) {
                            try {
                                JSONObject data = response.getJSONObject();
                                String userName = data.getString("name");
                                String profilePicUrl = null;
                                if (data.has("picture")) {
                                    profilePicUrl = data.getJSONObject("picture")
                                            .getJSONObject("data").getString("url");
                                }
                                mMainView.showUserProfileInformation(userName, profilePicUrl);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).executeAsync();
    }

    @Override
    public void onCancel() {
        mMainView.showMessage("Login Cancel");
    }

    @Override
    public void onError(FacebookException error) {
        mMainView.showMessage(error.getMessage());
    }
}
