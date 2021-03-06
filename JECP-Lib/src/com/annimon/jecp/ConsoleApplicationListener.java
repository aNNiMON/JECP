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
public interface ConsoleApplicationListener {
    
    public void onStartApp(Console console);
    
    
    public interface Console {
        
        public void print(String text);
        
        public void println(String text);
        
        public String read();
        
        public String read(final String title);
        
        public String readln();
    }
}
