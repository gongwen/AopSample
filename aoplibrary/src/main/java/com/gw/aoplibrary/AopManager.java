package com.gw.aoplibrary;

/**
 * Created by GongWen on 17/1/17.
 */

public class AopManager {
    public static void setDebugLogEnabled(boolean enabled) {
        DebugLogAspect.setEnabled(enabled);
    }

}
