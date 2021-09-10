package com.mustafaguvenc.soccerleauge.ui.teamlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mustafaguvenc.soccerleauge.R
import com.mustafaguvenc.soccerleauge.databinding.ItemTeamBinding
import com.mustafaguvenc.soccerleauge.model.TeamModel
import javax.inject.Inject

class TeamListAdapter
@Inject constructor (val teamList : ArrayList<TeamModel>)
    : RecyclerView.Adapter<TeamListAdapter.TeamListViewHolder>(){
    class TeamListViewHolder(var view :ItemTeamBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemTeamBinding>(inflater,
            R.layout.item_team,parent,false)

        return TeamListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.view.teams = teamList[position]
    }

    override fun getItemCount(): Int {
       return teamList.size
    }

    fun updateTeamList(newTeamList: List<TeamModel >) {

        teamList.clear()
        teamList.addAll(newTeamList)
        notifyDataSetChanged()
    }
}