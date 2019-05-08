package com.example.eye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//https://lx5475.github.io/2017/07/15/android-splash/
//첫 로딩화면 액티비티, 레이아웃은 없습니다.
//관련된 파일은 manifest, style.xml, splash.xml, logo.png입니다.
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // MainActivity.class 자리에 다음에 넘어갈 액티비티를 넣어주기
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("state", "launch");
        startActivity(intent);
        finish();
    }
}