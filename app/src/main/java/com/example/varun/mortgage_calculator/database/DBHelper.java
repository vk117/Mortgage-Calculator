package com.example.varun.mortgage_calculator.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by varun on 3/25/2018.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "calculations.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("create table " + DBschema.TABLE_NAME + "(" +
                " _id integer primary key autoincrement, " +
                        DBschema.PROP_TYPE + "," +
                        DBschema.ST_ADDRESS + "," +
                        DBschema.CITY + "," +
                        DBschema.STATE + "," +
                        DBschema.ZIPCODE + "," +
                        DBschema.PROP_PRICE + "," +
                        DBschema.DOWN_PAY + "," +
                        DBschema.RATE + "," +
                        DBschema.TERMS + "," +
                        DBschema.LAT + "," +
                        DBschema.LONG + "," +
                        DBschema.RES +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DBschema.TABLE_NAME );
        onCreate(db);
    }
}
