package com.edu.squash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.edu.squash.MainActivity;
import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.ui.login.LoginAty;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_splash);
        findViewById(R.id.iv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMain();
            }
        }, 2500);
    }

    private void toMain() {
        if (MyApp.getApp().isLogin()) {
            startActivity(new Intent(this, MainActivity.class));
        } else
            startActivity(new Intent(this, LoginAty.class));

    }

}
