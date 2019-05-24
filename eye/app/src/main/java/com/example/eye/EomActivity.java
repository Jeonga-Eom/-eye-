package com.example.eye;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EomActivity extends AppCompatActivity {
    //https://yoo-hyeok.tistory.com/53
    //private Context mContext;
    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;

    //https://recipes4dev.tistory.com/48추가 지우기 등
    ArrayList<String> Name = new ArrayList<String>();
    ArrayList<Integer> LeftDate = new ArrayList<Integer>();
    ArrayList<Integer> RightDate = new ArrayList<Integer>();
    ArrayList<Integer> LeftPeriod = new ArrayList<Integer>();
    ArrayList<Integer> RightPeriod = new ArrayList<Integer>();

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eom);
        //mContext = getActivity();

        listView =(ListView)findViewById(R.id.ListView_period);

        //데이터 초기화


        //simpleAdapter 생성
        //SimpleAdapter simpleAdapter = new SimpleAdapter(this,Data,android.R.layout.simple_list_item_2,new String[]{"school","name"},new int[]{android.R.id.text1,android.R.id.text2});
        //listView.setAdapter(simpleAdapter);

    }
    /*
    public void sharedPrefernces(int type) {
        //쉐어프리퍼런스
        sh_Pref = getSharedPreferences("Lens", MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        //이용 시작 시간 값 입력
        if(type == 1) {
            toEdit.putInt("Start Time Hour", Hour);
            toEdit.putInt("Start Time Minute", Min);
        }
        //이용 시작 시간 값 제거
        else {
            toEdit.remove("Start Time Hour");
            toEdit.remove("Start Time Minute");
        }
        toEdit.commit();
    }

    public void applySharedPreference() {
        sh_Pref = getSharedPreferences("Time", MODE_PRIVATE);
        if (sh_Pref != null && sh_Pref.contains("Start Time Hour") && sh_Pref.contains("Start Time Minute")) {
            //현재 시간 정보(이용 시간을 나타낼때 사용)
            final Calendar cal = Calendar.getInstance();
            int currentHour = cal.get(Calendar.HOUR_OF_DAY);
            int currentMin = cal.get(Calendar.MINUTE);
            //현재 시간과 설정한 시간의 차로 이용 시간 set
            if (currentMin < sh_Pref.getInt("Start Time Minute", 0)) {
                hourText.setText(Integer.toString(currentHour - sh_Pref.getInt("Start Time Hour", 0) - 1));
                minText.setText(Integer.toString(60 + currentMin - sh_Pref.getInt("Start Time Minute", 0)));
            }
            else {
                hourText.setText(Integer.toString(currentHour - sh_Pref.getInt("Start Time Hour", 0)));
                minText.setText(Integer.toString(currentMin - sh_Pref.getInt("Start Time Minute", 0)));
            }
        }
    }*/
}