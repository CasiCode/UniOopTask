package com.oop_task.data.jackson.entities

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.oop_task.engine.entities.MonsterEntity

@JsonRootName("MonsterEntity")
data class MonsterEntityXML(
    @set:JsonProperty("ID") var id: String? = null,
    @set:JsonProperty("Name") var name: String? = null,
    @set:JsonProperty("MaxHealthPoints") var maxHealthPoints: Int? = null,
    @set:JsonProperty("HealthPoints") var healthPoints: Int? = null,
    @set:JsonProperty("AttackPoints") var attackPoints: Int? = null,
    @set:JsonProperty("DefencePoints") var defencePoints: Int? = null,
    @set:JsonProperty("DamageRangeStart") var damageRangeStart: Int? = null,
    @set:JsonProperty("damageRangeEnd") var damageRangeEnd: Int? = null
) {
    fun map() = MonsterEntity(this)
}