
package com.annimon.jecp.demo;

import com.annimon.jecp.se.Application;

/**
 * @author aNNiMON
 */
public class Demo extends Application {
    
    public static void main(String[] args) {
        new Demo(480, 640);
    }
    
    public Demo(int width, int height) {
        super(new Main(), width, height);
    }
}