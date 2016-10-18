package com.mw.emoji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity {

    private TextView tv_about_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        tv_about_msg = (TextView) findViewById(R.id.tv_about_msg);
        tv_about_msg.setAutoLinkMask(Linkify.ALL);
        tv_about_msg.setMovementMethod(LinkMovementMethod
                                         .getInstance());
    }


}
