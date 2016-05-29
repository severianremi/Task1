package com.anja.task1.app.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anna on 29.05.2016.
 */
public class OrderDaoImpl implements OrderDao {

    private static final String IMAGE_NAME_SPLITTER = ",";
    private DbHelper mDbHelper;


    public void setDbHelper(DbHelper dbHelper) {
        this.mDbHelper = dbHelper;
    }

    @Override
    public void saveOrders(List<Order> orders) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        for (Order order : orders) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", order.getId());
            contentValues.put("title", order.getTitle());
            contentValues.put("status", order.getStatus().name());
            contentValues.put("create_date", order.getCreateDate().getMillis());
            contentValues.put("register_date", order.getRegisterDate().getMillis());
            contentValues.put("deadline_date", order.getDeadlineDate().getMillis());
            contentValues.put("responsible", order.getResponsible());
            contentValues.put("text_body", order.getText());
            contentValues.put("images", convertImageNamesToString(order.getImages()));
            contentValues.put("icon", order.getIcon());
            contentValues.put("likes", order.getLikes());
            contentValues.put("address", order.getAddress());
            contentValues.put("days", order.getDays());
            db.insertWithOnConflict("OrderTable", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        }
        mDbHelper.close();
    }


    private String convertImageNamesToString(List<String> imageNames) {
        StringBuilder names = new StringBuilder();
        for (String image : imageNames) {
            names.append(image).append(IMAGE_NAME_SPLITTER);
        }
        return names.toString();
    }


    @Override
    public List<Order> loadOrders(Order.Status status) {
        List<Order> orders = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM OrderTable WHERE status = ?", new String[]{status.name()});
        if (c.moveToFirst()) {
            int idIndex = c.getColumnIndex("id");
            int titleIndex = c.getColumnIndex("title");
            int statusIndex = c.getColumnIndex("status");
            int createDataIndex = c.getColumnIndex("create_date");
            int registerIndex = c.getColumnIndex("register_date");
            int deadlineIndex = c.getColumnIndex("deadline_date");
            int responsibleIndex = c.getColumnIndex("responsible");
            int textIndex = c.getColumnIndex("text_body");
            int iconIndex = c.getColumnIndex("icon");
            int likesIndex = c.getColumnIndex("likes");
            int addressIndex = c.getColumnIndex("address");
            int daysIndex = c.getColumnIndex("days");
            int imageIndex = c.getColumnIndex("images");
            do {
                Order order = new Order();
                order.setId(c.getInt(idIndex));
                order.setTitle(c.getString(titleIndex));
                order.setStatus(Order.Status.valueOf(c.getString(statusIndex)));
                order.setCreateDate(new DateTime(c.getLong(createDataIndex)));
                order.setRegisterDate(new DateTime(c.getLong(registerIndex)));
                order.setDeadlineDate(new DateTime(c.getLong(deadlineIndex)));
                order.setResponsible(c.getString(responsibleIndex));
                order.setText(c.getString(textIndex));
                order.setIcon(c.getInt(iconIndex));
                order.setLikes(c.getInt(likesIndex));
                order.setAddress(c.getString(addressIndex));
                order.setDays(c.getString(daysIndex));
                order.setImages(parseImageNames(c.getString(imageIndex)));
                orders.add(order);
            } while (c.moveToNext());
        }
        c.close();
        mDbHelper.close();
        return orders;
    }

    private List<String> parseImageNames(String imageNames) {
        String[] fileNames = imageNames.split(IMAGE_NAME_SPLITTER);
        return Arrays.asList(fileNames);
    }

    @Override
    public void deleteOrders() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete("OrderTable", null, null);
        mDbHelper.close();
    }
}
