package com.don.omdb.utils

import javax.inject.Qualifier


/**
 * Created by gideon on 8/11/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpDefault

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpWithAuth