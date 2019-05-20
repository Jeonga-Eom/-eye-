package com.example.eye;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class TimeFragment extends Fragment {
    //getSharedPreferences 오류 해결을 위한 Context
    private Context mContext;
    Handler handler = new Handler();
    //사용한 시간 표시 TextView
    TextView hourText;
    TextView minText;
    //사용 시작한 시간 저장
    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;
    int Hour;
    int Min;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancestate) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_time, container, false);

        //현재 시간을 얻기 위한 Calendar(timepickerdialog에 나타낼 때 이용), 착용 시간을 표시할 TextView와 오류 해결을 위한 Content
        final Calendar cal = Calendar.getInstance();
        hourText = rootView.findViewById(R.id.TextView_time_hours);
        minText = rootView.findViewById(R.id.TextView_time_minutes);
        mContext = getActivity();

        //이전 설정 시간이 있을 경우 timer 작동
        final TimeThread thread = new TimeThread();
        thread.start();

        //Start 버튼
        Button StartBtn = rootView.findViewById(R.id.time_start_button);
        StartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    //TimePickerDialog에서 설정한 시간 hour:min
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        //설정 시간 저장
                        Hour = hour; Min = min;
                        sharedPrefernces(1);
                        //설정 시간에 대한 Thread 시작
                        final TimeThread thread = new TimeThread();
                        thread.start();
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                dialog.show();
                //출처: https://kwon8999.tistory.com/entry/안드로이드-DatePicker-TimePicker-사용법 [Kwon's developer]
            }
        });
        //Reset 버튼
        Button ResetBtn = rootView.findViewById(R.id.time_reset_button);
        ResetBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //저장된 설정 시간 지우기, thread 종료
                sharedPrefernces(-1);
                thread.interrupt();
            }
        });
        return rootView;
    }

    public class TimeThread extends Thread {
        public void run() {
            while (true) {
               handler.post(new Runnable() {
                    public void run() {
                        applySharedPreference();
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //thread가 interrupt될 경우, 시간을 0으로 설정
                    hourText.setText(Integer.toString(0));
                    minText.setText(Integer.toString(0));
                }
            }
        }
    }

    public void sharedPrefernces(int type) {
        //쉐어프리퍼런스
        sh_Pref = mContext.getSharedPreferences("Time", MODE_PRIVATE);
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
        sh_Pref = mContext.getSharedPreferences("Time", MODE_PRIVATE);
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
    }
}