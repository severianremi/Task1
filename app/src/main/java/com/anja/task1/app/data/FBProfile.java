package com.anja.task1.app.data;

/**
 * Created by Anna on 29.05.2016.
 */
public class FBProfile {

    private String mAccessToken;
    private String mUserId;
    private String mUserName;
    private String mPhotoUrl;

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        this.mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.mPhotoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "FBProfile{" +
                "mAccessToken='" + mAccessToken + '\'' +
                ", mUserId='" + mUserId + '\'' +
                ", mUserName='" + mUserName + '\'' +
                ", mPhotoUrl='" + mPhotoUrl + '\'' +
                '}';
    }
}
