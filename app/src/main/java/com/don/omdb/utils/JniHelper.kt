package com.don.omdb.utils

/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
object JniHelper {
    init {
        System.loadLibrary("kotlin-jni")
    }

    external fun apiKey(): String
    external fun baseUrl(): String
}