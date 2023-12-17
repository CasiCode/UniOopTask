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
        IOEngine.registerSystemMessage("Entity id: ${entity.id} was registered")
    }

    fun deregister(entity: CreatureEntity) {
        entities.remove(entity.id)
        entityIDs.remove(entity.id)
        IOEngine.registerSystemMessage("Entity id: ${entity.id} was deregistered")
    }

    fun deregisterByID(id: String) {
        entities.remove(id)
        entityIDs.remove(id)
        IOEngine.registerSystemMessage("Entity id: $id was deregistered")
    }

    fun forEach(action: (CreatureEntity) -> Unit) {
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

    fun deregisterAllOf(entities: List<CreatureEntity>) {
        entities.forEach {
            deregister(it)
        }
    }

    // thrown java.util.ConcurrentModificationException previously
    fun deregisterDead() {
        val toDeregister = mutableListOf<CreatureEntity>()
        entities.forEach { (_, entity) ->
            if (entity.isDead) toDeregister.add(entity)
        }
        deregisterAllOf(toDeregister)
    }

    fun clear() {
        entities.clear()
        entityIDs.clear()
    }

    fun isEmpty(): Boolean {
        return entityIDs.size == 0
    }
}