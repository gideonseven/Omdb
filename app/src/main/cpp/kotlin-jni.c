#include <string.h>
#include <jni.h>

//START OF
//** secret url
//package com.don.omdb.utils
JNIEXPORT jstring JNICALL
Java_com_don_omdb_utils_JniHelper_baseUrl(JNIEnv *env, __unused jobject thiz) {
    return (*env)->NewStringUTF(env, "https://api.unsplash.com/");
}

JNIEXPORT jstring JNICALL
Java_com_don_omdb_utils_JniHelper_apiKey(JNIEnv *env, __unused jobject thiz) {
    return (*env)->NewStringUTF(env, "1i2IouUHVPwUImfJnF-WSJ5JtBUFX3gJ7H89DhrjFAA");
}
//END OF API KEY




