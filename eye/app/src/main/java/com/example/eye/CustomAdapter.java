package com.example.eye;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>  {

    private ArrayList<Dictionary> mList;
    private Context mContext;
    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;




    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener { // 1. 리스너 추가

        protected TextView mLeft;
        protected TextView mRight;
        protected TextView mLens;
        protected TextView mLocate;
        protected ImageView mImage;


        public CustomViewHolder(View view) {
            super(view);

            this.mLeft = (TextView) view.findViewById(R.id.textview_recyclerview_left);
            this.mRight = (TextView) view.findViewById(R.id.textview_recyclerview_right);
            this.mLens = (TextView) view.findViewById(R.id.textview_recyclerview_lens);
            this.mImage = (ImageView) view.findViewById(R.id.image);

            view.setOnCreateContextMenuListener(this); //2. 리스너 등록
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 메뉴 추가U

            MenuItem Delete = menu.add(Menu.NONE, 1002, 1, "삭제");
            Delete.setOnMenuItemClickListener(onEditMenu);

        }

        // 4. 캔텍스트 메뉴 클릭시 동작을 설정
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1002:

                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());

                        SharedPreferences pref = mContext.getSharedPreferences("Lens Data", MODE_PRIVATE);
                        toEdit = pref.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(mList);
                        toEdit.putString("task list",json);
                        toEdit.putString("task list2",json);
                        toEdit.apply();

                        break;
                }
                return true;
            }
        };


    }


    public CustomAdapter(Context context, ArrayList<Dictionary> list) {
        mList = list;
        mContext = context;

    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.mLens.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.mLeft.setGravity(Gravity.CENTER);
        viewholder.mRight.setGravity(Gravity.CENTER);
        viewholder.mLens.setGravity(Gravity.CENTER);

        viewholder.mLeft.setText(mList.get(position).getLeftEye());
        viewholder.mRight.setText(mList.get(position).getRightEye());
        viewholder.mLens.setText(mList.get(position).getLens());

        viewholder.mLens.setTextSize(20);

        final int Position = position;

        viewholder.mImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(mContext, recyclerview_click.class);
                intent.putExtra("left", mList.get(Position).getLeftEye());
                intent.putExtra("right", mList.get(Position).getRightEye());
                intent.putExtra("lens", mList.get(Position).getLens());
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }



}