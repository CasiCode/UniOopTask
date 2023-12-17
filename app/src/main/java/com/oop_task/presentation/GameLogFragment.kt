package com.oop_task.presentation

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.oop_task.R
import com.oop_task.data.db.DatabaseClient
import com.oop_task.databinding.FragmentGameLogBinding
import com.oop_task.engine.Fight
import com.oop_task.engine.IOEngine
import com.oop_task.engine.entities.MonsterEntity
import com.oop_task.engine.entities.PlayerEntity
import kotlinx.coroutines.launch

class GameLogFragment: Fragment() {
    private val binding by viewBinding(FragmentGameLogBinding::bind)
    private var navigationController: NavigationController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_log, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationController =
            (parentFragment as? NavigationController) ?: (activity as? NavigationController)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameLogTextview.movementMethod = ScrollingMovementMethod()
        lifecycleScope.launch {
//            val players: List<PlayerEntity> =
//                DatabaseClient.playerDao(requireContext()).getPlayers().map {
//                    it.map()
//                }
//            val monsters: List<MonsterEntity> =
//                DatabaseClient.monsterDao(requireContext()).getMonsters().map {
//                    it.map()
//                }
            IOEngine.clearMessages()
            val players = listOf(
                PlayerEntity(name = "Steve"),
                PlayerEntity(name = "Alex")
            )
            val monsters = listOf(
                MonsterEntity(name = "Spider"),
                MonsterEntity(name = "Zombie")
            )
            val fight = Fight(players, monsters)
            IOEngine.messages.collect { messages ->
                binding.gameLogTextview.text = buildString {
                    messages.forEach { msg ->
                        append(msg)
                        append(System.getProperty("line.separator"))
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameLogFragment()
    }
}