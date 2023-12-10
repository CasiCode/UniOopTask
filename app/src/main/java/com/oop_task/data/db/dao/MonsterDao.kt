package com.oop_task.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.oop_task.data.db.entities.MonsterEntityDB
import kotlinx.coroutines.flow.Flow

interface MonsterDao {
    @Insert
    suspend fun save(player: MonsterEntityDB)

    @Query("update ${MonsterEntityDB.TABLE_NAME} " +
            "set name = :name, maxHealthPoints = :maxHealthPoints, healthPoints = :healthPoints, " +
            "attackPoints = :attackPoints, defencePoints = :defencePoints, " +
            "damageRangeStart = :damageRangeStart, damageRangeEnd = :damageRangeEnd " +
            "where id = :id")
    suspend fun update(
        id: String,
        name: String,
        maxHealthPoints: Int,
        healthPoints: Int,
        attackPoints: Int,
        defencePoints: Int,
        damageRangeStart: Int,
        damageRangeEnd: Int
    )

    @Query("delete from ${MonsterEntityDB.TABLE_NAME} where id = :id")
    suspend fun deleteByID(id: String)

    @Delete
    suspend fun delete(player: MonsterEntityDB)

    @Query("select * from ${MonsterEntityDB.TABLE_NAME} where id = :id")
    suspend fun getPlayerByID(id: String): MonsterEntityDB

    @Query("select * from ${MonsterEntityDB.TABLE_NAME}")
    suspend fun getPlayers(): List<MonsterEntityDB>

    @Query("select * from ${MonsterEntityDB.TABLE_NAME}")
    fun getNotesFlow(): Flow<List<MonsterEntityDB>>
}