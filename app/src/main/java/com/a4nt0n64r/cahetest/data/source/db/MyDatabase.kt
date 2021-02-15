package com.a4nt0n64r.cahetest.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.a4nt0n64r.cahetest.domain.model.Person

@Database(
    entities = [Person::class],
    version = MyDatabase.VERSION,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "players.db"
        const val VERSION = 1
    }

    abstract fun playerDao(): PlayerDao
}

