package com.don.omdb.utils

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding


/**
 * Created by gideon on 8/11/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
abstract class AppFragment<T : ViewDataBinding>(@LayoutRes contentLayoutId: Int): CoreFragment<T>(contentLayoutId)