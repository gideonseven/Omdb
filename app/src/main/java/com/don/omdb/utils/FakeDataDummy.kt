package com.don.omdb.utils

import com.don.omdb.data.remote.MdlDetail
import com.don.omdb.data.remote.MdlMovieList

/**
 * Created by gideon on 05,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */

object FakeDataDummy {
    fun generateDummyMovies(): ArrayList<MdlMovieList> {
        val moviesEntities = arrayListOf<MdlMovieList>()
        /*  val Type: String? = null,
          val Year: String? = null,
          val imdbID: String? = null,
          val Poster: String? = null,
          val Title: String? = null*/
        moviesEntities.add(
                MdlMovieList(
                        "",
                        "movie",
                        "2016",
                        "tt2788732",
                        "https://m.media-amazon.com/images/M/MV5BMjA4NDYxNzYzOF5BMl5BanBnXkFtZTgwNzU1NzcwODE@._V1_SX300.jpg",
                        "Pete's Dragon"
                )
        )
        moviesEntities.add(
                MdlMovieList(
                        "",
                        "movie",
                        "1972",
                        "tt0068935",
                        "https://m.media-amazon.com/images/M/MV5BNGJlMTU5ZTYtZGNlOC00OGY5LTg0ODEtMWFkMTgyODc4OWZmXkEyXkFqcGdeQXVyNDIyMjczNjI@._V1_SX300.jpg",
                        "The Way of the Dragon"
                )
        )
        moviesEntities.add(
                MdlMovieList(
                        "",
                        "Dragon: The Bruce Lee Story",
                        "1993",
                        "tt0106770",
                        "movie",
                        "https://m.media-amazon.com/images/M/MV5BMjA1MTQxNzAtM2MyOC00NDBhLWFlNmEtOWZlM2E5MjNlODU2XkEyXkFqcGdeQXVyNDAxNjkxNjQ@._V1_SX300.jpg"
                )
        )
        return moviesEntities
    }

    fun generateDummyDetail(): MdlDetail {
        return MdlDetail("", "", "", "", "", "", "", "", "", "", "tt2788732", "", "", "Pete's Dragon", "", "", "", "", "", "2016")
    }
}