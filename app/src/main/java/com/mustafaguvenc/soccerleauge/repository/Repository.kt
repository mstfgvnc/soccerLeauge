package com.mustafaguvenc.soccerleauge.repository

import com.mustafaguvenc.soccerleauge.db.TeamsDao
import com.mustafaguvenc.soccerleauge.model.TeamModel
import com.mustafaguvenc.soccerleauge.network.TeamListApi
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor(private val teamsApi: TeamListApi,
                                     private val teamsDao: TeamsDao) {

    fun getTeamsFromApi(): Single<List<TeamModel>> {
        return teamsApi.getTeams()
    }

    suspend fun insertAll(vararg team : TeamModel):List<Long>{
        return teamsDao.insertAll(*team)
    }

    suspend fun getTeamsFromDatabase():List<TeamModel>{
        return teamsDao.getAllTeams()
    }

    suspend fun deleteAllTeamsFromDatabase(){
        return teamsDao.deleteAllTeams()
    }

}