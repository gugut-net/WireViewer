package com.gugut.base_character_sdk.di

import com.gugut.base_character_sdk.data.remote.CharactersApi
import com.gugut.base_character_sdk.data.remote.CharactersApi.Companion.BASE_URL
import com.google.gson.Gson
import com.gugut.base_character_sdk.data.repository_impl.CharactersRepositoryImpl
import com.gugut.base_character_sdk.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesMoviesService(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): CharactersApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(CharactersApi::class.java)

    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun providesRepo(
        repositoryImpl: CharactersRepositoryImpl
    ): CharactersRepository
}