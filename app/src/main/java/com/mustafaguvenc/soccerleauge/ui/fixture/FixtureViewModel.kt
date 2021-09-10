package com.mustafaguvenc.soccerleauge.ui.fixture

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mustafaguvenc.soccerleauge.model.MatchModel
import com.mustafaguvenc.soccerleauge.model.TeamModel
import com.mustafaguvenc.soccerleauge.repository.Repository
import com.mustafaguvenc.soccerleauge.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FixtureViewModel
@Inject constructor
    (private val repository: Repository, application : Application): BaseViewModel(application){

    val arrayFixture = MutableLiveData<Array<Array<MatchModel>>>()
    var byeList= arrayListOf<String>()
    var teamSize= 0
    fun getFixture(){
        launch {
               val teams = repository.getTeamsFromDatabase()
               createFixture(teams)

        }
    }

    private fun createFixture(teams:List<TeamModel>){

        Collections.shuffle(teams)
        teamSize= teams.size
        var ghost = false

        val totalRounds: Int = teamSize - 1
        var matchesPerRound:Int = teamSize / 2
        val rounds = Array(totalRounds*2) {
            Array(matchesPerRound){MatchModel(null,null)}
        }
        if (teamSize % 2 == 1) {
            teamSize++
            ghost = true
            matchesPerRound++

        }

        for(round in 0..totalRounds-1){

            for(match in 0..matchesPerRound-1){

                var home=0
                var away=0
                var byTeamNumber=0
                if(match%2==0){
                    home = (round - match + matchesPerRound - 1) % (teamSize - 1);
                    away = (teamSize + match + round - matchesPerRound) % (teamSize - 1);
                }else{
                    away = (round - match + matchesPerRound - 1) % (teamSize - 1);
                    home = (teamSize + match + round - matchesPerRound) % (teamSize - 1);
                }

                if (match == matchesPerRound-1 && round%2==0) {
                    away = teamSize - 1;
                    byTeamNumber=home
                }else if(match == matchesPerRound-1 && round%2!=0){
                    home = teamSize - 1;
                    byTeamNumber=away
                }



                if(!(ghost && match==matchesPerRound-1)){

                    rounds[round][match] = MatchModel(teams.get(home).team ,teams.get(away).team)
                    rounds[round+totalRounds][match] = MatchModel(teams.get(away).team ,teams.get(home).team)

                }else if(ghost && match==matchesPerRound-1){
                    byeList.add(teams.get(byTeamNumber).team!!)
                    println("giriyors")

                }

            }
        }

        arrayFixture.value=rounds
    }
}