package com.example.eye;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PeriodFragment extends Fragment {
    //getSharedPreferences 오류 해결을 위한 Context
    private Context mContext;
    //사용 렌즈
    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancestate) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_time, container, false);
        return rootView;
    }
}
