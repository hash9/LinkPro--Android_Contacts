package com.example.dell.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class LinkDAO extends SQLiteOpenHelper {
    public LinkDAO(Context context) {
        super(context, "Links", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE links (" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL," +
                "address TEXT," +
                "website TEXT," +
                "email TEXT," +
                "phoneNumber TEXT," +
                "grading REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Link link) {
        ContentValues data = link.toContentValues();
        SQLiteDatabase database = getWritableDatabase();
        database.insert("links", null, data);

    }

    public List<Link> listALL() {
        List<Link> links = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor =  database.rawQuery("SELECT * FROM links", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            String website = cursor.getString(cursor.getColumnIndex("website"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            double grading = cursor.getDouble(cursor.getColumnIndex("grading"));
            Link link = new Link(id, name, address, phoneNumber, website, email, grading);
            links.add(link);
        }
        return links;
    }

    public void remove(Link link) {
        SQLiteDatabase database = getWritableDatabase();
        String[] params = {link.getId() + ""};
        database.delete("students", "id = ?", params);
    }

    public void update(Link link, int originalId) {
        ContentValues data= link.toContentValues();
        SQLiteDatabase database = getWritableDatabase();
        String[] params = { originalId + ""};
        database.update("links", data, "id = ?", params);
    }
}
