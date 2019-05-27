package com.example.eye;

/* reference: http://www.androidtutorialshub.com/android-expandable-list-view-tutorial/ */
/* strings.xml + YangActivity.java + activity_yang.xml + list_row_group.xml + list_row_child.xml + ExpandableListViewAdapter.java */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YangActivity extends AppCompatActivity{

    private ExpandableListView expandableListView1;

    private ExpandableListViewAdapter expandableListViewAdapter1;

    private List<String> listDataGroup1;
    private HashMap<String, List<String>> listDataChild1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yang);

        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();

        // initializing the objects
        initObjects();

        // preparing list data
        initListData();

    }


    /**
     * method to initialize the views : 확장 뷰를 불러온다
     */
    private void initViews() {
        expandableListView1 = findViewById(R.id.expandableListView1);
    }

    /**
     * method to initialize the listeners : 리스너를 불러온다
     */
    private void initListeners() {

        // ExpandableListView on child click listener
        expandableListView1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        // ExpandableListView Group expanded listener :parent 그룹 확장
        expandableListView1.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        // ExpandableListView Group collapsed listener :parent 그룹 축소
        expandableListView1.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
    }

    /**
     * method to initialize the objects :오브젝트와 관련된 함수
     */
    private void initObjects() {

        // initializing the list of groups :그룹 어레이리스트를 만든다
        listDataGroup1 = new ArrayList<>();
        // initializing the list of child :차일드 해시맵을 만든다
        listDataChild1 = new HashMap<>();
        // initializing the adapter object :어댑터에 적용
        expandableListViewAdapter1 = new ExpandableListViewAdapter(this, listDataGroup1, listDataChild1);

        // setting list adapter :어뎁터에 set
        expandableListView1.setAdapter(expandableListViewAdapter1);
 }

    /*
     * Preparing the list data
     *
     * Dummy Items : child 리스트를 parent 그룹에 넣는다
     */
    private void initListData() {


        // Adding group data : parent 개별 그룹 형성
        listDataGroup1.add(getString(R.string.text_kind));
        listDataGroup1.add(getString(R.string.text_period));
        listDataGroup1.add(getString(R.string.text_dia));
        listDataGroup1.add(getString(R.string.text_buy));
        listDataGroup1.add(getString(R.string.text_how));
        listDataGroup1.add(getString(R.string.text_status));
        listDataGroup1.add(getString(R.string.text_go));
        listDataGroup1.add(getString(R.string.text_limit));
        listDataGroup1.add(getString(R.string.text_clean));
        listDataGroup1.add(getString(R.string.text_tear));

        // array of strings : 각 parent에 대한 child 어레이 형성
        String[] array;

        // list of kind
        List<String> kindList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_kind);
        for (String item : array) {
            kindList.add(item);
        }

        // list of period
        List<String> periodList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_period);
        for (String item : array) {
            periodList.add(item);
        }

        // list of dia
        List<String> diaList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_dia);
        for (String item : array) {
            diaList.add(item);
        }

        // list of buy
        List<String> buyList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_buy);
        for (String item : array) {
            buyList.add(item);
        }

        // list of how
        List<String> howList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_wear);
        for (String item : array) {
            howList.add(item);
        }

        // list of status
        List<String> statusList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_status);
        for (String item : array) {
            statusList.add(item);
        }

        // list of go
        List<String> goList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_go);
        for (String item : array) {
            goList.add(item);
        }

        // list of limit
        List<String> limitList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_limit);
        for (String item : array) {
            limitList.add(item);
        }

        // list of clean
        List<String> cleanList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_clean);
        for (String item : array) {
            cleanList.add(item);
        }

        // list of tear
        List<String> tearList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_tear);
        for (String item : array) {
            tearList.add(item);
        }

        // Adding child data : child를 parent에 넣기
        listDataChild1.put(listDataGroup1.get(0), kindList);
        listDataChild1.put(listDataGroup1.get(1), periodList);
        listDataChild1.put(listDataGroup1.get(2), diaList);
        listDataChild1.put(listDataGroup1.get(3), buyList);
        listDataChild1.put(listDataGroup1.get(4), howList);
        listDataChild1.put(listDataGroup1.get(5), statusList);
        listDataChild1.put(listDataGroup1.get(6), goList);
        listDataChild1.put(listDataGroup1.get(7), limitList);
        listDataChild1.put(listDataGroup1.get(8), cleanList);
        listDataChild1.put(listDataGroup1.get(9), tearList);

        // notify the adapter :어댑터에 적용
        expandableListViewAdapter1.notifyDataSetChanged();

    }

}
