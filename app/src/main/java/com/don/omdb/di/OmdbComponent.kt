package com.don.omdb.di

import com.don.omdb.ui.detail.DetailActivity
import com.don.omdb.ui.diffUtil.DiffUtilActivity
import com.don.omdb.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@Singleton
@Component(modules = [OmdbModule::class])
interface OmdbComponent {
    fun inject(target: MainActivity)
    fun inject(target: DetailActivity)
    fun inject(target: DiffUtilActivity)
}