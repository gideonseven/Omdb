package com.don.omdb.utils

import android.os.Bundle
import com.google.gson.GsonBuilder
import org.jetbrains.annotations.NotNull
import timber.log.Timber


/**
 * Created by Gideon Steven Tobing on 01,August,2021.
 * https://www.cicil.co.id/
 * gideon@cicil.co.id
 */
fun prettyPrinting(@NotNull any: Any){
    Timber.e("=== Pretty Printing ${GsonBuilder().setPrettyPrinting().create().toJson(any)}")
}

fun Bundle.logValue(){
    for (key in this.keySet()) {
        Timber.e("KEY ==> $key  : VALUE ==>  ${this.get(key)}")
    }
}