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
package com.annimon.jecp.me;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 * Load and save preferences to the record store.
 * @author aNNiMON
 */
public final class Prefs {

    private static final String RMS_NAME = "jecp_prefs";
    
    private static RecordStore rmsStore;
    
    public static byte[] load() {
        try {
            rmsStore = RecordStore.openRecordStore(RMS_NAME, true);
            return rmsStore.getRecord(1);
        } catch (RecordStoreException ex) {
            rmsStore = null;
        }
        return null;
    }
    
    public static void save(byte[] data) {
        try {
            rmsStore = RecordStore.openRecordStore(RMS_NAME, true);
            rmsStore.setRecord(1, data, 0, data.length);
        } catch (InvalidRecordIDException ridex) {
            try {
                rmsStore.addRecord(data, 0, data.length);
            } catch (RecordStoreException ex) { }
        } catch (RecordStoreException ex) {
        }
        if (rmsStore != null) {
            try {
                rmsStore.closeRecordStore();
                rmsStore = null;
            } catch (RecordStoreException ex) { }
        }
    }
}
