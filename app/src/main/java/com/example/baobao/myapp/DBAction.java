package com.example.baobao.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wangyun on 15/10/13.
 */
public class DBAction extends SQLiteOpenHelper {

    private final static String CREATE_BOOK = "CREATE TABLE BOOK(ID INTEGER PRIMARY KEY,age TEXT,name TEXT)";
    private Context mContext;

    public DBAction(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
