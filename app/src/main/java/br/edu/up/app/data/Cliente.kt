package br.edu.up.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var docId: String,
    var nome: String,
    var idade: Int,
    var foto: String = "semfoto.jpg",
) {
    constructor() : this(0,"","",0,"semfoto.jpg")
}