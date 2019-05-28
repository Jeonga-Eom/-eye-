package com.example.eye;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    String sql;
    SQLiteDatabase db;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //실행할 때 DB 최초 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE LENS (companyname TEXT, name TEXT, color TEXT, dia TEXT, gdia TEXT, moisture TEXT, bc TEXT, sale TEXT, period TEXT);");
        //result.append("\nt3 테이블 생성 완료.");
        insert("렌즈미","워터앰플 그레이","그레이","14","13.1","55%","8.6","10,000","한달");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS t3");
        onCreate(db);
    }
    public void insert(String companyname, String name, String color, String dia, String gdia, String moisture, String bc, String sale, String period){
        sql = String.format("INSERT INTO LENS VALUES('" + companyname + "','" + name + "','" + color + "','" + dia+ "','" + gdia+ "','" + moisture+ "','" + bc+ "','" + sale+ "','" + period + "');");
        db.execSQL(sql);
    }

}