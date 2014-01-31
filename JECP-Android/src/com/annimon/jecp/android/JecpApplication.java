package com.annimon.jecp.android;

import android.app.Activity;
import android.os.Bundle;
import com.annimon.jecp.ApplicationListener;

public abstract class JecpApplication extends Activity implements ApplicationListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final JecpSurfaceView view = new JecpSurfaceView(this);
        view.setFocusable(true);
        setContentView(view);
    }

    @Override
    protected void onPause() {
        onPauseApp();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        onDestroyApp();
        super.onDestroy();
    }
    
}
