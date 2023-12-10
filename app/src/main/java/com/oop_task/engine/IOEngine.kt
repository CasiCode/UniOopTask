package com.oop_task.engine

object IOEngine {
    private val messages = mutableListOf<String>()

    fun registerMessage(msg: String) {
        messages.add(msg)
    }

    // TODO(Мгновенная обработка регистрации (лупер в корутине))
    private fun displayMessages() {
        messages.forEach {
            TODO()
        }
        messages.clear()
    }
}