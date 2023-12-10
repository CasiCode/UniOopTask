package com.oop_task.engine

import com.oop_task.engine.entities.CreatureEntity
import com.oop_task.values.NonNumericValues.SYSTEM_MESSAGE_PREFIX

class EntityRegistry {
    private val entities = mutableMapOf<String, CreatureEntity>()
    private val entityIDs = mutableListOf<String>()

    fun getByID(id: String): CreatureEntity {
        return entities[id] ?: error("entity not registered")
    }

    fun register(entity: CreatureEntity) {
        entities[entity.id] = entity
        entityIDs.add(entity.id)

        val msg: String = buildString {
            append(SYSTEM_MESSAGE_PREFIX)
            append(" Entity ")
            append(entity.name)
            append(" was registered.")
        }
        IOEngine.registerMessage(msg)
    }

    fun deregister(entity: CreatureEntity) {
        entities.remove(entity.id)
        entityIDs.remove(entity.id)
    }

    fun deregisterByID(id: String) {
        entities.remove(id)
        entityIDs.remove(id)
    }

    fun forEach(action: (CreatureEntity) -> Unit): Unit {
        for (entity in entities) action(entity.value)
    }

    fun size() = entityIDs.size

    fun random(): CreatureEntity? {
        return entities[entityIDs.randomOrNull()]
    }

    fun registerAllOf(entities: List<CreatureEntity>) {
        entities.forEach {
            register(it)
        }
    }

    fun deregisterDead() {
        entities.forEach { (_, entity) ->
            if (entity.isDead) deregister(entity)
        }
    }

    fun clear() {
        entities.clear()
        entityIDs.clear()
    }

    fun isEmpty(): Boolean {
        return entityIDs.size == 0
    }
}