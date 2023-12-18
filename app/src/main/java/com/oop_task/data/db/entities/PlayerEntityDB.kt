package com.oop_task.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.oop_task.engine.entities.PlayerEntity

@Entity(tableName = PlayerEntityDB.TABLE_NAME)
class PlayerEntityDB(
    id: String,
    name: String,
    maxHealthPoints: Int,
    healthPoints: Int,
    attackPoints: Int,
    defencePoints: Int,
    damageRangeStart: Int,
    damageRangeEnd: Int,
    var healthPotionsAmount: Int,
) : SharedEntityDB(
    id,
    name,
    maxHealthPoints,
    healthPoints,
    attackPoints,
    defencePoints,
    damageRangeStart,
    damageRangeEnd
) {
    fun map() = PlayerEntity(this)

    companion object {
        const val TABLE_NAME = "players"
    }
}