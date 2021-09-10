package com.mustafaguvenc.soccerleauge.ui.teamlist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mustafaguvenc.soccerleauge.model.TeamModel
import com.mustafaguvenc.soccerleauge.repository.Repository
import com.mustafaguvenc.soccerleauge.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel
@Inject constructor
    (private val repository: Repository, application : Application): BaseViewModel(application){

    val teamList = MutableLiveData<List<TeamModel>>()
    private val disposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()


    fun getData(refresh:Boolean){
        if(refresh){
            launch {
                loading.value=true
                getDataFromAPI()
            }
        }else{
            launch {
                loading.value=true
                val teams = repository.getTeamsFromDatabase()
                if(teams.size==0){
                    getDataFromAPI()
                }else{
                    showView(teams)
                }

            }
        }

    }
    private fun getDataFromAPI() {

        disposable.add(
            repository.getTeamsFromApi()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<TeamModel>>() {
                    override fun onSuccess(t : List<TeamModel>) {
                        storeInSQLite(t)

                    }
                    override fun onError(e : Throwable) {
                        error.value=true
                        loading.value=false
                         e.printStackTrace()
                    }
                })
        )
    }
    private fun storeInSQLite (list : List<TeamModel>){
        launch {

            repository.deleteAllTeamsFromDatabase()
            val listLong= repository.insertAll(*list.toTypedArray())

            var i = 0
            while(i<list.size){
                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }

            showView(list)
        }

    }

    private fun showView(teams : List<TeamModel>){

        var i = 0
        while(i<teams.size){
            teams[i].uuid = i+1
            i = i + 1
        }

        teamList.value= teams
        loading.value=false
        error.value=false

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}