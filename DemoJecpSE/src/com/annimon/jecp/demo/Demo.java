
package com.annimon.jecp.demo;

import com.annimon.jecp.se.JecpApplication;

/**
 * @author aNNiMON
 */
public class Demo extends JecpApplication {
    
    public static void main(String[] args) {
        new Demo(240, 320);
    }
    
    public Demo(int width, int height) {
        super(new Main(), width, height);
    }
}