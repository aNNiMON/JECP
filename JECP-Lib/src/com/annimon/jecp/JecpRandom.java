package com.annimon.jecp;

import java.util.Random;

/**
 *
 * @author aNNiMON
 */
public class JecpRandom {
    
    private static final Random rnd = new Random();
    
    public static int rand(int to) {
        return rnd.nextInt(to);
    }
    
    public static int rand(int from, int to) {
        return rnd.nextInt(to - from) + from;
    }
    
    public static float rand(float from, float to) {
        return rnd.nextFloat() * (to - from) + from;
    }
    
    public static double rand(double from, double to) {
        return rnd.nextDouble() * (to - from) + from;
    }
    
    public static int randomColor(int from, int to) {
        if (from < 0) from = 0;
        else if (from > 255) from = 255;
        if (to < 0) to = 0;
        else if (to > 255) to = 255;
        if (from == to) {
            return (0xFF000000 | (from << 16) | (from << 8) | from);
        } else if (from > to) {
            final int temp = to;
            to = from;
            from = temp;
        }
        
        final int red = rand(from, to);
        final int green = rand(from, to);
        final int blue = rand(from, to);
        
        return (0xFF000000 | (red << 16) | (green << 8) | blue);
    }
}
