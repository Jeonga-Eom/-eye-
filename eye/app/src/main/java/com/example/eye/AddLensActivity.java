package com.example.eye;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddLensActivity extends AppCompatActivity {
    EditText name;
    Button add, cancel;
    RadioGroup group1, group2;
    RadioButton oneday1, twoweeks1, onemonth1, sixmonth1, oneday2, twoweeks2, onemonth2, sixmonth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);

        name = findViewById(R.id.EditText_lens_name);
        add = findViewById(R.id.Button_add);
        cancel = findViewById(R.id.Button_cancel);

        //group left
        group1 = findViewById(R.id.RadioGroup_lens_left);
        oneday1 = findViewById(R.id.period_left_oneday);
        twoweeks1 = findViewById(R.id.period_left_twoweeks);
        onemonth1 = findViewById(R.id.period_left_onemonth);
        sixmonth1 = findViewById(R.id.period_left_sixmonth);
        //group right
        group2 = findViewById(R.id.RadioGroup_lens_right);
        oneday2 = findViewById(R.id.period_right_oneday);
        twoweeks2 = findViewById(R.id.period_right_twoweeks);
        onemonth2 = findViewById(R.id.period_right_onemonth);
        sixmonth2 = findViewById(R.id.period_right_sixmonth);

        final DatePicker left = findViewById(R.id.dp_left);
        final DatePicker right = findViewById(R.id.dp_right);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                //이름
                bundle.putInt("type", 1);
                bundle.putString("lens_name", name.getText().toString());
                //왼쪽 정보
                bundle.putInt("left_year", left.getYear());
                bundle.putInt("left_month", left.getMonth());
                bundle.putInt("left_day", left.getDayOfMonth());
                //오른쪽 정보
                bundle.putInt("right_year", right.getYear());
                bundle.putInt("right_month", right.getMonth());
                bundle.putInt("right_day", right.getDayOfMonth());
                //왼쪽 교체 주기 정보
                int radio = group1.getCheckedRadioButtonId();
                if (oneday1.getId()==radio)
                    bundle.putInt("period1", 1);
                if (twoweeks1.getId()==radio)
                    bundle.putInt("period1", 14);
                if (onemonth1.getId()==radio)
                    bundle.putInt("period1", 30);
                if (sixmonth1.getId()==radio)
                    bundle.putInt("period1", 180);
                //오른쪽 교체 주기 정보
                radio = group2.getCheckedRadioButtonId();
                if (oneday2.getId()==radio)
                    bundle.putInt("period2", 1);
                if (twoweeks2.getId()==radio)
                    bundle.putInt("period2", 14);
                if (onemonth2.getId()==radio)
                    bundle.putInt("period2", 30);
                if (sixmonth2.getId()==radio)
                    bundle.putInt("period2", 180);
                intent.putExtras(bundle);
                //start activity
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
