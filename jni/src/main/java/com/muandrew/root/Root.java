package com.muandrew.root;

import com.muandrew.ui.Native;

import java.awt.image.ColorModel;

class Root {

    // -Djava.library.path="${workspace_loc:project}\lib;${env_var:PATH}"
    // vm arguments
    // -Djava.library.path="./cpp/build/lib"
    public static void main(String[] args) {
        ColorModel.getRGBdefault();
        String javaLibPath = System.getProperty("java.library.path");
        System.out.println(javaLibPath);
        Native.init();
        Native n = new Native();
        n.main();
    }
}
