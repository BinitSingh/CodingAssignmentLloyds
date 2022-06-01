package com.demo.codingassignmentlloyds.data.webservice
import com.demo.codingassignmentlloyds.BuildConfig
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response

interface RetrofitClient {
    @GET("/API/MostPopularMovies/{api_key}")
    suspend fun getMovieList(
        @Path("api_key") key: String = BuildConfig.API_KEY
    ): Response<MovieItemsListResponse>

}