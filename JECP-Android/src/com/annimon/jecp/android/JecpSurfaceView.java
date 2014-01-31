package com.annimon.jecp.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.annimon.jecp.ApplicationListener;

/**
 *
 * @author aNNiMON
 */
public class JecpSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final ApplicationListener mListener;
    private final JecpApplication mActivity;
    private final JecpGraphics mGraphics;

    private final Paint mPaint;
    private final SurfaceHolder mSurfaceHolder;
    private final DrawingThread mThread;
    
    private boolean mIsInit;

    public JecpSurfaceView(JecpApplication activity, ApplicationListener listener) {
        super(activity);
        setWillNotDraw(false);
        mActivity = activity;
        mListener = listener;
        
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
        invalidate();
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
        if (!mIsInit) {
            mListener.onStartApp(canvas.getWidth(), canvas.getHeight());
            mThread.start();
            mIsInit = true;
        }
        
        mGraphics.setCanvas(canvas);
        mListener.onPaint(mGraphics);
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

                try {
                    Thread.sleep(5L);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
