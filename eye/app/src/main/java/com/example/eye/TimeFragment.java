package com.example.eye;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.eye.service.JobSchedulerStart;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class TimeFragment extends Fragment {
    //getSharedPreferences 오류 해결을 위한 Context
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

        hourText = rootView.findViewById(R.id.TextView_time_hours);
        minText = rootView.findViewById(R.id.TextView_time_minutes);

        //이전 설정 시간이 있을 경우 timer 작동
        final TimeThread thread = new TimeThread();
        thread.start();

        //팝업 알림 제어
        ToggleButton setting = rootView.findViewById(R.id.Button_pop_up_setting);
        sh_Pref = getActivity().getSharedPreferences("Time", MODE_PRIVATE);
        if(sh_Pref != null && sh_Pref.getInt("state", 0) == 1) {
            setting.setChecked(true);
        }

        setting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sh_Pref = getActivity().getSharedPreferences("Time", MODE_PRIVATE);
                toEdit = sh_Pref.edit();
                if(isChecked) {
                    Toast.makeText(getActivity(), "팝업 알림 ON", Toast.LENGTH_SHORT).show();
                    toEdit.putInt("state", 1);
                }
                else {
                    Toast.makeText(getActivity(), "팝업 알림 OFF", Toast.LENGTH_SHORT).show();
                    if(sh_Pref.getInt("alert", 0) == 1)
                        JobSchedulerStart.destroy();
                }
                toEdit.apply();
            }
        });

        //Start 버튼
        ImageButton StartBtn = rootView.findViewById(R.id.time_start_button);
        StartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
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
        ImageButton ResetBtn = rootView.findViewById(R.id.time_reset_button);
        ResetBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //저장된 설정 시간 지우기, thread 종료
                sharedPrefernces(-1);
                thread.interrupt();
                Toast.makeText(getActivity(), "렌즈 착용 시간이 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    public class TimeThread extends Thread {
        public void run() {
            if(sh_Pref != null && sh_Pref.getInt("state", 0) == 1) {
                JobSchedulerStart.start(getActivity());
            }
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
                    if(sh_Pref.getInt("alert", 0) == 1)
                        JobSchedulerStart.destroy();
                    hourText.setText(Integer.toString(0));
                    minText.setText(Integer.toString(0));
                }
            }
        }
    }

    public void sharedPrefernces(int type) {
        //쉐어프리퍼런스
        sh_Pref = getActivity().getSharedPreferences("Time", MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        //이용 시작 시간 값 입력
        if (type == 1) {
            final Calendar cal = Calendar.getInstance();
            final Calendar set = Calendar.getInstance();
            int currentHour = cal.get(Calendar.HOUR_OF_DAY);
            int currentMin = cal.get(Calendar.MINUTE);
            long aTime = (currentHour * 60 + currentMin) * 60 * 1000;
            long bTime = (Hour * 60 + Min) * 60 * 1000;

            if (bTime > aTime) {
                set.set(Calendar.DATE, cal.get(Calendar.DAY_OF_MONTH) - 1);
            }

            set.set(Calendar.HOUR_OF_DAY, Hour);
            set.set(Calendar.MINUTE, Min);
            set.set(Calendar.SECOND, 0);

            toEdit.putLong("Millis", set.getTimeInMillis());
            Toast.makeText(getActivity(), "[렌즈 착용 시작 시간] " + set.get(Calendar.DAY_OF_MONTH) + "일 " + set.get(Calendar.HOUR_OF_DAY) + ":" + set.get(Calendar.MINUTE), Toast.LENGTH_LONG).show();
        }
        //이용 시작 시간 값 제거
        else {
            toEdit.remove("Millis");
        }
        toEdit.apply();

    }

    public void applySharedPreference() {
        if (sh_Pref != null && sh_Pref.contains("Millis")) {
            //현재 시간 정보(이용 시간을 나타낼때 사용)
            final Calendar current = Calendar.getInstance();
            final Calendar set = Calendar.getInstance();
            set.setTimeInMillis(sh_Pref.getLong("Millis", 0));

            long remain = current.getTimeInMillis() - set.getTimeInMillis();
            //Toast.makeText(getActivity(), "현재: " + current.get(Calendar.HOUR_OF_DAY) + ":" + current.get(Calendar.MINUTE) + " 설정: " + set.get(Calendar.HOUR_OF_DAY) + ":" + set.get(Calendar.MINUTE) + " 남: " + remain / (1000 * 60 * 60) + ":" + remain % (1000 * 60 * 60) / (1000 * 60), Toast.LENGTH_SHORT).show();
            hourText.setText("" + (remain / (1000 * 60 * 60)) % 24);
            minText.setText("" + (remain / (1000 * 60)) % 60);
        }
    }
}