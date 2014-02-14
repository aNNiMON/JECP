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

import java.util.Random;

/**
 * Special class to provide random numbers. Introduced to improve basic java.util.Random class.
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
	if (from < 0) {
	    from = 0;
	} else if (from > 255) {
	    from = 255;
	}
	if (to < 0) {
	    to = 0;
	} else if (to > 255) {
	    to = 255;
	}
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
