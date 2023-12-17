package com.oop_task.engine.entities

import com.oop_task.engine.Dice
import com.oop_task.engine.IOEngine
import com.oop_task.values.NonNumericValues.BASE_CREATURE_NAME
import com.oop_task.values.NonNumericValues.GAME_MESSAGE_PREFIX
import com.oop_task.values.NumericValues.ATTACK_DICE_SIDES
import com.oop_task.values.NumericValues.ATTACK_POINTS_MAX
import com.oop_task.values.NumericValues.ATTACK_POINTS_MIN
import com.oop_task.values.NumericValues.BASE_CREATURE_ATTACK
import com.oop_task.values.NumericValues.BASE_CREATURE_DAMAGE_INTRANGE_END
import com.oop_task.values.NumericValues.BASE_CREATURE_DAMAGE_INTRANGE_START
import com.oop_task.values.NumericValues.BASE_CREATURE_DEFENCE
import com.oop_task.values.NumericValues.BASE_CREATURE_HEALTH
import com.oop_task.values.NumericValues.BASE_CREATURE_MAX_HEALTH
import com.oop_task.values.NumericValues.DEFENCE_POINTS_MAX
import com.oop_task.values.NumericValues.DEFENCE_POINTS_MIN
import java.util.UUID

open class CreatureEntity(
    var name: String = BASE_CREATURE_NAME,
    maxHealthPoints: Int,
    healthPoints: Int,
    attackPoints: Int,
    defencePoints: Int,
    var damageRange: IntRange =
        IntRange(
            BASE_CREATURE_DAMAGE_INTRANGE_START,
            BASE_CREATURE_DAMAGE_INTRANGE_END
        )
) {
    val id: String = UUID.randomUUID().toString()
    var isDead: Boolean = false
    var maxHealthPoints: Int = BASE_CREATURE_MAX_HEALTH
        set(value) {
            if (value <= 0) field = 0
            else field = value
        }
    var healthPoints: Int = BASE_CREATURE_HEALTH
        set(value) {
            if (value <= 0) onDeath()
            else field = value
        }
    var attackPoints: Int = BASE_CREATURE_ATTACK
        set(value) {
            if ((value < ATTACK_POINTS_MIN) or (value > ATTACK_POINTS_MAX))
                error(
                    "Cannot set creatures attack points to number less than $ATTACK_POINTS_MIN or greater than $ATTACK_POINTS_MAX"
                )
            else field = value
        }
    var defencePoints: Int = BASE_CREATURE_DEFENCE
        set(value) {
            if ((value < DEFENCE_POINTS_MIN) or (value > DEFENCE_POINTS_MAX))
                error(
                    "Cannot set creatures attack points to number less than $DEFENCE_POINTS_MIN or greater than $DEFENCE_POINTS_MAX"
                )
            else field = value
        }

    private val ioEngine = IOEngine

    init {
        this.maxHealthPoints = maxHealthPoints
        this.healthPoints = healthPoints
        this.attackPoints = attackPoints
        this.defencePoints = defencePoints
    }

    protected fun onDeath() {
        isDead = true
        IOEngine.registerGameMessage("$name is now dead.")
    }

    fun recieveDamage(damage: Int) {
        if (damage <= 0) error("cannot damage a creature with less than 0 points of damage")
        IOEngine.registerGameMessage("$name got $damage points of damage")
        healthPoints -= damage
    }

    // all the creature's fields are public, that's awful
    fun attack(target: CreatureEntity?) {
        if (target == null) {
            return
        }
        IOEngine.registerGameMessage("$name attacked ${target.name}")

        var attackModifier: Int = attackPoints - target.defencePoints + 1
        val dice = Dice(ATTACK_DICE_SIDES)
        var successful = false
        do {
            val roll: Int = dice.roll()
            if (roll > ATTACK_DICE_SIDES - ATTACK_DICE_SIDES / 3 + 1) {
                successful = true
                break
            }
            attackModifier -= 1
        } while (attackModifier > 0)

        if (!successful) {
            IOEngine.registerGameMessage("the attack was not successful")
        } else {
            target.recieveDamage(damageRange.randomOrNull() ?: 0)
        }
    }
}