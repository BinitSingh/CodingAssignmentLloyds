package com.demo.codingassignmentlloyds.injection

import com.demo.codingassignmentlloyds.BuildConfig
import com.demo.codingassignmentlloyds.data.webservice.RemoteDataSource
import com.demo.codingassignmentlloyds.data.webservice.IDataSource
import com.demo.codingassignmentlloyds.data.webservice.MovieApi
import com.demo.codingassignmentlloyds.dispatcher.CoroutinesDispatchers
import com.demo.codingassignmentlloyds.dispatcher.CustomCoroutinesDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Provides an [OkHttpClient]
     * @param loggingInterceptor [HttpLoggingInterceptor] instance
     */
    @Provides
    fun provideOKHttpClient(interceptor: Interceptor) = OkHttpClient().apply {
        OkHttpClient.Builder().apply {
            callTimeout(40, SECONDS)
            connectTimeout(40, SECONDS)
            readTimeout(40, SECONDS)
            writeTimeout(40, SECONDS)
            addInterceptor(interceptor)
            build()
        }
    }

    /**
     * Returns a [MoshiConverterFactory] instance
     */
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    /**
     * Returns an instance of the [MovieApi] interface for the retrofit class
     */
    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Provides
    fun provideCouroutineDispatcher(): CoroutinesDispatchers = CustomCoroutinesDispatchers()

    /**
     * Returns a [IDataSource] impl -> ApiCallsImpl
     */
    @Provides
    fun provideRemoteDataSource(retrofit: MovieApi, dispatcher: CoroutinesDispatchers): IDataSource
    = RemoteDataSource(retrofit, dispatcher)
}