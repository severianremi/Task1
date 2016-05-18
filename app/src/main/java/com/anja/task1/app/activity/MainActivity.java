package com.anja.task1.app.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

import com.anja.task1.app.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.software.shell.fab.ActionButton;
import com.squareup.picasso.Picasso;


import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

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

    private CallbackManager mCallbackManager;


    public ActionButton getFab() {
        return mFab;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Login to Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("Success", "Login");
                        //Download profile from Facebook
                        getUserProfileInformation();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        //-------------------------

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavFooterView.setText(Html.fromHtml(mNavFooterText));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mActivityTitle);
        toolbar.setNavigationOnClickListener(this);
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        mDrawerLayout.openDrawer(mNavigationView);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private Bundle getUserProfileInformation() {
        Bundle params = new Bundle();
        params.putString("fields", "id,name, email,gender,cover,picture.type(large)");
        new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        if (response != null) {
                            try {
                                JSONObject data = response.getJSONObject();
                                mUserName.setText(data.getString("name"));
                                if (data.has("picture")) {
                                    String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                                    Picasso.with(getApplicationContext()).load(profilePicUrl).into(mProfilePhoto);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).executeAsync();
        return params;
    }
}
