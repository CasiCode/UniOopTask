package com.oop_task.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.oop_task.engine.entities.MonsterEntity

@JsonRootName("MonsterEntity")
@Entity(tableName = MonsterEntityDB.TABLE_NAME)
data class MonsterEntityDB(
    var id: String? = null,
    var name: String? = null,
    var maxHealthPoints: Int? = null,
    var healthPoints: Int? = null,
    var attackPoints: Int? = null,
    var defencePoints: Int? = null,
    var damageRangeStart: Int? = null,
    var damageRangeEnd: Int? = null
) {
    fun map() = MonsterEntity(this)

    companion object {
        const val TABLE_NAME = "monsters"
    }
}