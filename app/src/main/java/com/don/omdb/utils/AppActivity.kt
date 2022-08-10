package com.don.omdb.utils

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by gideon on 8/11/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
abstract class AppActivity<T : ViewDataBinding>(@LayoutRes contentLayoutId: Int) :
    CoreActivity<T>(contentLayoutId) {

    override fun obsoletedApp(message: String?) {
        super.obsoletedApp(message)
        Timber.e("aa obsoleted AppBase $message")
    }

    override fun maintenance(message: String?) {
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
    }
}