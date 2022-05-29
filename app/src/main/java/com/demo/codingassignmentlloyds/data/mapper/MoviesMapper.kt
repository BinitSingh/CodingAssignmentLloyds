package com.demo.codingassignmentlloyds.data.mapper

import com.demo.codingassignmentlloyds.data.model.MovieDetailResponse
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.datamodel.MovieDetail

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
                    imDbRating = it.imDbRating
                )
            )
        }
        return list
    }
    // todo need to look for better transdform logic
    fun MovieDetailResponse.toMovieDetail(): MovieDetail = MovieDetail(
        title = title,
        year = year,
        image = image,
        releaseDate = releaseDate,
        runtimeStr = runtimeStr,
        directors = directors,
        writers = writers,
        stars = stars,
        imDbRating = imDbRating,
        imDbRatingVotes = imDbRatingVotes
    )
}