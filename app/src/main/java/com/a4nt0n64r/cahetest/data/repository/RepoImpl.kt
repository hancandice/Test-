package com.a4nt0n64r.cahetest.data.repository

import com.a4nt0n64r.cahetest.data.source.db.PlayerDao
import com.a4nt0n64r.cahetest.domain.model.Person
import com.a4nt0n64r.cahetest.domain.repository.Repository

class RepoImpl(private val dao: PlayerDao) : Repository {

    override suspend fun deletePlayer(name: String) {
        dao.deleteByName(name)
    }

    override suspend fun findPlayer(name: String): Person {
        return dao.findByName(name)
    }

    override suspend fun getAllPlayers(): List<Person> {
        return dao.selectAll()
    }

    override suspend fun savePlayer(person: Person) {
        dao.savePlayer(person)
    }
}