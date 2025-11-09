package com.don.omdb.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.don.omdb.ui.detail.DetailActivityContent
import com.don.omdb.ui.main.MainScreen

@Composable
fun OmdbNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onMovieClick = { movieId ->
                    navController.navigate("detail/$movieId")
                }
            )
        }

        composable("detail/{imdbId}") { backStackEntry ->
            val imdbId = backStackEntry.arguments?.getString("imdbId")
            DetailActivityContent (
                viewModel = hiltViewModel(),
                imdbID = imdbId,
                onBackPressed = { navController.navigateUp() }
            )
        }
    }

}