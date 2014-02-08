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
public class Fps {

    private static final int MAX_FPS = 30;
    private static final int MAX_DELAY = 1000 / MAX_FPS;

    private static long currentFps;
    private static long counter = 0, startTime = 0;
    private static long startTimeForMeasureDelay = 0;

    public static long getFps() {
        counter++;
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }
        if ((System.currentTimeMillis() - startTime) >= 1000) {
            currentFps = counter;
            counter = 0;
            startTime = System.currentTimeMillis();
        }
        return currentFps;
    }

    public static void startMeasuringDelay() {
        startTimeForMeasureDelay = System.currentTimeMillis();
    }

    public static long getDelay() {
        long delay = System.currentTimeMillis() - startTimeForMeasureDelay;
        return (delay > MAX_DELAY ? 0 : MAX_DELAY - delay);
    }
}
