
package com.annimon.jecp.demo.screens;

import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.JecpRandom;
import com.annimon.jecp.Keys;

/**
 * Visual ME cross-platform demo.
 * @author aNNiMON
 */
public class VisualMEScreen extends Screen {
    
    private static final int MAX_DELTA_COLOR = 20;
    
    // Common parameters
    private final int numberOfBranches;
    private final int flexibility;
    private final float screenProportions;
    private final int speed;
    
    private final boolean visualizationAutochange;

    private final float angleDelta;
    private final float[] angles;
    
    // Length of one link
    private final float len;
    
    // Color change mode
    private boolean redChange,  greenChange,  blueChange;
    private int rc,  gc,  bc;
    private int rgbChangeMode,  visualMode,  colorMode;
    
    private int startAngleInt;
    
    private long timestamp;

    public VisualMEScreen(int width, int height) {
        super(width, height);
        // Init common parameters
        numberOfBranches = JecpRandom.rand(10, 20);
        // Angle between branches
        angleDelta = (float) (2 * Math.PI / numberOfBranches);
        // Flexibility of branches
        flexibility = JecpRandom.rand(15, 25);
        angles = new float[flexibility];
        screenProportions = 1f;
        speed = 9;
        visualizationAutochange = true;
        
        // Colors
        rc = 0;
        gc = 0;
        bc = 0;
        rgbChangeMode = 1;
        visualMode = 1;
        colorMode = 1;
        
        // Start rotate angle of branch
        startAngleInt = JecpRandom.rand(360);
                
        // Calculate one link length
        if (width > height) {
            len = height / screenProportions / flexibility;
        } else {
            len = width / screenProportions / flexibility;
        }
        
        timestamp = System.currentTimeMillis();
    }
    
    public void onPaint(JecpGraphics g) {
        super.onPaint(g);
        if (JecpRandom.rand(20) == 5) startAngleInt = JecpRandom.rand(360);
        
        // Start angle
        float startAngle = (float) (startAngleInt * Math.PI / 180);
        
        // Interpolate angles between branches
        angles[0] = (float) (angles[0] + Math.sin(startAngle) / speed);
        for (int i = 1; i < flexibility; i++) {
            angles[i] = (float) (angles[i] + (angles[i - 1] - angles[i]) * 0.1);
        }
        
        for (int j = 0; j < numberOfBranches; j++) {
            float x = 0.5f * width;
            float y = 0.5f * height;
            float tx = 0,  ty = 0;
            final float temp1 = j * angleDelta + angles[1];
            for (int i = 1; i < flexibility; i++) {
                final float temp2 = j * angleDelta + angles[i];
                if (visualMode == 1) {
                    tx = (float) (x + Math.cos(temp1) * len);
                    ty = (float) (y + Math.sin(temp2) * len);
                    x = x + (float) (Math.cos(temp2) * len);
                }
                else if (visualMode == 2) {
                    tx = (float) (x + Math.sin(temp2) * len);
                    ty = (float) (y + Math.cos(temp2) * len);
                }
                else if (visualMode == 3) {
                    tx = (float) (x + Math.tan(temp1) * len);
                    ty = (float) (y + Math.cos(temp2) * len);
                    y = y - (float) (Math.sin(temp2) * len);
                }
                else if (visualMode == 4) {
                    tx = (float) (x + Math.tan(temp1) * len);
                    ty = (float) (y + Math.cos(temp2) * len);
                    x = y - (float) (Math.sin(temp2) * len);
                }
                else if (visualMode == 5) {
                    tx = (float) (x + Math.tan(temp1) * len);
                    ty = (float) (y + Math.cos(temp2) * len);
                    x = x * ty * angleDelta + angles[i] - (float) (Math.sin(temp2) * len);
                }
                else if (visualMode == 6) {
                    x = x + (float) (Math.sin(temp2) * len);
                    y = y + (float) (Math.cos(temp2) * len);
                    tx = (float) (x + Math.cos(temp1) * len);
                    ty = (float) (y + Math.sin(temp1) * len);
                    x = x + (float) (Math.sin(temp2) * len);
                    y = y + (float) (Math.cos(temp2) * len);
                }
                
                if (colorMode == 1) setColor(g, rc, gc, bc);
                else {
                    final int deltaColor = 255 - 255 * i / flexibility;
                    if (colorMode == 2)       setColor(g, deltaColor, 0, 255);
                    else if (colorMode == 3)  setColor(g, deltaColor, 255, 0);
                    else if (colorMode == 4)  setColor(g, deltaColor, 255, 255);
                    
                    else if (colorMode == 5)  setColor(g, 0, deltaColor, 255);
                    else if (colorMode == 6)  setColor(g, 255, deltaColor, 0);
                    else if (colorMode == 7)  setColor(g, 255, deltaColor, 255);
                    
                    else if (colorMode == 8)  setColor(g, 0, 255, deltaColor);
                    else if (colorMode == 9)  setColor(g, 255, 0, deltaColor);
                    else if (colorMode == 10) setColor(g, 255, 255, deltaColor);
                    
                    else if (colorMode == 11) setColor(g, deltaColor, deltaColor, 0);
                    else if (colorMode == 12) setColor(g, deltaColor, deltaColor, 255);
                    
                    else if (colorMode == 13) setColor(g, deltaColor, 0, deltaColor);
                    else if (colorMode == 14) setColor(g, deltaColor, 255, deltaColor);
                    
                    else if (colorMode == 15) setColor(g, 0, deltaColor, deltaColor);                    
                    else if (colorMode == 16) setColor(g, 255, deltaColor, deltaColor);
                    
                    else if (colorMode == 17) setColor(g, rc, deltaColor, 255);
                    else if (colorMode == 18) setColor(g, 255, gc, deltaColor);
                    else if (colorMode == 19) setColor(g, 255, deltaColor, bc);
                    
                    else if (colorMode == 20) setColor(g, bc, rc, deltaColor);
                }
                
                g.drawLine((int)x, (int)y, (int)tx, (int)ty);
                
                x = tx;
                y = ty;
            }
        }
    }
    
