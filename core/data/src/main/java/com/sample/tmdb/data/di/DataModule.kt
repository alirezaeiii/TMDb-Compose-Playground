package com.sample.tmdb.data.di

import com.sample.tmdb.data.BuildConfig
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.utils.createNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideNetworkClient(): Retrofit = createNetworkClient(BuildConfig.TMDB_BASE_URL)

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideTVShowService(retrofit: Retrofit): TVShowService = retrofit.create(TVShowService::class.java)

    @Singleton
    @Provides
    fun providePersonService(retrofit: Retrofit): PersonService = retrofit.create(PersonService::class.java)
}
