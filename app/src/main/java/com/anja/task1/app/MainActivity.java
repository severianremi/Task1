package com.anja.task1.app;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



//[Comment] Wrong status bar color
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setOnClickListenerForChildren((ViewGroup) findViewById(R.id.mainLayout)); //[Comment] Please don't use upper-case in id-names

        RecyclerView rv = (RecyclerView) findViewById(R.id.images_layout_id); //Comment rv replace to recyclerViewImages (more informative)
        LinearLayoutManager lm
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(lm);
        TaskImageRecycleViewAdapter adapter = new TaskImageRecycleViewAdapter();
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0); //[Comment] Just call finish()
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        String msg = v.getClass().getSimpleName() + " id: " + v.getResources().getResourceEntryName(v.getId());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void setOnClickListenerForChildren(ViewGroup parent) {
        for (int i = parent.getChildCount() - 1; i >= 0; i--) {  //[Comment] for(int i= 0; i < parent.getChildCount() - 1; i++)
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                setOnClickListenerForChildren((ViewGroup) child);
            } else {
                if (child != null && child instanceof TextView) {
                    child.setOnClickListener(this);
                }
            }
        }
    }

}
