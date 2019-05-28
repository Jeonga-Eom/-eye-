package com.example.eye;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class KimActivity extends AppCompatActivity {

    private static String TAG = "recyclerview_example";

    private ArrayList<Dictionary> mArrayList;
    private ArrayList<Dictionary> mArrayList2;
    private CustomAdapter mAdapter;
    private CustomAdapter mAdapter2;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    ListView listview;
    EditText editTextSearch;
    ArrayAdapter<String> arrayAdapter;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kim);

        //horizontal로 listview 만들기
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.recyclerview_main_list2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView2.setLayoutManager(linearLayoutManager2);

        applySharedPreference();


        mAdapter = new CustomAdapter(this, mArrayList);
        mAdapter2 = new CustomAdapter(this, mArrayList2);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView2.setAdapter(mAdapter2);


        ImageButton buttonInsert = findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(KimActivity.this);
                View view = LayoutInflater.from(KimActivity.this)
                        .inflate(R.layout.edit_box1, null, false);

                //
                builder.setView(view);
                final Button ButtonCancle = (Button) view.findViewById(R.id.button_dialog_cancle);
                final Button ButtonNext = (Button) view.findViewById(R.id.button_dialog_next);
                final EditText editTextLeftEye = (EditText) view.findViewById(R.id.editText_lefteye);
                final EditText editTextRightEye = (EditText) view.findViewById(R.id.editText_righteye);

                final AlertDialog dialog = builder.create();
                dialog.show();

                ButtonNext.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(KimActivity.this);
                        View view = LayoutInflater.from(KimActivity.this)
                                .inflate(R.layout.edit_box2, null, false);

                        //
                        builder2.setView(view);
                        final Button ButtonBefore = (Button) view.findViewById(R.id.before);
                        final Button ButtonNext2 = (Button) view.findViewById(R.id.next2);
                        editTextSearch = (EditText) view.findViewById(R.id.LensSearch);
                        listview = view.findViewById(R.id.listview);

                        arrayAdapter = new ArrayAdapter<String>(KimActivity.this, android.R.layout.simple_list_item_1);
                        arrayAdapter.add("원데이 아큐브 오아시스(근시 원시용)");arrayAdapter.add("원데이 아큐브 디파인 비비드 스타일");arrayAdapter.add("원데이 아큐브 오아시스(난시용)");
                        arrayAdapter.add("원데이 아큐브 모이스트 멀티포컬");arrayAdapter.add("원데이 아큐브 트루아이(근시 원시용)");arrayAdapter.add("원데이 아큐브 모이스트(근시 원시용)");
                        arrayAdapter.add("원데이 아큐브 모이스트(난시용)");arrayAdapter.add("원데이 아큐브(근시 원시용)");arrayAdapter.add("2주 아큐브 오아시스(근시 원시용)");
                        arrayAdapter.add("2주 아큐브 어드밴스(근시 원시용)");arrayAdapter.add("2주 아큐브 어드밴스(난시용)");arrayAdapter.add("아큐브2 디파인 비비드 스타일");
                        arrayAdapter.add("2주 아큐브 오아시스(난시용)");arrayAdapter.add("아큐브 비타");arrayAdapter.add("아큐브 비타(난시용)");
                        arrayAdapter.add("소프렌TM데일리(난시용)");arrayAdapter.add("소프렌TM데일리(근시용)");arrayAdapter.add("바이오트루 원데이(난시용)");
                        arrayAdapter.add("바이오트루 원데이");arrayAdapter.add("옵티마 FW(근시용)");arrayAdapter.add("바슈롬 울트라");
                        arrayAdapter.add("퓨어비전2 HD(근시용)");arrayAdapter.add("퓨어비전2 멀티포컬");arrayAdapter.add("퓨어비전2 HD(난시용)");
                        arrayAdapter.add("바슈롬 레이셀(한달용) 트윙클 브라운");arrayAdapter.add("바슈롬 내츄렐 퓨어 블랙");arrayAdapter.add("바슈롬 내츄렐 시크 브라운");
                        arrayAdapter.add("바슈롬 레이셀(한달용) 크리스탈 브라운");arrayAdapter.add("클라렌 아이리스 M(근시용) 릴리 블루");arrayAdapter.add("클라렌 아이리스 원데이 수지그레이");
                        arrayAdapter.add("클라렌 아이리스2 시어 브라운");arrayAdapter.add("클라렌 아이리스 원데이 알리샤브라운(난시용)");arrayAdapter.add("클라렌 아이리스 원데이 소울브라운");
                        arrayAdapter.add("클라렌 아이리스 원데이 수지브라운");arrayAdapter.add("클라렌 아이리스 원데이 알리샤브라운(근시용)");arrayAdapter.add("클라렌 아이리스 원데이 재즈블랙");
                        arrayAdapter.add("클라렌 에어수(근시용)");arrayAdapter.add("클라렌 55T(난시용)");arrayAdapter.add("클라렌 아이리스 원데이 블루문");
                        arrayAdapter.add("클라렌 55S");arrayAdapter.add("클라렌 원데이");arrayAdapter.add("에어옵틱스 나이트&데이 PLANE");
                        arrayAdapter.add("에어옵틱스 나이트&데이 NAP");arrayAdapter.add("후레쉬룩 원데이 컬러");arrayAdapter.add("후레쉬룩 원데이 컬러");
                        arrayAdapter.add("데일리스 일루미네이트");arrayAdapter.add("포커스 데일리스");arrayAdapter.add("데일리스 아쿠아 컴포트 플러스");
                        arrayAdapter.add("데일리스 토탈 원");arrayAdapter.add("샤이닝 퓨어 브라운");arrayAdapter.add("제니스 3칼라 스카이그레이");
                        arrayAdapter.add("샤이닝퓨어 초코");arrayAdapter.add("틴틴청순 내츄럴초코");arrayAdapter.add("츄잉 3콘 그레이");arrayAdapter.add("틴틴청순 라떼브라운");
                        arrayAdapter.add("샤이닝 그레이");arrayAdapter.add("미스티 내츄럴초코");arrayAdapter.add("크리스탈 3콘 그레이");arrayAdapter.add("안나수이 돌리걸 틴트 브라운");
                        arrayAdapter.add("시크리스 3콘 내츄럴 그레이");arrayAdapter.add("시크리스 3콘 내츄럴 브라운");arrayAdapter.add("심포니 3콘 그레이");arrayAdapter.add("스페니쉬 원데이 그레이");
                        arrayAdapter.add("스페니쉬 원데이 브라운");arrayAdapter.add("썸데이 그레이");arrayAdapter.add("썸데이 브라운");arrayAdapter.add("시크리스 3콘 코랄 브라운");
                        arrayAdapter.add("시크리스 3콘 코랄 그레이");arrayAdapter.add("스페니쉬 리얼브라운");arrayAdapter.add("스페니쉬 리얼그레이");arrayAdapter.add("스페니쉬 써클그레이");
                        arrayAdapter.add("스페니쉬 써클브라운");arrayAdapter.add("코튼 그레이");arrayAdapter.add("코튼 브라운");arrayAdapter.add("아이블랑 립스틱 브라운");arrayAdapter.add("마카롱 브라운");
                        arrayAdapter.add("루이샤인 로미오 브라운");arrayAdapter.add("워터앰플 3칼라 브라운");arrayAdapter.add("아트릭76 바이블 브라운");arrayAdapter.add("루이샤인 줄리엣 브라운");arrayAdapter.add("아이블랑 네일 그레이");
                        arrayAdapter.add("루이샤인 베이글 크림 브라운");arrayAdapter.add("워터앰플 3칼라 그레이");arrayAdapter.add("아이블랑 립스틱 그레이");arrayAdapter.add("오로라 투톤 브라운");arrayAdapter.add("코튼 초쿄");
                        arrayAdapter.add("시크릿베리 그레이");arrayAdapter.add("모찌 2칼라 쵸코");arrayAdapter.add("브러쉬 홀로그램 브라운");arrayAdapter.add("섀도우 홀로그램 브라운");arrayAdapter.add("루이샤인 베이글 신드롬 그레이");
                        arrayAdapter.add("섀도우 홀로그램 그레이");arrayAdapter.add("영롱 포텐 그레이");arrayAdapter.add("비너스3칼라 그레이");arrayAdapter.add("아이블랑 틴트 그레이");
                        arrayAdapter.add("워터앰플 그레이");arrayAdapter.add("워터앰플 브라운");arrayAdapter.add("워터앰플 쵸코");

                        listview.setAdapter(arrayAdapter);
                        listview.setTextFilterEnabled(true);

                        //editTextSearch filtering event
                        editTextSearch.addTextChangedListener(new TextWatcher(){
                            public void beforeTextChanged(CharSequence s, int start, int count, int after){

                            }
                            public void onTextChanged(CharSequence s, int start, int before, int count){
                                listview.setFilterText(editTextSearch.getText().toString());
                            }

                            public void afterTextChanged(Editable s) {
                                if(editTextSearch.getText().length() == 0) {
                                    listview.clearTextFilter();
                                }
                            }
                        });

                        //listview click event
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            public void onItemClick(AdapterView parent, View v, int position, long id){
                                editTextSearch.setText(((TextView)v).getText().toString());
                            }
                        });

                        final AlertDialog dialog2 = builder2.create();
                        dialog2.show();

                        ButtonNext2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                                AlertDialog.Builder builder3 = new AlertDialog.Builder(KimActivity.this);
                                View view = LayoutInflater.from(KimActivity.this)
                                        .inflate(R.layout.edit_box3, null, false);

                                //
                                builder3.setView(view);
                                final RadioGroup RG = view.findViewById(R.id.radiogroup);
                                final RadioButton radHistory = view.findViewById(R.id.radioButton_history);
                                final RadioButton radWishList = view.findViewById(R.id.radioButton_wishlist);
                                final Button ButtonBefore2 = (Button) view.findViewById(R.id.before2);
                                final Button ButtonFinish = (Button) view.findViewById(R.id.finish);

                                final AlertDialog dialog3 = builder3.create();
                                dialog3.show();
                                ButtonFinish.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        String LeftEye = editTextLeftEye.getText().toString();
                                        String RightEye = editTextRightEye.getText().toString();
                                        String Lens = editTextSearch.getText().toString();
                                        String locate = "history";

                                        int radioid = RG.getCheckedRadioButtonId();
                                        if (radHistory.getId() == radioid)
                                            locate = "history";
                                        if (radWishList.getId() == radioid)
                                            locate = "wishlist";



                                        if(locate=="history") {
                                            Dictionary dict = new Dictionary(LeftEye, RightEye, Lens);

                                            mArrayList.add(0,dict);
                                            mAdapter.notifyDataSetChanged();//변경된 데이터를 화면에 반영

                                        }
                                        if(locate=="wishlist"){
                                            Dictionary dict = new Dictionary(LeftEye, RightEye, Lens);

                                            mArrayList2.add(0,dict);
                                            mAdapter2.notifyDataSetChanged();//변경된 데이터를 화면에 반영
                                        }

                                        dialog.dismiss();
                                        dialog2.dismiss();
                                        dialog3.dismiss();
                                        sharedPreference();
                                    }
                                });

                                ButtonBefore2.setOnClickListener(new View.OnClickListener(){
                                    public void onClick(View v){
                                        dialog3.dismiss();
                                    }
                                });
                            }
                        });

                        ButtonBefore.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                dialog2.dismiss();
                            }
                        });

                        dialog2.show();
                    }
                });

                ButtonCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.show();

           
            }
        });



    }


    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;
    public void sharedPreference(){
        sh_Pref = getSharedPreferences("Lens Data",MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mArrayList);
        String json2 = gson.toJson(mArrayList2);
        toEdit.putString("task list",json);
        toEdit.putString("task list2",json2);
        toEdit.apply();
    }

    public void applySharedPreference() {
        sh_Pref = getSharedPreferences("Lens Data", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sh_Pref.getString("task list",null);
        String json2 = sh_Pref.getString("task list2",null);
        Type type = new TypeToken<ArrayList<Dictionary>>() {}.getType();
        mArrayList = gson.fromJson(json, type);
        mArrayList2 = gson.fromJson(json2, type);

        if(mArrayList == null){
            mArrayList = new ArrayList<>();
        }
        if(mArrayList2 == null){
            mArrayList2 = new ArrayList<>();
        }
    }

}