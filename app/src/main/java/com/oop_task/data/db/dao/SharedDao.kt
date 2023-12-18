package com.oop_task.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.oop_task.data.db.entities.MonsterEntityDB
import com.oop_task.data.db.entities.PlayerEntityDB
import kotlinx.coroutines.flow.Flow

@Dao
interface SharedDao {
    @Query("select * from ${MonsterEntityDB.TABLE_NAME} cross join ${PlayerEntityDB.TABLE_NAME}")
    fun getEntitiesFlow(): Flow<List<PlayerEntityDB>>
}