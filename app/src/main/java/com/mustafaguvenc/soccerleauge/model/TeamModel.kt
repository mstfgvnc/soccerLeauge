package com.mustafaguvenc.soccerleauge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TeamModel(
    @ColumnInfo(name="team")
    val team :String?

)
{
    @PrimaryKey(autoGenerate = true)
    var uuid :Int= 0
}