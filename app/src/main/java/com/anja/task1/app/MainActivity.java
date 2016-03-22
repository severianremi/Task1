package com.anja.task1.app;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setOnClickListenerForChildren((ViewGroup) findViewById(R.id.main_layout));

        RecyclerView recyclerViewImages = (RecyclerView) findViewById(R.id.images_layout_id);
        LinearLayoutManager lm
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewImages.setLayoutManager(lm);
        TaskImageRecycleViewAdapter adapter = new TaskImageRecycleViewAdapter();
        recyclerViewImages.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
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
        for (int i = 0; i <= parent.getChildCount() - 1; i++) {
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
