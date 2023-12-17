package com.oop_task.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.oop_task.engine.entities.PlayerEntity

@JsonRootName("PlayerEntity")
@Entity(tableName = PlayerEntityDB.TABLE_NAME)
data class PlayerEntityDB(
    @PrimaryKey var id: String,
    var name: String,
    var maxHealthPoints: Int,
    var healthPoints: Int,
    var attackPoints: Int,
    var defencePoints: Int,
    var damageRangeStart: Int,
    var damageRangeEnd: Int,
    var healthPotionsAmount: Int,
) {
    fun map() = PlayerEntity(this)

    companion object {
        const val TABLE_NAME = "players"
    }
}