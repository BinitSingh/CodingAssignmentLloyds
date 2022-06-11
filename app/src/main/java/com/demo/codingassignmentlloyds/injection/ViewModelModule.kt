package com.llyods.assignment.di

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.repository.MovieRepositoryImpl
import com.demo.codingassignmentlloyds.data.webservice.IDataSource
import com.demo.codingassignmentlloyds.domain.EntityMapper
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import com.demo.codingassignmentlloyds.injection.CoroutineScopeDefault
import com.demo.codingassignmentlloyds.injection.CoroutineScopeIO
import com.demo.codingassignmentlloyds.injection.MovieListMappingAnnotation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideMovieRepository(
        dataSource: IDataSource,
        @MovieListMappingAnnotation
        mapper: EntityMapper<MovieItemsListResponse, List<Movie>>
    ): MovieRepositoryImpl =
        MovieRepositoryImpl(dataSource, mapper)

    @Provides
    fun provideMovieListUseCase(
        repository: MovieRepositoryImpl,
        @CoroutineScopeDefault
        dispatcher: CoroutineDispatcher
    ): MovieListUseCase = MovieListUseCase(repository, dispatcher)
}