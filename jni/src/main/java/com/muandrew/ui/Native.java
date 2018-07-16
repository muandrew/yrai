package com.muandrew.ui;

public class Native {

    public static void init() {
        System.loadLibrary("native");
    }

    public native void main();
}
