package com.annimon.jecp.android;

import android.app.Activity;
import android.os.Bundle;
import com.annimon.jecp.ApplicationListener;

public abstract class JecpApplication extends Activity {
    
    private ApplicationListener listener;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate();
    }
    
    public void init(ApplicationListener listener) {
        this.listener = listener;
        final JecpSurfaceView view = new JecpSurfaceView(this, listener);
        view.setFocusable(true);
        setContentView(view);
    }
    
    protected abstract void onCreate();

    @Override
    protected void onPause() {
        listener.onPauseApp();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        listener.onDestroyApp();
        super.onDestroy();
    }
    
}
