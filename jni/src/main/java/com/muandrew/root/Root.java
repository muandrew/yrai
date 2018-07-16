package com.muandrew.root;

import com.muandrew.ui.Native;

class Root {

    public static void main(String[] args) {
        String javaLibPath = System.getProperty("java.library.path");
        System.out.println(javaLibPath);
        Native.init();
        Native n = new Native();
        n.main();
    }
}
