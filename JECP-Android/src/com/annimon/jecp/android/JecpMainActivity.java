package com.annimon.jecp.android;

import android.app.Activity;
import android.os.Bundle;
import com.annimon.jecp.ApplicationListener;

public abstract class JecpMainActivity extends Activity implements ApplicationListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onStartApp();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseApp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyApp();
    }
    
}
