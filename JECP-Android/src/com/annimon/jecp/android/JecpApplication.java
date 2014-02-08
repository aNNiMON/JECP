/*
 * Copyright 2014 Victor Melnik <annimon119@gmail.com>, and
 * individual contributors as indicated by the @authors tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.annimon.jecp.android;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.annimon.jecp.ApplicationListener;
import com.annimon.jecp.Jecp;

public abstract class JecpApplication extends Activity {
    
    static AssetManager sAssetManager;
    
    private ApplicationListener listener;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sAssetManager = getAssets();
        Jecp.helper = new JecpHelper(this);
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
