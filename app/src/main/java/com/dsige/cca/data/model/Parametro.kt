package com.dsige.cca.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Parametro {
    @PrimaryKey
    var parameterId: Int = 0
    var nameParameter: String = ""
    var valueParameter: Int = 0
}
