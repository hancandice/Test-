package com.a4nt0n64r.cahetest.domain.repository

import com.a4nt0n64r.cahetest.domain.model.Person

//Тут описаны все взаимодействия с Model = Бизнес логика = данные
interface Repository {
    suspend fun findPlayer(name: String): Person
    suspend fun deletePlayer(name: String)
    suspend fun getAllPlayers(): List<Person>
    suspend fun savePlayer(person: Person)
}