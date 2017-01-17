package com.gw.aopsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gw.aoplibrary.annotation.DebugLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1();
        test2("gong", "wen");
    }

    @DebugLog
    public void test1() {
    }

    @DebugLog
    public String test2(String firstName, String secondName) {
        return firstName + " " + secondName;
    }
}
