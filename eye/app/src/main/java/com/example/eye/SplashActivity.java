package com.example.eye;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

//https://lx5475.github.io/2017/07/15/android-splash/
//첫 로딩화면 액티비티, 레이아웃은 없습니다.
//관련된 파일은 manifest, style.xml, splash.xml, logo.png입니다.
public class SplashActivity extends AppCompatActivity {
    private Handler mWaitHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //The following code will execute after the 5 seconds.

                try {
                    //Go to next page i.e, start the next activity.
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("state", "launch");
                    startActivity(intent);
                    finish();

                    //Let's Finish Splash Activity since we don't want to show this when user press back button.
                    finish();
                } catch (Exception e) {
                }
            }
        }, 1500);  // Give a 5 seconds delay.
    }
        // MainActivity.class 자리에 다음에 넘어갈 액티비티를 넣어주기
        @Override
        public void onDestroy() {
            super.onDestroy();

            //Remove all the callbacks otherwise navigation will execute even after activity is killed or closed.
            mWaitHandler.removeCallbacksAndMessages(null);
        }
}