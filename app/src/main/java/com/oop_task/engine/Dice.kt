package com.oop_task.engine

import com.oop_task.values.NumericValues.DEFAULT_DICE_SIDES

class Dice(
    private val sides: Int = DEFAULT_DICE_SIDES
) {
    init {
        require(sides > 1)
    }

    fun roll(): Int {
        return IntRange(1, sides).random()
    }
}
