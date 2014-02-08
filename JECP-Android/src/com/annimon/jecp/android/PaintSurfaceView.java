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

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.annimon.jecp.ApplicationListener;
import com.annimon.jecp.Jecp;

/**
 *
 * @author aNNiMON
 */
public class PaintSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final ApplicationListener mListener;
    private final Application mActivity;
    private final JecpGraphics mGraphics;

    private final Paint mPaint;
    private final SurfaceHolder mSurfaceHolder;
    private final DrawingThread mThread;

    public PaintSurfaceView(Application activity, ApplicationListener listener) {
        super(activity);
        mActivity = activity;
        mListener = listener;
        
        requestFocus();
        setFocusableInTouchMode(true);
        
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(PaintSurfaceView.this);
        
        mThread = new DrawingThread();
        mThread.mKeepRunning = true;
        
        mPaint = new Paint();
        mGraphics = new JecpGraphics(mPaint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mListener.onStartApp(getWidth(), getHeight());
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int newW, int newH) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.mKeepRunning = false;
        boolean retry = true;
        while (retry) {
            try {
                mThread.join();
                retry = false;
            } catch (InterruptedException e) { }
        }
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        mGraphics.setCanvas(canvas);
        mListener.onPaint(mGraphics);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Jecp.inputListener != null) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Jecp.inputListener.onKeyPressed(keyCode);
                return true;
            } else if (event.getAction() == KeyEvent.ACTION_UP) {
                Jecp.inputListener.onKeyReleased(keyCode);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Jecp.inputListener != null) {
            final int x = (int) event.getX();
            final int y = (int) event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Jecp.inputListener.onPointerPressed(x, y);
                    return true;
                case MotionEvent.ACTION_UP:
                    Jecp.inputListener.onPointerReleased(x, y);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    Jecp.inputListener.onPointerDragged(x, y);
                    return true;
            }
        }
        return super.onTouchEvent(event);
    }

    private class DrawingThread extends Thread {

        private boolean mKeepRunning = true;

        @Override
        public void run() {
            Canvas c;

            while (mKeepRunning) {
                mListener.onUpdate();
                c = null;

                try {
                    c = mSurfaceHolder.lockCanvas();
                    if (c != null) {
                        synchronized (mSurfaceHolder) {
                            onDraw(c);
                        }
                    }
                } finally {
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}
