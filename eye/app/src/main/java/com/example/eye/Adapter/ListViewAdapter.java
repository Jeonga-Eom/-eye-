package com.example.eye.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eye.ListVO.ListVO;
import com.example.eye.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements View.OnClickListener {
    public interface ListBtnClickListener {
        void onListBtnClick(int position);
    }
    int resourceId;
    // 생성자로부터 전달된 ListBtnClickListener  저장.
    private ListBtnClickListener listBtnClickListener ;

    private ArrayList<ListVO> listVO = new ArrayList<>() ;
    public ListViewAdapter() {
    }
    public ListViewAdapter(Context context, int resource, ListBtnClickListener clickListener) {
        super();
        this.resourceId=resource;
        this.listBtnClickListener = clickListener;
    }

    @Override
    public int getCount() {
        return listVO.size() ;
    }

    // ** 이 부분에서 리스트뷰에 데이터를 넣어줌 **
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //postion = ListView의 위치      /   첫번째면 position = 0
        final int pos = position;
        final Context context = parent.getContext();


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.TextView_lens_name) ;
        TextView left = (TextView) convertView.findViewById(R.id.TextView_LeftInfo) ;
        TextView right = (TextView) convertView.findViewById(R.id.TextView_RightInfo) ;
        ImageButton delete = (ImageButton) convertView.findViewById(R.id.delete);


        ListVO listViewItem = listVO.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        name.setText(listViewItem.getName());
        left.setText(listViewItem.Left());
        right.setText(listViewItem.Right());

        //삭제 이벤트
        delete.setTag(position);
        delete.setOnClickListener(this);
        return convertView;
    }

    public void onClick(View v) {
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick((int)v.getTag()) ;
        }
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }


    @Override
    public Object getItem(int position) {
        return listVO.get(position) ;
    }

    // 데이터값 넣어줌
    public void addVO(String name, Integer LeftDate, Integer RightDate, Integer LeftPeriod, Integer RightPeriod) {
        ListVO item = new ListVO();
        item.setName(name);
        item.setLeftDate(LeftDate);
        item.setRightDate(RightDate);
        item.setLeftPeriod(LeftPeriod);
        item.setRightPeriod(RightPeriod);

        listVO.add(item);
    }
}