    private void setColor(JecpGraphics g, int red, int green, int blue) {
        g.setColor(0xFF000000 | (red << 16) | (green << 8) | blue);
    }

    public void onUpdate() {
        rgb();
        if (System.currentTimeMillis() - timestamp > 800) {
            if (visualizationAutochange) {
                final int rnd = JecpRandom.rand(400);
                if (rnd == 16) colorMode = JecpRandom.rand(MAX_DELTA_COLOR) + 1;
                else if (rnd == 19) visualMode = JecpRandom.rand(6) + 1;
            }
            timestamp = System.currentTimeMillis();
        }
        sleep(10);
    }

    public void onKeyPressed(int key) {
        super.onKeyPressed(key);
        if (key == Keys.KEY_NUM1) {
            colorModeDecrease();
        } else if (key == Keys.KEY_NUM3) {
            colorModeIncrease();
        } else if (key == Keys.KEY_NUM7) {
            visualModeDecrease();
        } else if (key == Keys.KEY_NUM9) {
            visualModeIncrease();
        }
    }

    public void onPointerPressed(int x, int y) {
        super.onPointerPressed(x, y);
        boolean leftPart = (x < (width / 2));
        if (y >= (height / 2)) {
            if (leftPart) colorModeDecrease();
            else colorModeIncrease();
        } else {
            if (leftPart) visualModeDecrease();
            else visualModeIncrease();
        }
    }
    
    private void colorModeDecrease() {
        colorMode--;
        if (colorMode < 1) {
            colorMode = MAX_DELTA_COLOR;
        }
    }
    
    private void colorModeIncrease() {
        colorMode++;
        if (colorMode > MAX_DELTA_COLOR) {
            colorMode = 1;
        }
    }
    
    private void visualModeDecrease() {
        visualMode--;
        if (visualMode < 1) {
            visualMode = 6;
        }
    }
    
    private void visualModeIncrease() {
        visualMode++;
        if (visualMode > 6) {
            visualMode = 1;
        }
    }
    
    private void rgb() {
        if (rgbChangeMode == 1) {
            rc++;
            if (rc > 253) {
                rc--;
                redChange = true;
            }
            if (redChange == true) {
                rc = rc - 2;
            }
            if (rc < 18 && redChange == true) {
                rc = 0;
                redChange = false;
                rgbChangeMode++;
            }
        }
        else if (rgbChangeMode == 2) {
            gc++;
            if (gc > 253) {
                gc--;
                greenChange = true;
            }
            if (greenChange == true) {
                gc = gc - 2;
            }
            if (gc < 18 && greenChange == true) {
                gc = 0;
                greenChange = false;
                rgbChangeMode++;
            }
        }
        else if (rgbChangeMode == 3) {
            bc++;
            if (bc > 253) {
                bc--;
                blueChange = true;
            }
            if (blueChange == true) {
                bc = bc - 2;
            }
            if (bc < 18 && blueChange == true) {
                bc = 0;
                blueChange = false;
                rgbChangeMode++;
            }
        }
        else if (rgbChangeMode == 4) {
            rc++;
            gc++;
            if (rc > 253) {
                rc--;
                gc--;
                redChange = true;
            }
            if (redChange == true) {
                rc = rc - 2;
                gc = gc - 2;
            }
            if (rc < 18 && redChange == true) {
                rc = 0;
                gc = 0;
                redChange = false;
                rgbChangeMode++;
            }
        }
        else if (rgbChangeMode == 5) {
            rc++;
            bc++;
            if (rc > 253) {
                rc--;
                bc--;
                redChange = true;
            }
            if (redChange == true) {
                rc = rc - 2;
                bc = bc - 2;
            }
            if (rc < 18 && redChange == true) {
                rc = 0;
                bc = 0;
                redChange = false;
                rgbChangeMode++;
            }
        }
        else if (rgbChangeMode == 6) {
            gc++;
            bc++;
            if (gc > 253) {
                gc--;
                bc--;
                greenChange = true;
            }
            if (greenChange == true) {
                gc = gc - 2;
                bc = bc - 2;
            }
            if (gc < 18 && greenChange == true) {
                gc = 0;
                bc = 0;
                greenChange = false;
                rgbChangeMode++;
            }
        }
        else if (rgbChangeMode == 7) {
            rc++;
            gc++;
            bc++;
            if (gc > 253) {
                rc--;
                gc--;
                bc--;
                greenChange = true;
            }
            if (greenChange == true) {
                rc = rc - 2;
                gc = gc - 2;
                bc = bc - 2;
            }
            if (gc < 18 && greenChange == true) {
                rc = 0;
                gc = 0;
                bc = 0;
                greenChange = false;
                rgbChangeMode = 1;
            }
        }
    }
}