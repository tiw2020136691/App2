package com.bawei.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bawei.app.MainActivity;

public class WelcomeActivity extends AppCompatActivity {
        private static final int TIME = 5000;
    private static final int GO_MAIN = 100;
    private static final int GO_GUIDE = 101;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_MAIN:
                    goMain();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        boolean isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        if (isFirstIn) {//true
            edit.putBoolean("isFirstIn", false);
            handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
        }else{
            handler.sendEmptyMessageDelayed(GO_MAIN, TIME);
        }
        edit.commit();
    }

    private void goGuide() {
        Toast.makeText(this, "guide", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void goMain() {
        Toast.makeText(this, "Main", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
