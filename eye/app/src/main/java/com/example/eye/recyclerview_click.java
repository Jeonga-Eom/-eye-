package com.example.eye;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class recyclerview_click extends AppCompatActivity {
    TextView Left;
    TextView Right;
    TextView LensName;
    TextView CompanyName;
    TextView Color;
    TextView DIA;
    TextView GDIA;
    TextView Moisture;
    TextView BC;
    TextView Sale;
    TextView Period;
    DBHelper dbHelper;
    SQLiteDatabase db;
    final static String dbName = "LENS.db";
    final static int dbVersion = 2;
    String sql;
    String com = "tlqkf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_click);


        Intent intent = getIntent();

        Left = findViewById(R.id.left);
        Right = findViewById(R.id.right);
        LensName = findViewById(R.id.lensname);
        CompanyName = findViewById(R.id.companyname);
        Color = findViewById(R.id.color);
        DIA = findViewById(R.id.dia);
        GDIA = findViewById(R.id.gdia);
        Moisture = findViewById(R.id.moisture);
        BC = findViewById(R.id.bc);
        Sale = findViewById(R.id.sale);
        Period = findViewById(R.id.period);

        dbHelper = new DBHelper(this, "LENS.db", null, 1);
        db = dbHelper.getReadableDatabase();

        Left.setText(intent.getStringExtra("left"));
        Right.setText(intent.getStringExtra("right"));
        LensName.setText(intent.getStringExtra("lens"));

        sql = "SELECT * FROM LENS WHERE name='" + intent.getStringExtra("lens") + "'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            CompanyName.setText(cursor.getString(0));
            Color.setText(cursor.getString(2));
            DIA.setText(cursor.getString(3) + "mm");
            GDIA.setText(cursor.getString(4) + "mm");
            Moisture.setText(cursor.getString(5));
            BC.setText(cursor.getString(6) + "mm");
            Sale.setText(cursor.getString(7) + "원");
            Period.setText(cursor.getString(8));

        }


    }
}
