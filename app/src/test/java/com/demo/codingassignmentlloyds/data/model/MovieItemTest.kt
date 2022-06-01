package com.demo.codingassignmentlloyds.data.model

import com.demo.codingassignmentlloyds.MockFileReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert
import org.junit.Test

class MovieItemTest {

    @Test
    fun createMovieItemFromJson() {
        val fileName = "/MovieItem.json"
        val json = MockFileReader().getResponseFromJson(fileName)
        val movieItem = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(MovieItem::class.java).fromJson(json)

        Assert.assertEquals("tt1745960", movieItem?.id)
        Assert.assertEquals("Top Gun: Maverick", movieItem?.title)
        Assert.assertEquals("2022", movieItem?.year)
    }
}
