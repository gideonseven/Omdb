package com.don.omdb.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.utils.FakeDataDummy
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


/**
 * Created by gideon on 05,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class MainViewModelTest() : ViewModel() {

 /*   @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    lateinit var viewModel: MainViewModel


    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun getMovies() {
        val dummyMovies = FakeDataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MdlMovieList>>()
        movies.value = dummyMovies

        `when`(viewModel.getMovies()).thenReturn(movies)
        val observer = mock(Observer::class.java) as Observer<List<MdlMovieList>>
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }*/

}