package com.example.olivier.crosswalkwrtc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new TurnOnWifiTask(this).execute();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new EnsureConnectedTask(this).execute();

    }

}
