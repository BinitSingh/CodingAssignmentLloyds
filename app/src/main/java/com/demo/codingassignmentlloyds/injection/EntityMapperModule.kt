package com.llyods.assignment.di

import com.demo.codingassignmentlloyds.data.datamapper.MovieListMapper
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.domain.EntityMapper
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.injection.MovieListMappingAnnotation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object EntityMapperModule {

    @Provides
    @MovieListMappingAnnotation
    fun provideEntityMapper(): EntityMapper<MovieItemsListResponse, List<Movie>> =
        MovieListMapper()
}