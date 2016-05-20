package com.anja.task1.app.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anja.task1.app.R;
import com.anja.task1.app.presenter.MainPresenter;
import com.anja.task1.app.presenter.MainPresenterImpl;
import com.anja.task1.app.view.MainView;
import com.facebook.login.LoginManager;
import com.software.shell.fab.ActionButton;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity
        implements MainView, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Bind(R.id.nav_footer)
    TextView mNavFooterView;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;
    @BindString(R.string.nav_footer_text)
    String mNavFooterText;
    @BindString(R.string.in_work_request_status_tab)
    String mInWorkTabText;
    @BindString(R.string.done_request_status_tab)
    String mDoneTabText;
    @BindString(R.string.wait_request_status_tab)
    String mWaitTabText;
    @Bind(R.id.request_status_vp)
    ViewPager mViewPager;
    @Bind(R.id.request_status_tab_host)
    MaterialTabHost mTabHost;
    @Bind(R.id.fab)
    ActionButton mFab;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindString(R.string.title_activity_main)
    String mActivityTitle;
    @Bind(R.id.nav_name_user)
    TextView mUserName;
    @Bind(R.id.nav_profile_photo)
    ImageView mProfilePhoto;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavFooterView.setText(Html.fromHtml(mNavFooterText));
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(mActivityTitle);
        mToolbar.setNavigationOnClickListener(this);
        createTabs();
    }

    //TODO Разобраться позже с фрагментами
    public ActionButton getFab() {
        return mFab;
    }

    private void createTabs(){
        RequestStatusFragmentPagerAdapter adapter = new RequestStatusFragmentPagerAdapter(
                getSupportFragmentManager(),
                new String[]{mInWorkTabText, mDoneTabText, mWaitTabText});
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabHost.setSelectedNavigationItem(position);
            }
        });
        for (int i = 0; i < adapter.getCount(); i++) {
            mTabHost.addTab(mTabHost.newTab()
                    .setText(adapter.getPageTitle(i))
                    .setTabListener(createTabListener()));
        }
    }

    private MaterialTabListener createTabListener() {
        return new MaterialTabListener() {
            @Override
            public void onTabSelected(MaterialTab materialTab) {
                mViewPager.setCurrentItem(materialTab.getPosition());
            }

            @Override
            public void onTabReselected(MaterialTab materialTab) {
            }

            @Override
            public void onTabUnselected(MaterialTab materialTab) {
            }
        };
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void defaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        mPresenter.onBackPressed();
    }

    @Override
    public boolean isNavigationOpen() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_login) {
            mPresenter.onFacebookLogInButtonPress();
        }
        mPresenter.onFinishNavigationItemSelected();
        return true;
    }

    @Override
    public void onClick(View v) {
        mPresenter.onNavigationButtonPress();
    }

    @Override
    public void openNavigation() {
        mDrawerLayout.openDrawer(mNavigationView);
    }

    @Override
    public void closeNavigation() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showLogInFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    @Override
    public void showUserProfileInformation(String userName, String photoUrl) {
        mUserName.setText(userName);
        Picasso.with(getApplicationContext()).load(photoUrl).into(mProfilePhoto);
    }

}
