package com.mustafaguvenc.soccerleauge.di

import com.mustafaguvenc.soccerleauge.network.TeamListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private  val BASE_URL = "https://gist.githubusercontent.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): TeamListApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TeamListApi::class.java)

    }
}