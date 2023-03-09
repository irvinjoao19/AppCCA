package com.dsige.cca.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Actividad {
    @PrimaryKey
    var activityId : Int = 0
    var nameActivity : String = ""
}