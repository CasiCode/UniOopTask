package com.oop_task.data.db

import android.content.Context
import androidx.room.Room
import com.oop_task.data.db.dao.MonsterDao
import com.oop_task.data.db.dao.PlayerDao
import com.oop_task.data.db.dao.SharedDao

object DatabaseClient {
    private var db: AppDatabase? = null

    fun playerDao(context: Context): PlayerDao {
        return getInstance(context).playerDao()
    }

    fun monsterDao(context: Context): MonsterDao {
        return getInstance(context).monsterDao()
    }

    fun sharedDao(context: Context): SharedDao {
        return getInstance(context).sharedDao()
    }

    private fun getInstance(context: Context): AppDatabase {
        return db ?: run {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.NAME
            ).build()
            this.db = db
            db
        }
    }
}