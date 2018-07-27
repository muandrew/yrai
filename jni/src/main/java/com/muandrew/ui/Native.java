package com.muandrew.ui;

import com.muandrew.util.OSType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Native {

    public static void loadLibrary(String libraryName) {
        OSType type = OSType.computeType();
        String fileName = getFileName(type, libraryName);
        File workingDirectory = Paths.get("").toAbsolutePath().toFile();
        File library = new File(workingDirectory, fileName);
        InputStream is = Native.class.getResourceAsStream(File.separator + fileName);
        try {
            Files.copy(is, library.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            library.delete();
        }
        System.loadLibrary(libraryName);
        library.deleteOnExit();
    }

    public static String getFileName(OSType type, String libraryName) {
        switch (type) {
            case WINDOWS:
                return "lib" + libraryName + ".dll";
            case MAC:
                return "lib" + libraryName + ".dylib";
            case DEFAULT:
            default:
                return "lib" + libraryName + ".so";
        }
    }

    public static void init() {
        loadLibrary("native");
    }

    public native void main();
}
