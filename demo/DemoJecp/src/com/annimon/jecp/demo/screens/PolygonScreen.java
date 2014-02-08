
package com.annimon.jecp.demo.screens;

import com.annimon.jecp.Graphics;
import com.annimon.jecp.JecpRandom;

/**
 * Drawing polygon demo.
 * @author aNNiMON
 */
public class PolygonScreen extends Screen {
    
    private final int color;
    private final int[] numPoints;
    private double startAngle;
    private int radius, radiusDelta;
    
    public PolygonScreen(int width, int height) {
        super(width, height);
        startAngle = 0;
        radius = 0;
        radiusDelta = 1;
        color = JecpRandom.randomColor(100, 255);
        numPoints = new int[JecpRandom.rand(3, 7)];
        for (int i = 0; i < numPoints.length; i++) {
            numPoints[i] = JecpRandom.rand(3, 8);
        }
    }
    
    public void onPaint(Graphics g) {
        super.onPaint(g);
        g.setColor(color);
        for (int i = 0; i < numPoints.length; i++) {
            g.drawPolygon(width / 2, height / 2, numPoints[i], width / (i*2+1) + radius,
                    startAngle * (i % 2 == 0 ? 1d : -1d));
        }
    }

    public void onUpdate() {
        startAngle += 0.02;
        if (startAngle >= 2 * Math.PI) startAngle = 0;
        
        if (radius > (width / 2)) radiusDelta =  -radiusDelta;
        else if (radius < (-width / 2)) radiusDelta =  -radiusDelta;
        radius += radiusDelta;
        sleep(10);
    }
    
    public void onKeyPressed(int key) {
        radiusDelta = -radiusDelta;
        super.onKeyPressed(key);
    }
    
}
