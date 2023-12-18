package com.oop_task.data.db

import android.content.Context
import androidx.room.Room
import com.oop_task.data.db.dao.MonsterDao
import com.oop_task.data.db.dao.PlayerDao

object DatabaseClient {
    private var db: AppDatabase? = null

    fun playerDao(context: Context): PlayerDao {
        return getInstance(context).playerDao()
    }

    fun monsterDao(context: Context): MonsterDao {
        return getInstance(context).monsterDao()
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