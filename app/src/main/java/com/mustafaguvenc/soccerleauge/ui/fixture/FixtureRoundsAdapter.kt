package com.mustafaguvenc.soccerleauge.ui.fixture

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafaguvenc.soccerleauge.R
import com.mustafaguvenc.soccerleauge.databinding.ItemFixtureRoundsBinding
import com.mustafaguvenc.soccerleauge.model.MatchModel

class FixtureRoundsAdapter(var roundsList : Array<Array<MatchModel>>)
    : RecyclerView.Adapter<FixtureRoundsAdapter.FixtureItemViewHolder>(){
    class FixtureItemViewHolder(var view :ItemFixtureRoundsBinding) : RecyclerView.ViewHolder(view.root) {

    }
    lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context=parent.context
        val view = DataBindingUtil.inflate<ItemFixtureRoundsBinding>(inflater,
            R.layout.item_fixture_rounds,parent,false)

        return FixtureItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FixtureItemViewHolder, position: Int) {
        holder.view.roundList.layoutManager=LinearLayoutManager(context)
        holder.view.roundList.adapter= FixtureMatchesAdapter(roundsList[position].toList())

    }

    override fun getItemCount(): Int {
        return roundsList.size
    }

    fun updateFixtureList(newRoundList: Array<Array<MatchModel>>) {

        roundsList=newRoundList
        notifyDataSetChanged()
    }




}