package com.demo.codingassignmentlloyds.data.datamapper

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.domain.EntityMapper
import com.demo.codingassignmentlloyds.domain.datamodel.Movie

class MovieListMapper: EntityMapper<MovieItemsListResponse, List<Movie>>  {
    override fun transformFrom(entity: MovieItemsListResponse): List<Movie> {
        val movieList = mutableListOf<Movie>()
        entity.items?.forEach { movieItem ->
            movieItem.apply {
                movieList.add(
                    Movie(
                        id = id,
                        title = title,
                        year = year,
                        image = image,
                        crew = crew,
                        imDbRating = imDbRating,
                        imDbRatingCount = imDbRatingCount
                    )
                )
            }
        }
        return movieList
    }
}