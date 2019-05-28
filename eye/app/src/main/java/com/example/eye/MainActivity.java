package com.example.eye;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageButton time_period, faq, mylens, source;
    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time_period=findViewById(R.id.eom);
        faq=findViewById(R.id.yang);
        mylens=findViewById(R.id.kim);
        source=findViewById(R.id.source);

        time_period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), EomActivity.class);
                startActivity(intent1);
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), YangActivity.class);
                startActivity(intent2);
            }
        });
        mylens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), KimActivity.class);
                startActivity(intent3);
            }
        });
        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), SourceActivity.class);
                startActivity(intent4);
            }
        });

        DBHelper dbHelper = new DBHelper(this, "LENS.db", null, 1);
        try {
            db = dbHelper.getReadableDatabase();
        } catch (Exception e) {
            db = dbHelper.getReadableDatabase();
        }
    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("아이아퍼")
                .setMessage("앱을 종료하시겠습니까?")
                .setNegativeButton("취소", null)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        {
                            finish();
                        }
                    }
                });
        builder.show();
    }


}
