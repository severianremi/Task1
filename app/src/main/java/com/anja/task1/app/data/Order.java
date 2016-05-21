package com.anja.task1.app.data;

import com.anja.task1.app.R;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Anna on 16.04.2016.
 */
public class Order {

    private String mTitle;
    private Status mStatus;
    private DateTime mCreateDate;
    private DateTime mRegisterDate;
    private DateTime mDeadlineDate;
    private String mResponsible;
    private String mText;
    private List<Integer> mImages;
    private int mIcon;
    private int mLikes;
    private String mAddress;
    private String mDays;

    public enum Status {
        IN_WORK(R.string.in_work_request_status),
        WAIT(R.string.wait_request_status),
        DONE(R.string.done_request_status);

        private int mTitleId;

        Status(int titleId) {
            mTitleId = titleId;
        }

        public int getTitleId() {
            return mTitleId;
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status mStatus) {
        this.mStatus = mStatus;
    }

    public DateTime getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(DateTime mCreateDate) {
        this.mCreateDate = mCreateDate;
    }

    public DateTime getRegisterDate() {
        return mRegisterDate;
    }

    public void setRegisterDate(DateTime mRegisterDate) {
        this.mRegisterDate = mRegisterDate;
    }

    public DateTime getDeadlineDate() {
        return mDeadlineDate;
    }

    public void setDeadlineDate(DateTime mDeadlineDate) {
        this.mDeadlineDate = mDeadlineDate;
    }

    public String getResponsible() {
        return mResponsible;
    }

    public void setResponsible(String mResponsible) {
        this.mResponsible = mResponsible;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public List<Integer> getImages() {
        return mImages;
    }

    public void setImages(List<Integer> mImages) {
        this.mImages = mImages;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public int getLikes() {
        return mLikes;
    }

    public void setLikes(int mLikes) {
        this.mLikes = mLikes;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getDays() {
        return mDays;
    }

    public void setDays(String mDays) {
        this.mDays = mDays;
    }
}
