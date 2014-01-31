package com.annimon.jecp.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.annimon.jecp.ApplicationListener;

public abstract class JecpApplication extends Activity {
    
    private ApplicationListener listener;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
