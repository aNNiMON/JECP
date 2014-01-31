package com.annimon.jecp.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 *
 * @author aNNiMON
 */
public class JecpSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final JecpApplication mActivity;
    private final JecpGraphics mGraphics;

    private final Paint mPaint;
    private final SurfaceHolder mSurfaceHolder;
    private final DrawingThread mThread;
    
    private boolean mIsInit;

    public JecpSurfaceView(JecpApplication activity) {
        super(activity);
        mActivity = activity;
        
        mIsInit = false;

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(JecpSurfaceView.this);
        
        mThread = new DrawingThread();
        mThread.mKeepRunning = true;
        
        mPaint = new Paint();
        mGraphics = new JecpGraphics(mPaint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int newW, int newH) {
        
    }

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
        if (!mIsInit) {
            mActivity.onStartApp(canvas.getWidth(), canvas.getHeight());
            mIsInit = true;
        }
        
        mGraphics.setCanvas(canvas);
        mActivity.onPaint(mGraphics);
    }

    private class DrawingThread extends Thread {

        private boolean mKeepRunning = true;

        @Override
        public void run() {
            Canvas c;

            while (mKeepRunning) {
                mActivity.onUpdate();
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

                try {
                    Thread.sleep(5L);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
