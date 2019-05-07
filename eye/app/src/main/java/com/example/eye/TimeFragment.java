package com.example.eye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hanjin.R;

public class TimeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancestate) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_time, container, false);

        return rootView;
    }
}
