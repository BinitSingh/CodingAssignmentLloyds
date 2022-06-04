package com.llyods.assignment.di

import com.demo.codingassignmentlloyds.data.repository.MovieRepositoryImpl
import com.demo.codingassignmentlloyds.data.webservice.IDataSource
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    /**
     * Returns a [MovieRepositoryImpl] instance
     */
    @Provides
    fun provideMovieRepository(webService: IDataSource): MovieRepositoryImpl =
        MovieRepositoryImpl(webService)

    /**
     * Returns a [MovieListUseCase] instance
     */
    @Provides
    fun provideMovieListUseCase(repository: MovieRepositoryImpl): MovieListUseCase =
        MovieListUseCase(repository)


}