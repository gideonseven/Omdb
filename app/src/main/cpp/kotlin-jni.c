#include <string.h>
#include <jni.h>

//START OF
//** secret url
//package com.don.omdb.utils
JNIEXPORT jstring JNICALL
Java_com_don_omdb_utils_JniHelper_baseUrl(JNIEnv *env, __unused jobject thiz) {
    return (*env)->NewStringUTF(env, "https://www.omdbapi.com/");
}

JNIEXPORT jstring JNICALL
Java_com_don_omdb_utils_JniHelper_apiKey(JNIEnv *env, __unused jobject thiz) {
    return (*env)->NewStringUTF(env, "3fe861be");
}
//END OF API KEY




