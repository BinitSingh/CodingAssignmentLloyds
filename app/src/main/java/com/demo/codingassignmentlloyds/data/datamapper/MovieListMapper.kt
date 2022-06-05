package com.demo.codingassignmentlloyds.data.datamapper

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.domain.EntityMapper
import com.demo.codingassignmentlloyds.domain.datamodel.Movie

class MovieListMapper: EntityMapper<MovieItemsListResponse, List<Movie>>  {
    override fun transformFrom(entity: MovieItemsListResponse): List<Movie> {
        val list = mutableListOf<Movie>()
        entity.items?.forEach {
            list.add(
                Movie(
                    id = it.id,
                    title = it.title,
                    year = it.year,
                    image = it.image,
                    crew = it.crew,
                    imDbRating = it.imDbRating,
                    imDbRatingCount = it.imDbRatingCount
                )
            )
        }
        return list
    }
}