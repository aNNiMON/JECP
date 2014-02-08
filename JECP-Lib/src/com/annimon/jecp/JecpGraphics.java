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

package com.annimon.jecp;

/**
 *
 * @author aNNiMON
 */
public abstract class JecpGraphics {
    
    public abstract void drawImage(Image image, int x, int y);
    
    public abstract void drawLine(int x1, int y1, int x2, int y2);
    
    public abstract void drawRect(int x, int y, int width, int height);
    
    public abstract void drawString(String text, int x, int y);
    
    public abstract void fillRect(int x, int y, int width, int height);
    
    public abstract int getTextWidth(String text);
    
    public abstract int getTextHeight();
    
    public abstract void setColor(int color);
    
    public abstract void setColor(int red, int green, int blue);
    
    
    public final void drawPolygon(int centerX, int centerY, int numPoints, int radius, double startAngle) {
        final double deltaAngle = Math.toRadians(360f / numPoints);
        
        int startX = (int) (centerX + Math.sin(startAngle) * radius);
        int startY = (int) (centerY + Math.cos(startAngle) * radius);
        for (int i = 0; i <= numPoints; i++) {
            final double angle = startAngle + i * deltaAngle;
            final int endX = (int) (centerX + Math.sin(angle) * radius);
            final int endY = (int) (centerY + Math.cos(angle) * radius);
            drawLine(startX, startY, endX, endY);
            
            startX = endX;
            startY = endY;
        }
    }
}
