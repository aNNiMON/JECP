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
 * The Graphics class is the abstract base class for all graphics contexts that allow an application to draw onto components that are
 * realized on various devices, as well as onto off-screen images.
 *
 * @author aNNiMON
 */
public abstract class Graphics {

    /**
     * Draws Image with its top-left corner at (x, y).
     *
     * @param image image to be drawn
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public abstract void drawImage(Image image, int x, int y);

    /**
     * Draws a line using current color.
     *
     * @param startingX x where line starts.
     * @param startingY y where line starts.
     * @param endingX x where line ends.
     * @param endingY y where line ends.
     */
    public abstract void drawLine(int startingX, int startingY, int endingX, int endingY);

    /**
     * Draws an empty rectangle using current color.
     *
     * @param x x where rectangle starts.
     * @param y y where rectangle starts.
     * @param width width of rectangle.
     * @param height height of rectangle.
     */
    public abstract void drawRect(int x, int y, int width, int height);

    /**
     * Draws a string using current color and font.
     *
     * @param text string to be drawn.
     * @param x x where string starts.
     * @param y y where string starts.
     */
    public abstract void drawString(String text, int x, int y);

    /**
     * Draws a filled rectangle using current color.
     *
     * @param x x where rectangle starts.
     * @param y y where rectangle starts.
     * @param width width of rectangle.
     * @param height height of rectangle.
     */
    public abstract void fillRect(int x, int y, int width, int height);

    /**
     * Returns width of a text using current font.
     *
     * @param text text to be measured.
     * @return string width in pixels.
     */
    public abstract int getTextWidth(String text);

    /**
     * Returns height of a text line using current font.
     *
     * @return line height in pixels.
     */
    public abstract int getTextHeight();

    /**
     * Sets current color to specified. Color is an integer formed in 0xRRGGBB way.
     *
     * @param color color to be set.
     */
    public abstract void setColor(int color);

    /**
     * Sets current color to specified. Each integer is 0-255 component of color in its RGB-presentation.
     *
     * @param red red component of color.
     * @param green green component of color.
     * @param blue blue component of color.
     */
    public abstract void setColor(int red, int green, int blue);

    /**
     * Draws a polygon using specified options. Its center is located at (centerX, centerY). <br/><br/>
     * Number of points are used to form polygon, assuming that if number of points will be close to positive infinity then polygon will
     * form circle.
     *
     * @param centerX
     * @param centerY
     * @param numPoints points to make polygon.
     * @param radius radius of polygon.
     * @param startAngle angle in radians to start drawing polygon.
     * @throws IllegalArgumentException if numPoints is less than 3.
     */
    public final void drawPolygon(int centerX, int centerY, int numPoints, int radius, double startAngle) throws IllegalArgumentException {
	if (numPoints < 2) {
	    throw new IllegalArgumentException("Number of points for polygon cant be less than 3.");
	}
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
