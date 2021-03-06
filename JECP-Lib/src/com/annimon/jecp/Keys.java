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
 * Keyboard codes.
 * @author aNNiMON
 */
public class Keys {
    
    public static boolean wasdAsDpad = false;
    public static boolean numericdAsDpad = false;
    
    public static final int
            DPAD_LEFT = -501,
            DPAD_UP = -502,
            DPAD_RIGHT = -503,
            DPAD_DOWN = -504,
            DPAD_FIRE = -505;
    
    /** Java ME keys */
    public static final int
            KEY_NUM0 = 48,
            KEY_NUM1 = 49,
            KEY_NUM2 = 50,
            KEY_NUM3 = 51,
            KEY_NUM4 = 52,
            KEY_NUM5 = 53,
            KEY_NUM6 = 54,
            KEY_NUM7 = 55,
            KEY_NUM8 = 56,
            KEY_NUM9 = 57,
            KEY_STAR = 42,
            KEY_POUND = 35,
            KEY_BACK = -11,
            LEFT_SOFT = -6,
            RIGHT_SOFT = -7;
    
    /** Java SE keys */
    public static final int
            VK_LEFT = 37,
            VK_UP = 38,
            VK_RIGHT = 39,
            VK_DOWN = 40,
            VK_ENTER = 10,
            VK_SPACE = 32,
            VK_ESCAPE = 27,
            VK_W = 87,
            VK_S = 83,
            VK_A = 65,
            VK_D = 68;
    
    /** Android keys */
    public static final int
            ANDRO_LEFT = 21,
            ANDRO_UP = 19,
            ANDRO_RIGHT = 22,
            ANDRO_DOWN = 20,
            ANDRO_ENTER = 66,
            ANDRO_SPACE = 62,
            ANDRO_W = 51,
            ANDRO_S = 47,
            ANDRO_A = 29,
            ANDRO_D = 32,
            ANDRO_BACKSPACE = 67,
            ANDRO_MENU = 82,
            ANDRO_BACK = 4,
            ANDRO_FOCUS = 80,
            ANDRO_CAMERA = 27;
    
    
    public static final int convertToDpad(int key) {
        if (wasdAsDpad) {
            switch(key) {
                case VK_W: return DPAD_UP;
                case VK_S: return DPAD_DOWN;
                case VK_A: return DPAD_LEFT;
                case VK_D: return DPAD_RIGHT;
                case VK_ENTER:
                case VK_SPACE:
                    return DPAD_FIRE;
            }
        }
        if (numericdAsDpad) {
            switch(key) {
                case KEY_NUM2: return DPAD_UP;
                case KEY_NUM8: return DPAD_DOWN;
                case KEY_NUM4: return DPAD_LEFT;
                case KEY_NUM6: return DPAD_RIGHT;
                case KEY_NUM5: return DPAD_FIRE;
            }
        }
        switch(key) {
            case VK_UP: return DPAD_UP;
            case VK_DOWN: return DPAD_DOWN;
            case VK_LEFT: return DPAD_LEFT;
            case VK_RIGHT: return DPAD_RIGHT;
        }
        return key;
    }
}
