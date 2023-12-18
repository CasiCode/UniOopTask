package com.oop_task.data.jackson.entities

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "EntityList")
data class EntityListXML(
    @field:JacksonXmlProperty(localName = "Player")
    var players: List<PlayerEntityXML>? = null,
    @field:JacksonXmlProperty(localName = "Monster")
    var monsters: List<MonsterEntityXML>? = null
) {
    fun map(): Map<String, List<Any>> {
        return mapOf(
            Pair("players", players ?: emptyList()),
            Pair("monsters", monsters ?: emptyList())
        )
    }
}