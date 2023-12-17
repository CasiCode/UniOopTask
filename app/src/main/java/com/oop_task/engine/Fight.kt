package com.oop_task.engine

import com.oop_task.engine.entities.MonsterEntity
import com.oop_task.engine.entities.PlayerEntity
import com.oop_task.values.NonNumericValues.GAME_MESSAGE_PREFIX

class Fight(
    players: List<PlayerEntity> = mutableListOf(),
    monsters: List<MonsterEntity> = mutableListOf()
) {
    private val playerRegistry = EntityRegistry()
    private val monsterRegistry = EntityRegistry()

    init {
        playerRegistry.registerAllOf(players)
        monsterRegistry.registerAllOf(monsters)
        onStart()
    }

    // may cause issues if ids are passed by value
    private fun makePlayerMoves() {
        playerRegistry.forEach { player ->
            player as PlayerEntity
            if ((player.healthPoints <= player.maxHealthPoints * 0.5) and player.canHeal()) {
                player.heal()
            } else if (!monsterRegistry.isEmpty()) {
                player.attack(monsterRegistry.random())
                monsterRegistry.deregisterDead()
            }
        }
    }

    private fun makeMonsterMoves() {
        monsterRegistry.forEach { monster ->
            if (!playerRegistry.isEmpty()) {
                monster.attack(playerRegistry.random())
                playerRegistry.deregisterDead()
            }
        }
    }

    private fun onStart() {
        while (true) {
            if (monsterRegistry.isEmpty()) {
                IOEngine.registerGameMessage("The players have won!")
                break
            }
            if (playerRegistry.isEmpty()) {
                IOEngine.registerGameMessage("The monsters have won.")
                break
            }
            makePlayerMoves()
            makeMonsterMoves()
        }
    }
}