package com.oop_task.engine.entities

import com.oop_task.data.db.entities.PlayerEntityDB
import com.oop_task.data.jackson.entities.PlayerEntityXML
import com.oop_task.engine.IOEngine
import com.oop_task.values.NonNumericValues
import com.oop_task.values.NonNumericValues.BASE_PLAYER_NAME

class PlayerEntity(
    name: String = BASE_PLAYER_NAME,
    maxHealthPoints: Int = 10,
    healthPoints: Int = 10,
    attackPoints: Int = 10,
    defencePoints: Int = 4,
    damageRange: IntRange = IntRange(3, 6),
    healthPotionsAmount: Int = 4
): CreatureEntity(
    name,
    maxHealthPoints,
    healthPoints,
    attackPoints,
    defencePoints,
    damageRange
) {
    constructor(entity: PlayerEntityDB) : this(
        name = entity.name ?: BASE_PLAYER_NAME,
        maxHealthPoints = entity.maxHealthPoints ?: 10,
        healthPoints = entity.healthPoints ?: 10,
        attackPoints = entity.attackPoints ?: 10,
        defencePoints = entity.defencePoints ?: 4,
        damageRange = IntRange(
            entity.damageRangeStart ?: 3,
            entity.damageRangeEnd ?: 6
        ),
        healthPotionsAmount = entity.healthPotionsAmount ?: 4
    )

    constructor(entity: PlayerEntityXML): this(
        name = entity.name ?: BASE_PLAYER_NAME,
        maxHealthPoints = entity.maxHealthPoints ?: 10,
        healthPoints = entity.healthPoints ?: 10,
        attackPoints = entity.attackPoints ?: 10,
        defencePoints = entity.defencePoints ?: 4,
        damageRange = IntRange(
            entity.damageRangeStart ?: 3,
            entity.damageRangeEnd ?: 6
        ),
        healthPotionsAmount = entity.healthPotionsAmount ?: 4
    )

    private var healthPotionsAmount = 0
        set(value) {
            if (value < 0) error("cannot set health potions amount to negative value")
            else field = value
        }

    init {
        this.healthPotionsAmount = healthPotionsAmount
    }

    fun canHeal(): Boolean = healthPotionsAmount > 0

    fun heal() {
        if (healthPotionsAmount <= 0)
            error("cannot heal with healthPotionsAmount less or equal to 0")
        else {
            healthPoints += (healthPoints * HEALTH_POTION_MULTIPLIER).toInt()
            healthPotionsAmount -= 1
            val msg: String = buildString {
                append(NonNumericValues.GAME_MESSAGE_PREFIX)
                append(" ")
                append(name)
                append(" used a health potion")
            }
            IOEngine.registerMessage(msg)
        }
    }

    companion object {
       const val HEALTH_POTION_MULTIPLIER = 0.3
    }
}