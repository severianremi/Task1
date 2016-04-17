package com.anja.task1.app.activity;


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
import butterknife.Bind;
import butterknife.ButterKnife;
import com.anja.task1.app.DataModelApplication;
import com.anja.task1.app.R;
import com.anja.task1.app.Request;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class SelectedRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("dd MM yyyy");
    private DataModelApplication mDataModel;
    private RequestImageRecycleViewAdapter mImageAdapter;

    @Bind(R.id.selected_request_main_sv)
    ViewGroup mainSv;
    @Bind(R.id.selected_request_images_rv)
    RecyclerView imagesRv;

    @Bind(R.id.selected_request_title)
    TextView requestTitle;
    @Bind(R.id.selected_request_status)
    TextView requestStatus;
    @Bind(R.id.selected_request_create_date)
    TextView requestCreateDate;
    @Bind(R.id.selected_request_register_date)
    TextView requestRegisterDate;
    @Bind(R.id.selected_request_deadline_date)
    TextView requestDeadlineDate;
    @Bind(R.id.selected_request_responsible)
    TextView requestResponsible;
    @Bind(R.id.selected_request_text)
    TextView requestText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_request);
        mDataModel = (DataModelApplication) getApplication();
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setOnClickListenerForChildren(mainSv);

        LinearLayoutManager lm
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        imagesRv.setLayoutManager(lm);
        mImageAdapter = new RequestImageRecycleViewAdapter();
        imagesRv.setAdapter(mImageAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Request request = mDataModel.getSelectedRequest();
        requestTitle.setText(request.getTitle());
        String status = getResources().getString(request.getStatus().getTitleId());
        requestStatus.setText(status);
        requestCreateDate.setText(DATE_TIME_FORMATTER.print(request.getCreateDate()));
        requestRegisterDate.setText(DATE_TIME_FORMATTER.print(request.getRegisterDate()));
        requestDeadlineDate.setText(DATE_TIME_FORMATTER.print(request.getDeadlineDate()));
        requestResponsible.setText(request.getResponsible());
        requestText.setText(request.getText());
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
