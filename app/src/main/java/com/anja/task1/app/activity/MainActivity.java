package com.anja.task1.app.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import com.anja.task1.app.R;
import com.anja.task1.app.fragment.InWorkOrDoneFragment;
import com.anja.task1.app.fragment.WaitFragment;
import com.software.shell.fab.ActionButton;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Bind(R.id.nav_footer)
    TextView navFooterView;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @BindString(R.string.nav_footer_text)
    String navFooterText;
    @BindString(R.string.in_work_request_status_tab)
    String inWorkTabText;
    @BindString(R.string.done_request_status_tab)
    String doneTabText;
    @BindString(R.string.wait_request_status_tab)
    String waitTabText;
    @Bind(R.id.request_status_vp)
    ViewPager viewPager;
    @Bind(R.id.request_status_tab_host)
    MaterialTabHost tabHost;
    @Bind(R.id.fab)
    ActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindString(R.string.title_activity_main)
    String activityTitle;

    public ActionButton getFab() {
        return fab;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setNavigationItemSelectedListener(this);
        navFooterView.setText(Html.fromHtml(navFooterText));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(activityTitle);
        toolbar.setNavigationOnClickListener(this);
        RequestStatusFragmentPagerAdapter adapter = new RequestStatusFragmentPagerAdapter(
                getSupportFragmentManager(),
                new String[]{inWorkTabText, doneTabText, waitTabText});
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
               tabHost.setSelectedNavigationItem(position);
            }
        });
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(createTabListener()));
        }
    }

    private MaterialTabListener createTabListener(){
        return new MaterialTabListener() {
            @Override
            public void onTabSelected(MaterialTab materialTab) {
                viewPager.setCurrentItem(materialTab.getPosition());
            }

            @Override
            public void onTabReselected(MaterialTab materialTab) {}

            @Override
            public void onTabUnselected(MaterialTab materialTab) {}
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        drawerLayout.openDrawer(navigationView);
    }

    private static class RequestStatusFragmentPagerAdapter extends FragmentStatePagerAdapter { //[Comment] Should be external

        private String[] mTabNames;

        public RequestStatusFragmentPagerAdapter(FragmentManager fm, String[] tabNames) {
            super(fm);
            mTabNames = tabNames;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    InWorkOrDoneFragment fragment = new InWorkOrDoneFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(InWorkOrDoneFragment.TYPE_KEY, InWorkOrDoneFragment.IN_WORK);
                    fragment.setArguments(bundle); //[Comment] Copy/Paste code. Create please one method
                    return fragment;
                }
                case 1: {
                    InWorkOrDoneFragment fragment = new InWorkOrDoneFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(InWorkOrDoneFragment.TYPE_KEY, InWorkOrDoneFragment.DONE);
                    fragment.setArguments(bundle);
                    return fragment;
                }
                case 2: //[Comment] Magic numbers
                    return new WaitFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3; //[Comment] Magic numbers
        }
    }
}
