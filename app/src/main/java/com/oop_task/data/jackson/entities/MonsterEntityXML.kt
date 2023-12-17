package com.oop_task.data.jackson.entities

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.oop_task.engine.entities.MonsterEntity

@JacksonXmlRootElement(localName = "MonsterEntity")
data class MonsterEntityXML(
    @field:JacksonXmlProperty(localName = "ID")
    var id: String? = null,
    @field:JacksonXmlProperty(localName = "Name")
    var name: String? = null,
    @field:JacksonXmlProperty(localName = "MaxHealthPoints")
    var maxHealthPoints: Int? = null,
    @field:JacksonXmlProperty(localName = "HealthPoints")
    var healthPoints: Int? = null,
    @field:JacksonXmlProperty(localName = "AttackPoints")
    var attackPoints: Int? = null,
    @field:JacksonXmlProperty(localName = "DefencePoints")
    var defencePoints: Int? = null,
    @field:JacksonXmlProperty(localName = "DamageRangeStart")
    var damageRangeStart: Int? = null,
    @field:JacksonXmlProperty(localName = "DamageRangeEnd")
    var damageRangeEnd: Int? = null
) {
    fun map() = MonsterEntity(this)
}