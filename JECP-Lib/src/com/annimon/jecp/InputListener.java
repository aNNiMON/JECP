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
 * Listener for available input events.
 *
 * @author aNNiMON
 */
public interface InputListener {

    /**
     * Called on key pressing.
     *
     * @param key pressed key code.
     */
    public void onKeyPressed(int key);

    /**
     * Called on key releasing.
     *
     * @param key released key code.
     */
    public void onKeyReleased(int key);

    /**
     * Called on pointer pressed. Not available on Java ME devices.
     *
     * @param x
     * @param y
     */
    public void onPointerPressed(int x, int y);

    /**
     * Called on pointer released. Not available on Java ME devices.
     *
     * @param x
     * @param y
     */
    public void onPointerReleased(int x, int y);

    /**
     * Called on pointer dragged. Not available on Java ME devices.
     *
     * @param x
     * @param y
     */
    public void onPointerDragged(int x, int y);
}
