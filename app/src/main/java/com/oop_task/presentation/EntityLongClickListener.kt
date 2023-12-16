package com.oop_task.presentation

import com.oop_task.engine.entities.CreatureEntity

fun interface EntityLongClickListener {
    fun onLongClick(entity: CreatureEntity)
}