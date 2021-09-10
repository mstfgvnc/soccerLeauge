package com.mustafaguvenc.soccerleauge.ui.teamlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafaguvenc.soccerleauge.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_team_list.*
import javax.inject.Inject

@AndroidEntryPoint
class TeamList : Fragment() {

    val viewModel : TeamListViewModel by viewModels()
    @Inject
    lateinit var adapter: TeamListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData(false)
        teamList.layoutManager=LinearLayoutManager(context)
        teamList.adapter=adapter

        fixtureButton.setOnClickListener {
            val action = TeamListDirections.actionTeamListToFixture()
            it.findNavController().navigate(action)

        }

        swipeRefresh.setOnRefreshListener {

            viewModel.getData(true)
            teamList.visibility=View.GONE
            loading.visibility=View.VISIBLE
            swipeRefresh.isRefreshing=false

        }

        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.teamList.observe(viewLifecycleOwner,  { teams ->
            teams?.let{
                teamList.visibility = View.VISIBLE
                adapter.updateTeamList(teams)
                fixtureButton.visibility=View.VISIBLE
            }

        })
        viewModel.loading.observe(viewLifecycleOwner,  {
            it?.let {
                if(it){
                    loading.visibility=View.VISIBLE
                    teamList.visibility=View.GONE
                    error.visibility=View.GONE
                    fixtureButton.visibility=View.GONE

                }else{
                    loading.visibility=View.GONE
                }

            }
        })
        viewModel.error.observe(viewLifecycleOwner,  {
            it?.let {
                if(it){
                    error.visibility=View.VISIBLE
                    fixtureButton.visibility=View.GONE

                }else{
                    error.visibility=View.GONE

                }
            }
        })

    }


}