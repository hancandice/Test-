package com.a4nt0n64r.cahetest.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.a4nt0n64r.cahetest.domain.model.Person

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePlayer(person: Person)

    @Query("DELETE FROM players WHERE name_field = :name")
    fun deleteByName(name: String)

    @Query("SELECT name_field,data_field FROM players")
    fun selectAll(): List<Person>

    @Query("SELECT name_field,data_field FROM players WHERE name_field = :name")
    fun findByName(name: String): Person

}