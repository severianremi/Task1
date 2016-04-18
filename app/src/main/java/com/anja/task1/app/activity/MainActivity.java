package com.anja.task1.app.activity;

import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

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
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(new MaterialTabListener() {
                                @Override
                                public void onTabSelected(MaterialTab materialTab) {
                                    viewPager.setCurrentItem(materialTab.getPosition());
                                }

                                @Override
                                public void onTabReselected(MaterialTab materialTab) {

                                }

                                @Override
                                public void onTabUnselected(MaterialTab materialTab) {

                                }
                            })
            );
        }

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
//
//        if (id == R.id.nav_camara) {
//            ftrans.replace(R.id.container, fimport);
//        } else if (id == R.id.nav_gallery) {
//            ftrans.replace(R.id.container, fgallery);
//
//        } else if (id == R.id.nav_slideshow) {
//            ftrans.replace(R.id.container, fshow);
//
//        } else if (id == R.id.nav_manage) {
//            ftrans.replace(R.id.container, ftools);
//
//        } else if (id == R.id.nav_share) {
//            ftrans.replace(R.id.container, fshare);
//
//        } else if (id == R.id.nav_send) {
//            ftrans.replace(R.id.container, fsend);
//
//        } ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        drawerLayout.openDrawer(navigationView);
    }

    private static class RequestStatusFragmentPagerAdapter extends FragmentStatePagerAdapter {

        String[] mTabNames;

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
                    fragment.setArguments(bundle);
                    return fragment;
                }
                case 1: {
                    InWorkOrDoneFragment fragment = new InWorkOrDoneFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(InWorkOrDoneFragment.TYPE_KEY, InWorkOrDoneFragment.DONE);
                    fragment.setArguments(bundle);
                    return fragment;
                }
                case 2:
                    return new WaitFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
