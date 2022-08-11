#include <string.h>
#include <jni.h>

//START OF
//** secret url
//package com.don.omdb.utils
JNIEXPORT jstring JNICALL
Java_com_don_omdb_utils_JniHelper_baseUrl(JNIEnv *env, __unused jobject thiz) {
//    return (*env)->NewStringUTF(env, "https://api.unsplash.com/");
    return (*env)->NewStringUTF(env, "https://api.themoviedb.org/3/");
}

JNIEXPORT jstring JNICALL
Java_com_don_omdb_utils_JniHelper_apiKey(JNIEnv *env, __unused jobject thiz) {
//    return (*env)->NewStringUTF(env, "1i2IouUHVPwUImfJnF-WSJ5JtBUFX3gJ7H89DhrjFAA");
    return (*env)->NewStringUTF(env, "8bc38114f17a8e3002a5e151b1ac2bda");
}
//END OF API KEY




