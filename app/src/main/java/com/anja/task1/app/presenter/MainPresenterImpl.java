package com.anja.task1.app.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.anja.task1.app.data.Order;
import com.anja.task1.app.view.MainView;
import com.anja.task1.app.view.impl.OrderListFragment;
import com.anja.task1.app.view.impl.OrderPageItem;
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

import java.util.Arrays;
import java.util.List;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by Anna on 19.05.2016.
 */
public class MainPresenterImpl implements MainPresenter, FacebookCallback<LoginResult>, MaterialTabListener {

    private MainView mMainView;
    private CallbackManager mCallbackManager;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void onCreate() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, this);
        mMainView.createTabs(createTicketPages(), this);
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


    public List<OrderPageItem> createTicketPages() {
        return Arrays.asList(
                new OrderPageItem(mMainView.getInWorkTabName(), createFragment(Order.Status.IN_WORK)),
                new OrderPageItem(mMainView.getDoneTabName(), createFragment(Order.Status.DONE)),
                new OrderPageItem(mMainView.getWaitTabName(), createFragment(Order.Status.WAIT))
        );
    }

    private OrderListFragment createFragment(Order.Status dataType) {
        OrderListFragment fragment = new OrderListFragment();
        OrderListPresenter orderListPresenter = new OrderListPresenterImpl(fragment, dataType);
        fragment.setOrderListPresenter(orderListPresenter);
        return fragment;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        mMainView.showTabPage(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }
}
