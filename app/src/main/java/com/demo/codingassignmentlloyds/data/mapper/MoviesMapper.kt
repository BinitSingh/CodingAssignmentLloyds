package com.demo.codingassignmentlloyds.data.mapper

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie

object MoviesMapper {

    fun MovieItemsListResponse.toMovieList(): List<Movie> {
        val list = mutableListOf<Movie>()
        items?.forEach {
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