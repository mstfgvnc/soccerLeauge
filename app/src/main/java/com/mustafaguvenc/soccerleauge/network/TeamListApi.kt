package com.mustafaguvenc.soccerleauge.network

import com.mustafaguvenc.soccerleauge.model.TeamModel
import io.reactivex.Single
import retrofit2.http.GET

interface TeamListApi {

  //  https://gist.githubusercontent.com/mstfgvnc/a26b81f28f282044a4ed458dd57e114d/raw/676eb68023cc44593b73813a0634b6cbabb2916b/SoccerTeams
    @GET("mstfgvnc/a26b81f28f282044a4ed458dd57e114d/raw/64a8cea1cd75790878ab30f479ed9d9e9ce70595/SoccerTeams")
    fun getTeams(): Single<List<TeamModel>>

}