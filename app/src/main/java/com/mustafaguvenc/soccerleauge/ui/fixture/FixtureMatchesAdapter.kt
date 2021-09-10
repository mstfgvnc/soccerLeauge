package com.mustafaguvenc.soccerleauge.ui.fixture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mustafaguvenc.soccerleauge.R
import com.mustafaguvenc.soccerleauge.databinding.ItemFixtureMatchesBinding
import com.mustafaguvenc.soccerleauge.model.MatchModel

class FixtureMatchesAdapter(var matchList : List<MatchModel>)
    : RecyclerView.Adapter<FixtureMatchesAdapter.FixtureMatchesItemViewHolder>(){
    class FixtureMatchesItemViewHolder(var view : ItemFixtureMatchesBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureMatchesItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemFixtureMatchesBinding>(inflater,
            R.layout.item_fixture_matches,parent,false)

        return FixtureMatchesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FixtureMatchesItemViewHolder, position: Int) {
        holder.view.matches=matchList[position]
        holder.view.matchRank.text=(position+1).toString()
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

}