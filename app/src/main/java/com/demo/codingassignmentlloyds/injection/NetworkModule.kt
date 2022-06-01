package com.demo.codingassignmentlloyds.injection

import com.demo.codingassignmentlloyds.BuildConfig
import com.demo.codingassignmentlloyds.data.webservice.ApiCallsImpl
import com.demo.codingassignmentlloyds.data.webservice.IApiCalls
import com.demo.codingassignmentlloyds.data.webservice.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /**
     * Provides [HttpLoggingInterceptor] instance with logging level set to body
     */
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Provides an [OkHttpClient]
     * @param loggingInterceptor [HttpLoggingInterceptor] instance
     */
    @Provides
    fun provideOKHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient().apply {
        OkHttpClient.Builder().apply {
            callTimeout(40, TimeUnit.SECONDS)
            connectTimeout(40, TimeUnit.SECONDS)
            readTimeout(40, TimeUnit.SECONDS)
            writeTimeout(40, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            build()
        }
    }

    /**
     * Returns a [MoshiConverterFactory] instance
     */
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    /**
     * Returns an instance of the [RetrofitClient] interface for the retrofit class
     */
    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): RetrofitClient =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build().run {
                create(RetrofitClient::class.java)
            }

    /**
     * Returns a [IApiCalls] impl -> ApiCallsImpl
     */
    @Provides
    fun provideRetrofitService(retrofit: RetrofitClient): IApiCalls = ApiCallsImpl(retrofit)
}