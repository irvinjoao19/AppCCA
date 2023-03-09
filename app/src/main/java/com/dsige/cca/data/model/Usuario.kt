package com.dsige.cca.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Usuario {
    @PrimaryKey
    var opcionId: Int = 0
    var nombreOpcion: String = ""
    var parentId: Int = 0
    var nombreParentId: String = ""
    var usuarioId: Int = 0
    var nombreUsuario: String = ""
    var permisosOpcion: String = ""
}
