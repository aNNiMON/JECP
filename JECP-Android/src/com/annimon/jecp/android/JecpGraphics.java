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

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        mCanvas.drawLine(x1, y1, x2, y2, mPaint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawRect(x, y, x + width, y + height, mPaint);
    }
    
    @Override
    public void drawString(String text, int x, int y) {
        mCanvas.drawText(text, x, y, mPaint);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        mPaint.setStyle(Paint.Style.FILL);
        mCanvas.drawRect(x, y, x + width, y + height, mPaint);
    }
    
    @Override
    public int getTextWidth(String text) {
        return (int) mPaint.measureText(text);
    }
    
    @Override
    public int getTextHeight() {
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        return (int) (fm.descent - fm.ascent + fm.leading);
    }

    @Override
    public void setColor(int color) {
        mPaint.setColor(color);
    }
    
    @Override
    public void setColor(int red, int green, int blue) {
        mPaint.setARGB(255, red, green, blue);
    }
    
}
