package com.mustafaguvenc.soccerleauge.di

import com.mustafaguvenc.soccerleauge.db.TeamsDao
import com.mustafaguvenc.soccerleauge.network.TeamListApi
import com.mustafaguvenc.soccerleauge.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: TeamListApi,
                          localDataSource: TeamsDao)
    = Repository(remoteDataSource,localDataSource)
}