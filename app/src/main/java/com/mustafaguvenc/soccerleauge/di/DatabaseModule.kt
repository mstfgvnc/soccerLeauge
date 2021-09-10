package com.mustafaguvenc.soccerleauge.di

import android.content.Context
import com.mustafaguvenc.soccerleauge.db.TeamsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTeamDatabase(@ApplicationContext appContext: Context) = TeamsDatabase.invoke(appContext)

    @Singleton
    @Provides
    fun provideTurbinbeDao(db: TeamsDatabase) = db.teamsDao()

}