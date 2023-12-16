package com.oop_task.presentation

import com.oop_task.data.db.entities.MonsterEntityDB
import com.oop_task.data.db.entities.PlayerEntityDB

sealed class ListEntity {
    data class PlayerEntity(val player: PlayerEntityDB) : ListEntity()
    data class MonsterEntity(val monster: MonsterEntityDB) : ListEntity()
}
