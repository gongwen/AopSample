package com.gw.aopsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gw.aoplibrary.annotation.AsyncMethod;
import com.gw.aoplibrary.annotation.DebugLog;
import com.gw.viewfinder.ViewFinder;
import com.gw.viewfinder.annotation.BindView;
import com.gw.viewfinder.annotation.OnClick;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.activity_main)
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewFinder.inject(this);
        tv.setText("apt demo");

        test1();
        for (int i = 0; i < 20; i++) {
            test2("gong", "wen", i);
        }
    }

    @DebugLog
    public void test1() {
    }

    @AsyncMethod
    @DebugLog
    public String test2(String firstName, String secondName, int index) {
        Random mRandom = new Random();
        try {
            Thread.sleep((3 + mRandom.nextInt(3)) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return firstName + " " + secondName;
    }

    @OnClick({R.id.activity_main, R.id.tv})
    public void click() {
    }
}
