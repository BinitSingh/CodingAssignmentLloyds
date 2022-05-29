package com.demo.codingassignmentlloyds.data.webservice
import com.demo.codingassignmentlloyds.BuildConfig
import com.demo.codingassignmentlloyds.data.model.MovieDetailResponse
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response

interface RetrofitClient {
    @GET("/API/MostPopularMovies/{api_key}")
    suspend fun getMovieList(
        @Path("api_key") key: String = BuildConfig.API_KEY
    ): Response<MovieItemsListResponse>

    @GET("/API/Title/{api_key}/{movie_id}")
    suspend fun getMovieDetailResponse(
        @Path("api_key") key: String = BuildConfig.API_KEY,
        @Path("movie_id") movieId: String?,
    ): Response<MovieDetailResponse>

}