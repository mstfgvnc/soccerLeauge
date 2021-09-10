package com.mustafaguvenc.soccerleauge.ui.fixture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mustafaguvenc.soccerleauge.R
import com.mustafaguvenc.soccerleauge.model.MatchModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fixture.*

@AndroidEntryPoint
class Fixture : Fragment() {

    val viewModel : FixtureViewModel by viewModels()

    private val adapter= FixtureRoundsAdapter( Array(0) {
         Array(0){ MatchModel(null,null) }
    })
    var pagerSnapHelper = PagerSnapHelper()
    val roundPosition =MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fixture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFixture()
        val linearLayoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        fixtureList.layoutManager=linearLayoutManager
        fixtureList.adapter=adapter
        pagerSnapHelper.attachToRecyclerView(fixtureList)

        fixtureList.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    roundPosition.value=linearLayoutManager.findFirstVisibleItemPosition()+1
                }
            }
        })

        observeLiveData()

    }

    fun observeLiveData(){

        viewModel.arrayFixture.observe(viewLifecycleOwner,  { arrayFixture ->

            arrayFixture?.let{
                fixtureList.visibility = View.VISIBLE
                adapter.updateFixtureList(arrayFixture)
                roundPosition.value=1

            }

        })

        roundPosition.observe(viewLifecycleOwner,{

            if(viewModel.byeList.size != 0){

                if(it<(viewModel.teamSize-1)||viewModel.teamSize==0){
                    half.text="Half : 1"
                    week.text="Week : " + it
                }else{
                    half.text="Half : 2"
                    week.text="Week : " + (it-viewModel.teamSize+2)
                }

                println(viewModel.byeList.size)
                if(it<(viewModel.teamSize-1)){
                    byeTeam.text= "Bye : " +  viewModel.byeList[it-1]
                }else{
                    byeTeam.text= "Bye : " +  viewModel.byeList[it-viewModel.teamSize+1]
                }
                byeTeam.visibility=View.VISIBLE
            }else{
                byeTeam.visibility=View.GONE

                if(it<(viewModel.teamSize)||viewModel.teamSize==0){
                    half.text="Half : 1"
                    week.text="Week : " + it
                }else{
                    half.text="Half : 2"
                    week.text="Week : " + (it-viewModel.teamSize+1)
                }
            }

        })

    }

}