package com.oop_task.data.db.entities

import androidx.room.PrimaryKey

open class SharedEntityDB(
    @PrimaryKey var id: String,
    var name: String,
    var maxHealthPoints: Int,
    var healthPoints: Int,
    var attackPoints: Int,
    var defencePoints: Int,
    var damageRangeStart: Int,
    var damageRangeEnd: Int
)