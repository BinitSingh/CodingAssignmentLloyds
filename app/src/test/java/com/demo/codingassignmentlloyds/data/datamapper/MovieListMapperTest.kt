package com.demo.codingassignmentlloyds.data.datamapper

import com.demo.codingassignmentlloyds.MockFileReader
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MovieListMapperTest {
    @Test
    fun testTransformFrom() {
        val movieListMapperTest = MovieListMapper()
        val fileName = "/MovieItemsListResponse.json"
        val json = MockFileReader().getResponseFromJson(fileName)
        val movieItemsListResponse = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(MovieItemsListResponse::class.java).fromJson(json)
        movieItemsListResponse?.let {
            val movieList = movieListMapperTest.transformFrom(it)
            Assert.assertEquals(movieList.size, it.items?.size)
            Assert.assertEquals(it.items?.get(0)?.id, movieList.get(0).id)
        }
    }
}