package com.llyods.assignment.di

import com.demo.codingassignmentlloyds.presentation.view.adapter.MovieListAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object UIModule {
    @Provides
    fun provideMovieListAdaptor(): MovieListAdaptor{
        return MovieListAdaptor()
    }
}