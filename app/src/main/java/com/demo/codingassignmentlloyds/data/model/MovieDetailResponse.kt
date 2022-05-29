package com.demo.codingassignmentlloyds.data.model

import com.squareup.moshi.Json

data class MovieDetailResponse(

    @Json(name = "id" )
    val id : String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "originalTitle")
    val originalTitle: String?,
    @Json(name = "fullTitle")
    val fullTitle: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "year")
    val year: String?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "releaseDate")
    val releaseDate: String?,
    @Json(name = "runtimeStr")
    val runtimeStr: String?,
    @Json(name = "directors")
    val directors: String?,
    @Json(name = "writers")
    val writers: String?,
    @Json(name = "writerList")
    val writerList: List<IDNameData>?,
    @Json(name = "stars")
    val stars: String?,
    @Json(name = "starList")
    val starList:List<IDNameData>?,
    @Json(name = "actorList")
    val actorList: List<IDNameData>?,
    @Json(name = "fullCast")
    val fullCast: String?,
    @Json(name = "genres")
    val genres: String?,
    @Json(name = "companies")
    val companies: String?,
    @Json(name = "countries")
    val countries: String?,
    @Json(name = "languages")
    val languages: String?,
    @Json(name = "contentRating")
    val contentRating: String?,
    @Json(name = "imDbRating")
    val imDbRating: String?,
    @Json(name = "imDbRatingVotes")
    val imDbRatingVotes  : String?,
    @Json(name = "metacriticRating")
    val metacriticRating : String?,
    @Json(name = "ratings")
    val ratings: String?,
    @Json(name = "wikipedia")
    val wikipedia: String?,
    @Json(name = "posters")
    val posters: String?,
    @Json(name = "images")
    val images: String?,
    @Json(name = "trailer")
    val trailer: String?,
    @Json(name = "boxOffice")
    val boxOffice: BoxOffice?,
    @Json(name = "tagline")
    val tagline: String?,
    @Json(name = "keywords")
    val keywords: String?,
    @Json(name = "keywordList")
    val keywordList: ArrayList<String>,
    @Json(name = "tvSeriesInfo")
    val tvSeriesInfo: String?,
    @Json(name = "tvEpisodeInfo")
    val tvEpisodeInfo: String?,
    @Json(name = "errorMessage")
    val errorMessage: String?
)
