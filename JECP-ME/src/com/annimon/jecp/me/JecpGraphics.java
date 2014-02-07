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

package com.annimon.jecp.me;

import com.annimon.jecp.ImageInterface;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author aNNiMON
 */
public class JecpGraphics extends com.annimon.jecp.JecpGraphics {
    
    private final Graphics g;
    
    protected JecpGraphics(Graphics g) {
        this.g = g;
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
    }
    
    public void drawImage(ImageInterface image, int x, int y) {
        g.drawImage(((ImageME)image).image, x, y, Graphics.TOP | Graphics.LEFT);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }
    
    public void drawString(String text, int x, int y) {
        g.drawString(text, x, y, Graphics.TOP | Graphics.LEFT);
    }
    
    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }
    
    public int getTextWidth(String text) {
        return g.getFont().stringWidth(text);
    }
    
    public int getTextHeight() {
        return g.getFont().getHeight();
    }

    public void setColor(int color) {
        g.setColor(color);
    }
    
    public void setColor(int red, int green, int blue) {
        g.setColor(red, green, blue);
    }
}
