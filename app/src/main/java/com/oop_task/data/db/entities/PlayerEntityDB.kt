package com.oop_task.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.oop_task.engine.entities.PlayerEntity

@JsonRootName("PlayerEntity")
@Entity(tableName = PlayerEntityDB.TABLE_NAME)
data class PlayerEntityDB(
    @PrimaryKey @set:JsonProperty("ID") var id: String? = null,
    @set:JsonProperty("Name") var name: String? = null,
    @set:JsonProperty("MaxHealthPoints") var maxHealthPoints: Int? = null,
    @set:JsonProperty("HealthPoints") var healthPoints: Int? = null,
    @set:JsonProperty("AttackPoints") var attackPoints: Int? = null,
    @set:JsonProperty("DefencePoints") var defencePoints: Int? = null,
    @set:JsonProperty("DamageRangeStart") var damageRangeStart: Int? = null,
    @set:JsonProperty("damageRangeEnd") var damageRangeEnd: Int? = null,
    @set:JsonProperty("HealthPotionsAmount") var healthPotionsAmount: Int? = null,
) {
    fun map() = PlayerEntity(this)

    companion object {
        const val TABLE_NAME = "players"
    }
}