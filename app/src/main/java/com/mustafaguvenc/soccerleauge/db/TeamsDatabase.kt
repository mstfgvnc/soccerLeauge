package com.mustafaguvenc.soccerleauge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mustafaguvenc.soccerleauge.model.TeamModel

@Database(entities = arrayOf(TeamModel::class),version = 1)
abstract class TeamsDatabase : RoomDatabase(){

    abstract fun teamsDao (): TeamsDao

    companion object{
        @Volatile private var instance : TeamsDatabase? = null
        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock){
            instance?: makeDatabese(context).also {
                instance=it
            }
        }

        private fun makeDatabese(context : Context) = Room.databaseBuilder(context.applicationContext
            ,TeamsDatabase::class.java,"teamdatabase").build()

    }
}