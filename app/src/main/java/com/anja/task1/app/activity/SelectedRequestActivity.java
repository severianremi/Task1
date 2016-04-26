package com.anja.task1.app.activity;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.anja.task1.app.DataModelApplication;
import com.anja.task1.app.R;
import com.anja.task1.app.Request;
import com.anja.task1.app.util.DateConverter;


public class SelectedRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private RequestImageRecycleViewAdapter mImageAdapter;
    @Bind(R.id.selected_request_main_sv)
    ViewGroup mMainSv;
    @Bind(R.id.selected_request_images_rv)
    RecyclerView mImagesRv;
    @Bind(R.id.selected_request_title)
    TextView mRequestTitle;
    @Bind(R.id.selected_request_status)
    TextView mRequestStatus;
    @Bind(R.id.selected_request_create_date)
    TextView mRequestCreateDate;
    @Bind(R.id.selected_request_register_date)
    TextView mRequestRegisterDate;
    @Bind(R.id.selected_request_deadline_date)
    TextView mRequestDeadlineDate;
    @Bind(R.id.selected_request_responsible)
    TextView mRequestResponsible;
    @Bind(R.id.selected_request_text)
    TextView mRequestText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_request);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setOnClickListenerForChildren(mMainSv);

        LinearLayoutManager lm
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mImagesRv.setLayoutManager(lm);
        mImageAdapter = new RequestImageRecycleViewAdapter();
        mImagesRv.setAdapter(mImageAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Request request = DataModelApplication.getSelectedRequest();
        mRequestTitle.setText(request.getTitle());
        String status = getResources().getString(request.getStatus().getTitleId());
        mRequestStatus.setText(status);
        mRequestCreateDate.setText(DateConverter.toSelectedRequestFormat(request.getCreateDate()));
        mRequestRegisterDate.setText(DateConverter.toSelectedRequestFormat(request.getRegisterDate()));
        mRequestDeadlineDate.setText(DateConverter.toSelectedRequestFormat(request.getDeadlineDate()));
        mRequestResponsible.setText(request.getResponsible());
        mRequestText.setText(request.getText());
        mImageAdapter.setImages(request.getImages());
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
