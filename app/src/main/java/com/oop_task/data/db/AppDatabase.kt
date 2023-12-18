package com.oop_task.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oop_task.data.db.dao.MonsterDao
import com.oop_task.data.db.dao.PlayerDao
import com.oop_task.data.db.entities.MonsterEntityDB
import com.oop_task.data.db.entities.PlayerEntityDB

@Database(
    entities = [
        PlayerEntityDB::class,
        MonsterEntityDB::class
    ],
    version = AppDatabase.VERSION
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun monsterDao(): MonsterDao

    companion object {
        const val NAME = "app_dp"
        const val VERSION = 1
    }
}