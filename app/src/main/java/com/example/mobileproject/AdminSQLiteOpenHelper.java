package com.example.mobileproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table users(id integer primary key autoincrement, name text, surname text, email text, pass text, register text, grade text)");
        database.execSQL("create table store(id integer primary key autoincrement, code text, description text)");
        database.execSQL("create table subjects(id integer primary key autoincrement, nameSub text, nameTea text, register text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
