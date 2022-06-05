package com.llyods.assignment.di

import com.demo.codingassignmentlloyds.data.datamapper.MovieListMapper
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.repository.MovieRepositoryImpl
import com.demo.codingassignmentlloyds.data.webservice.IDataSource
import com.demo.codingassignmentlloyds.dispatcher.CoroutinesDispatchers
import com.demo.codingassignmentlloyds.domain.EntityMapper
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import com.demo.codingassignmentlloyds.injection.MovieListMappingAnnotation
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
    fun provideMovieListUseCase(
        repository: MovieRepositoryImpl,
        dispatcher: CoroutinesDispatchers,
        @MovieListMappingAnnotation
        mapper: EntityMapper<MovieItemsListResponse, List<Movie>>
    ): MovieListUseCase = MovieListUseCase(repository, dispatcher, mapper)



}