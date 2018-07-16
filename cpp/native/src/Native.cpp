#include "com_muandrew_ui_Native.h"
#include "window.h"

// Implementation of native method sayHello() of HelloJNI class
JNIEXPORT void JNICALL Java_com_muandrew_ui_Native_main(JNIEnv *env, jobject thisObj) {
    yrai::window_init();
}