package com.dsige.cca.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RegisterWork {
    @PrimaryKey(autoGenerate = true)
    var registerId: Int = 0
    var activityId: Int = 0
    var subActivityId: Int = 0
    var dateRegister: String = ""
    var typeFile: Int = 0
    var nameFile: String = ""
    var routeFile: String = ""
    var nameVoice: String = ""
    var routeVoice: String = ""
    var coordinateX: String = ""
    var coordinateY: String = ""
    var tramo: String = ""
    var errorGps: String = ""
    var estado: Int = 0
    var usuarioId: Int = 0
    var altitud: String = ""
    var campoL: String = ""
    var latitude: String = ""
    var longitude: String = ""

    var active: Int = 0
}