package com.don.omdb.ui.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {
    companion object {
        const val EXTRA_IMDB = "extra_imdb"
    }

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imdbID = intent.extras?.getString(EXTRA_IMDB)

        setContent {
            MaterialTheme {
                DetailActivityContent(
                    viewModel = detailViewModel,
                    imdbID = imdbID,
                    onBackPressed = { finish() }
                )
            }
        }
    }
}
