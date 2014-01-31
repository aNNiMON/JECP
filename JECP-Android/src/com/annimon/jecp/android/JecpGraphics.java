package com.annimon.jecp.android;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 *
 * @author aNNiMON
 */
public class JecpGraphics implements com.annimon.jecp.JecpGraphics {
    
    private final Paint mPaint;
    private Canvas mCanvas;

    public JecpGraphics(Paint paint) {
        mPaint = paint;
    }
    
    public void setCanvas(Canvas mCanvas) {
        this.mCanvas = mCanvas;
    }
    
    public void drawLine(int x1, int y1, int x2, int y2) {
        mCanvas.drawLine(x1, y1, x2, y2, mPaint);
    }

    public void drawRect(int x, int y, int width, int height) {
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawRect(x, y, x + width, y + height, mPaint);
    }

    public void fillRect(int x, int y, int width, int height) {
        mPaint.setStyle(Paint.Style.FILL);
        mCanvas.drawRect(x, y, x + width, y + height, mPaint);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }
    
}
