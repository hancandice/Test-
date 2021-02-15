package com.a4nt0n64r.cahetest.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "players")
data class Person(
    @SerializedName("name")
    @ColumnInfo(name = "name_field") val name: String,
    @SerializedName("data")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "data_field") val data: String

)

data class CloudPlayer(
    @SerializedName("player")
    val person: Person
) {
    override fun toString(): String {
        return "Name = ${person.name}, data = ${person.data}"
    }
}


