package com.don.omdb.ui.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.don.omdb.R
import com.don.omdb.databinding.ActivityContainerBinding
import com.don.omdb.utils.AppActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity<ActivityContainerBinding>(R.layout.activity_container) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.content_frame) as NavHostFragment
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.nav_main)
            val navController = navHostFragment.navController
            navController.setGraph(graph, intent.extras)
        }
    }
}
