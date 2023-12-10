package com.oop_task.engine.entities

import com.oop_task.data.db.entities.MonsterEntityDB
import com.oop_task.data.db.jackson.entities.MonsterEntityXML
import com.oop_task.values.NonNumericValues.BASE_MONSTER_NAME

// TODO(ИЗБАВИТЬСЯ ОТ МАГИЧЕСКИХ ЗНАЧЕНИЙ)
class MonsterEntity(
    name: String = BASE_MONSTER_NAME,
    maxHealthPoints: Int = 5,
    healthPoints: Int = 5,
    attackPoints: Int = 5,
    defencePoints: Int = 3,
    damageRange: IntRange = IntRange(2, 4)
) : CreatureEntity(
    name,
    maxHealthPoints,
    healthPoints,
    attackPoints,
    defencePoints,
    damageRange
) {
    constructor(entity: MonsterEntityDB) : this(
        name = entity.name ?: BASE_MONSTER_NAME,
        maxHealthPoints = entity.maxHealthPoints ?: 5,
        healthPoints = entity.healthPoints ?: 5,
        attackPoints = entity.attackPoints ?: 5,
        defencePoints = entity.defencePoints ?: 3,
        damageRange = IntRange(
            entity.damageRangeStart ?: 2,
            entity.damageRangeEnd ?: 4
        )
    )

    constructor(entity: MonsterEntityXML): this(
        name = entity.name ?: BASE_MONSTER_NAME,
        maxHealthPoints = entity.maxHealthPoints ?: 5,
        healthPoints = entity.healthPoints ?: 5,
        attackPoints = entity.attackPoints ?: 5,
        defencePoints = entity.defencePoints ?: 3,
        damageRange = IntRange(
            entity.damageRangeStart ?: 2,
            entity.damageRangeEnd ?: 4
        )
    )
}