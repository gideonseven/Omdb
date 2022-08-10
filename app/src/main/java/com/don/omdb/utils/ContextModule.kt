package com.don.omdb.utils

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    @ActivityScoped
    fun providesFragmentManager(activity: FragmentActivity): FragmentManager = activity.supportFragmentManager
}

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    @FragmentScoped
    fun provideChildFragmentManager(fragment: Fragment): FragmentManager = fragment.childFragmentManager
}