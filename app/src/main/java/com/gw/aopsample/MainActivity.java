package com.gw.aopsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gw.aoplibrary.annotation.AsyncMethod;
import com.gw.aoplibrary.annotation.DebugLog;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1();
        for (int i = 0; i < 10; i++) {
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
}
