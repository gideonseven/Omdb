cmake_minimum_required(VERSION 3.22.1)

add_library(kotlin-jni SHARED
            kotlin-jni.c)

# Fix: 16 KB page alignment for Android 15+
target_link_options(kotlin-jni PRIVATE "-Wl,-z,max-page-size=16384")

# Include libraries needed for kotlin-jni lib
target_link_libraries(kotlin-jni
            android
            log)