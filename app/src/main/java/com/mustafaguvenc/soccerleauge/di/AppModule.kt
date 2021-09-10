package com.mustafaguvenc.soccerleauge.di

import com.mustafaguvenc.soccerleauge.model.TeamModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideList() : ArrayList<TeamModel> = ArrayList<TeamModel>()


}