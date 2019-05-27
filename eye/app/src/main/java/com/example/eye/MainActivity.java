package com.example.eye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton time_period, source;
    Button yang, kim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time_period=findViewById(R.id.eom);
        yang=findViewById(R.id.yang);
        kim=findViewById(R.id.kim);
        source=findViewById(R.id.source);

        time_period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), EomActivity.class);
                startActivity(intent1);
            }
        });

        yang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), YangActivity.class);
                startActivity(intent2);
            }
        });
        kim.setOnClickListener(new View.OnClickListener() {
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

    }

}
