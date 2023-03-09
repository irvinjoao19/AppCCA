package com.dsige.cca.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SubActividad {
    @PrimaryKey
    var subActivityId: Int = 0
    var activityId: Int = 0
    var nameSubActivity: String = ""
}
