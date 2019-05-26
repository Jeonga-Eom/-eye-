package com.example.eye;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.eye.Adapter.ListViewAdapter;


public class EomActivity extends AppCompatActivity implements ListViewAdapter.ListBtnClickListener {
    //https://yoo-hyeok.tistory.com/53
    private Context mContext;
    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;

    private ListView listview ;
    private ListViewAdapter adapter;

    //https://recipes4dev.tistory.com/48추가 지우기 등
    String name;
    Integer LeftDate, RightDate, LeftPeriod, RightPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eom);
        mContext = getApplicationContext();

        setListView();

        //렌즈 add버튼
        Button btnAdd = findViewById(R.id.add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EomActivity.this, AddLensActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        //listview item
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
            }
        }) ;
    }
    //listview의 삭제 버튼
    @Override
    public void onListBtnClick(int position) {
        sharedPreferences(-1, position);
    }

    //listView 출력 function
    protected void setListView() {
        //list view
        adapter = new ListViewAdapter(this, R.layout.custom_listview, this);
        sh_Pref = mContext.getSharedPreferences("Period", MODE_PRIVATE);
        //adapter를 통한 값 전달
        for(int i=0; i <= sh_Pref.getInt("number", 0); i++){
            String Name;
            Integer LeftDate, RightDate, LeftPeriod, RightPeriod;
            String s = "lens_name_" + i;
            if(sh_Pref.contains(s)) {
                Name = sh_Pref.getString(s, null);
                s = "left_date_" + i;
                LeftDate = sh_Pref.getInt(s, 0);
                s = "right_date_" + i;
                RightDate = sh_Pref.getInt(s, 0);
                s = "left_period_" + i;
                LeftPeriod = sh_Pref.getInt(s, 0);
                s = "right_period_" + i;
                RightPeriod = sh_Pref.getInt(s, 0);
                adapter.addVO(Name, LeftDate, RightDate, LeftPeriod, RightPeriod);
            }
        }
        listview = (ListView) findViewById(R.id.ListView_period);
        listview.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listview);
        adapter.notifyDataSetChanged();
    }

    //추가하는 렌즈에 대한 데이터를 Result로 받고 저장
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();

            name = bundle.getString("lens_name");
            LeftDate = bundle.getInt("left_year") * 10000 + bundle.getInt("left_month") * 100 + bundle.getInt("left_day");
            RightDate = bundle.getInt("right_year") * 10000 + bundle.getInt("right_month") * 100 + bundle.getInt("right_day");
            LeftPeriod = bundle.getInt("period1");
            RightPeriod = bundle.getInt("period2");
            sharedPreferences(1, 0);
        }
    }

    //sharedPreferences에 저장 및 삭제
    public void sharedPreferences(int type, int n) {
        //쉐어프리퍼런스
        sh_Pref = mContext.getSharedPreferences("Period", MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        //렌즈 기간 정보 저장
        if(type == 1) {
            int i = sh_Pref.getInt("number", -1) + 1;

            toEdit.putInt("number", i);
            String str = "lens_name_" + i; toEdit.putString(str, name);
            str = "left_date_" + i; toEdit.putInt(str, LeftDate);
            str = "right_date_" + i; toEdit.putInt(str, RightDate);
            str = "left_period_" + i; toEdit.putInt(str, LeftPeriod);
            str = "right_period_" + i; toEdit.putInt(str, RightPeriod);
        }
        else {
            if(sh_Pref.getInt("number", -1) == n)
                toEdit.putInt("number", n - 1);
            String str = "lens_name_" + n; toEdit.remove(str);
            str = "left_date_" + n; toEdit.remove(str);
            str = "right_date_" + n; toEdit.remove(str);
            str = "left_period_" + n; toEdit.remove(str);
            str = "right_period_" + n; toEdit.remove(str);
        }
        toEdit.commit();
        cleanup();
        setListView();
    }

    private void cleanup() {
        //쉐어프리퍼런스
        sh_Pref = mContext.getSharedPreferences("Period", MODE_PRIVATE);
        toEdit = sh_Pref.edit();

        int i = 0, j = 0;
        while(i <= sh_Pref.getInt("number", -1)) {
            String a = "lens_name_" + i;
            if (sh_Pref.contains(a)) {
                String b = "lens_name_" + j;
                String name = sh_Pref.getString(a, null);
                toEdit.putString(b, name);
                j++;
            }
            i++;
        }
        toEdit.putInt("number", j - 1);
        toEdit.commit();
    }


    //ScrollView에서 ListView의 height 조절
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}