package com.mustafaguvenc.soccerleauge.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mustafaguvenc.soccerleauge.model.TeamModel


@Dao
interface TeamsDao {
    @Insert
    suspend fun insertAll(vararg team : TeamModel) : List<Long>

    @Query("SELECT * FROM teammodel")
    suspend fun getAllTeams():List<TeamModel>


    @Query("DELETE FROM teammodel")
    suspend fun deleteAllTeams()

}