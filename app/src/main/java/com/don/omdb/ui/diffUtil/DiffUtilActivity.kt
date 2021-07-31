package com.don.omdb.ui.diffUtil

import android.os.Bundle
import com.don.omdb.databinding.ActivityDiffUtilBinding
import com.don.omdb.ui.BaseActivity
import com.don.omdb.utils.bindingDelegates


/**
 * Created by Gideon Steven Tobing on 31,July,2021.
 * https://www.cicil.co.id/
 * gideon@cicil.co.id
 */
class DiffUtilActivity: BaseActivity() {

    private val binding by bindingDelegates (ActivityDiffUtilBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


}