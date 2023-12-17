package com.oop_task.engine

import com.oop_task.values.NonNumericValues
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion

object IOEngine {
    private val _messages = MutableStateFlow(mutableListOf(""))
    val messages = _messages.asStateFlow().onCompletion { _messages.value.clear() }

    private fun registerMessage(msg: String) {
        _messages.value.add(msg)
    }

    fun clearMessages() {
        _messages.value.clear()
    }

    fun registerSystemMessage(msg: String) {
        val message = buildString {
            append(NonNumericValues.SYSTEM_MESSAGE_PREFIX)
            append(" ")
            append(msg)
        }
        registerMessage(message)
    }

    fun registerGameMessage(msg: String) {
        val message = buildString {
            append(NonNumericValues.GAME_MESSAGE_PREFIX)
            append(" ")
            append(msg)
        }
        registerMessage(message)
    }
}