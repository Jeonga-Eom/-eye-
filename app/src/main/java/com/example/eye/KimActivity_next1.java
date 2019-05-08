package com.example.eye;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class KimActivity_next1 extends Activity {


    private ArrayList<HashMap<String,String>> Data = new ArrayList<HashMap<String,String>>();
    private HashMap<String,String> InputData1 = new HashMap<>();
    private HashMap<String,String> InputData2 = new HashMap<>();
    private HashMap<String,String> InputData3 = new HashMap<>();
    private HashMap<String,String> InputData4 = new HashMap<>();
    private ListView listView;

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kim_next1);
        listView =findViewById(R.id.LensList);

        InputData1.put("LenKind","일반");
        InputData1.put("name","일반");
        Data.add(InputData1);

        InputData2.put("LenKind","난시용");
        InputData2.put("name","난시용");
        Data.add(InputData2);

        InputData3.put("LenKind","원시용");
        InputData3.put("name","원시용");
        Data.add(InputData3);

        InputData4.put("LenKind","근시용");
        InputData4.put("name","근시용");
        Data.add(InputData4);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,Data,android.R.layout.simple_list_item_2,new String[]{"LensKind","name"},new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(simpleAdapter);

        Button next2= findViewById(R.id.next2);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),KimActivity_next2.class);
                startActivity(intent);
            }
        });
        Button before2= findViewById(R.id.before2);
        before2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
