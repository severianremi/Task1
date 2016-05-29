package com.anja.task1.app.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Anna on 29.05.2016.
 */
public class FBProfileDaoImpl implements FBProfileDao {

    private DbHelper mDbHelper;

    public void setDbHelper(DbHelper dbHelper) {
        this.mDbHelper = dbHelper;
    }

    @Override
    public void saveProfile(FBProfile fbProfile) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete("FBProfile", null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", fbProfile.getUserId());
        contentValues.put("access_token", fbProfile.getAccessToken());
        contentValues.put("user_name", fbProfile.getUserName());
        contentValues.put("photo_url", fbProfile.getPhotoUrl());
        db.insert("FBProfile", null, contentValues);
        mDbHelper.close();
    }

    @Override
    public FBProfile loadProfile() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor c = db.query("FBProfile", null, null, null, null, null, null);
        FBProfile fbProfile = null;
        if (c.moveToFirst()) {
            int userIdIndex = c.getColumnIndex("user_id");
            int accessTokenIndex = c.getColumnIndex("access_token");
            int userNameIndex = c.getColumnIndex("user_name");
            int photoUrlIndex = c.getColumnIndex("photo_url");
            fbProfile = new FBProfile();
            fbProfile.setUserId(c.getString(userIdIndex));
            fbProfile.setAccessToken(c.getString(accessTokenIndex));
            fbProfile.setUserName(c.getString(userNameIndex));
            fbProfile.setPhotoUrl(c.getString(photoUrlIndex));
        }
        c.close();
        mDbHelper.close();
        return fbProfile;
    }
}
