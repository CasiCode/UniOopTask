package com.oop_task.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.oop_task.data.db.entities.PlayerEntityDB
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Insert
    suspend fun save(player: PlayerEntityDB)

    @Query("update ${PlayerEntityDB.TABLE_NAME} " +
            "set name = :name, maxHealthPoints = :maxHealthPoints, healthPoints = :healthPoints, " +
            "attackPoints = :attackPoints, defencePoints = :defencePoints, " +
            "damageRangeStart = :damageRangeStart, damageRangeEnd = :damageRangeEnd, " +
            "healthPotionsAmount = :healthPotionsAmount " +
            "where id = :id")
    suspend fun update(
        id: String,
        name: String,
        maxHealthPoints: Int,
        healthPoints: Int,
        attackPoints: Int,
        defencePoints: Int,
        damageRangeStart: Int,
        damageRangeEnd: Int,
        healthPotionsAmount: Int
    )

    @Query("delete from ${PlayerEntityDB.TABLE_NAME} where id = :id")
    suspend fun deleteByID(id: String)

    @Delete
    suspend fun delete(player: PlayerEntityDB)

    @Query("select * from ${PlayerEntityDB.TABLE_NAME} where id = :id")
    suspend fun getPlayerByID(id: String): PlayerEntityDB

    @Query("select * from ${PlayerEntityDB.TABLE_NAME}")
    suspend fun getPlayers(): List<PlayerEntityDB>

    @Query("select * from ${PlayerEntityDB.TABLE_NAME}")
    fun getPlayersFlow(): Flow<List<PlayerEntityDB>>
}