package com.muandrew.root;

import com.muandrew.ui.Native;

import java.awt.image.ColorModel;

class Root {

    public static void main(String[] args) {
        ColorModel.getRGBdefault();
        String javaLibPath = System.getProperty("java.library.path");
        System.out.println(javaLibPath);
        Native.init();
        Native n = new Native();
        n.main();
    }
}
