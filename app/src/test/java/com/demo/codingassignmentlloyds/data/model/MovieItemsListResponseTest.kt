package com.demo.codingassignmentlloyds.data.model

import com.demo.codingassignmentlloyds.MockFileReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert
import org.junit.Test

class MovieItemsListResponseTest {


    @Test
    fun createMovieItemFromJson() {
        val fileName = "/MovieItemsListResponse.json"
        val json = MockFileReader().getResponseFromJson(fileName)
        val movieItemsListResponse = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(MovieItemsListResponse::class.java).fromJson(json)

        Assert.assertEquals("", movieItemsListResponse?.errorMessage)
        Assert.assertEquals(100, movieItemsListResponse?.items?.size)

        val movieItem = movieItemsListResponse?.items?.get(0)
        Assert.assertEquals("tt1745960", movieItem?.id)
        Assert.assertEquals("Top Gun: Maverick", movieItem?.title)
        Assert.assertEquals("2022", movieItem?.year)
    }
}

