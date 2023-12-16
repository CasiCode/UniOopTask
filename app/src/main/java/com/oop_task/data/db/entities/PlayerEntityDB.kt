package com.oop_task.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.oop_task.engine.entities.PlayerEntity

@JsonRootName("PlayerEntity")
@Entity(tableName = PlayerEntityDB.TABLE_NAME)
data class PlayerEntityDB(
    @PrimaryKey var id: String? = null,
    var name: String? = null,
    var maxHealthPoints: Int? = null,
    var healthPoints: Int? = null,
    var attackPoints: Int? = null,
    var defencePoints: Int? = null,
    var damageRangeStart: Int? = null,
    var damageRangeEnd: Int? = null,
    var healthPotionsAmount: Int? = null,
) {
    fun map() = PlayerEntity(this)

    companion object {
        const val TABLE_NAME = "players"
    }
}