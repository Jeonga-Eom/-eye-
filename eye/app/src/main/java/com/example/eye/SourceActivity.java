package com.example.eye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        TextView mainlogo = (TextView) findViewById(R.id.mainlogo);
        String text = "Icon made by [iconixar]\nfrom www.flaticon.com ";
        mainlogo.setText(text);
        Linkify.TransformFilter mTransform = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };
        Pattern pattern1 = Pattern.compile("iconixar");
        Pattern pattern2 = Pattern.compile("www.flaticon.com");
        Linkify.addLinks(mainlogo, pattern1, "https://www.flaticon.com/authors/iconixar",null,mTransform);
        Linkify.addLinks(mainlogo, pattern2, "https://www.flaticon.com",null,mTransform);
    }
}
