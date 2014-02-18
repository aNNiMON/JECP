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
package com.annimon.jecp.se;

import com.annimon.jecp.Image;
import java.awt.Color;
import java.awt.Graphics;

/**
 * The graphics class to be used in desktop apps. Paints using Graphics2D methods.
 *
 * @author aNNiMON
 */
public class JecpGraphics extends com.annimon.jecp.Graphics {

    private final Graphics g;

    protected JecpGraphics(Graphics g) {
	this.g = g;
    }

    @Override
    public void drawImage(Image image, int x, int y) {
	g.drawImage(((ImageSE) image).image, x, y, null);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
	g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
	g.drawRect(x, y, width, height);
    }

    @Override
    public void drawString(String text, int x, int y) {
	g.drawString(text, x, y + getTextHeight());
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
	g.fillRect(x, y, width, height);
    }
    
    @Override
    public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        final int[] x = new int[] { x1, x2, x3 };
        final int[] y = new int[] { y1, y2, y3 };
        g.fillPolygon(x, y, 3);
    }

    @Override
    public int getTextWidth(String text) {
	return g.getFontMetrics().stringWidth(text);
    }

    @Override
    public int getTextHeight() {
	return g.getFontMetrics().getHeight();
    }

    @Override
    public void setColor(int color) {
	g.setColor(new Color(color));
    }

    @Override
    public void setColor(int red, int green, int blue) {
	g.setColor(new Color(red, green, blue));
    }
}
