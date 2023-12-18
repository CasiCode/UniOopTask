package com.oop_task.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oop_task.data.db.entities.MonsterEntityDB
import kotlinx.coroutines.flow.Flow

@Dao
interface MonsterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(monster: MonsterEntityDB)

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
    suspend fun delete(monster: MonsterEntityDB)

    @Query("select * from ${MonsterEntityDB.TABLE_NAME} where id = :id")
    suspend fun getMonsterByID(id: String): MonsterEntityDB

    @Query("select * from ${MonsterEntityDB.TABLE_NAME}")
    suspend fun getMonsters(): List<MonsterEntityDB>

    @Query("select * from ${MonsterEntityDB.TABLE_NAME}")
    fun getMonstersFlow(): Flow<List<MonsterEntityDB>>
}