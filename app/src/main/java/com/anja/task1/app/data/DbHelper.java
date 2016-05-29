package com.anja.task1.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anna on 28.05.2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "OrderDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE OrderTable ("
                + "id integer primary key,"
                + "title text,"
                + "status text,"
                + "create_date INTEGER,"
                + "register_date INTEGER,"
                + "deadline_date INTEGER,"
                + "responsible text,"
                + "text_body text,"
                + "images text,"
                + "icon INTEGER,"
                + "likes INTEGER,"
                + "address text,"
                + "days text" + ");");
        db.execSQL("create index status_indx ON OrderTable (status);");
        db.execSQL("create table FBProfile ("
                + "user_id text primary key,"
                + "access_token text,"
                + "user_name text,"
                + "photo_url text"
                +");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
