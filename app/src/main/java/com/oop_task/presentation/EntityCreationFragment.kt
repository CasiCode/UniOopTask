package com.oop_task.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.oop_task.R
import com.oop_task.data.db.DatabaseClient
import com.oop_task.data.db.entities.MonsterEntityDB
import com.oop_task.data.db.entities.PlayerEntityDB
import com.oop_task.databinding.FragmentEntityCreationBinding
import com.oop_task.engine.entities.MonsterEntity
import com.oop_task.engine.entities.PlayerEntity
import kotlinx.coroutines.launch

class EntityCreationFragment: Fragment() {
    private val binding by viewBinding(FragmentEntityCreationBinding::bind)

    private var navigationController: NavigationController? = null
    private var isPlayer: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entity_creation, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationController =
            (parentFragment as? NavigationController) ?: (activity as? NavigationController)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            navigationController?.back()
        }
        binding.toggleButton.setOnClickListener {
            isPlayer = !isPlayer
            binding.toggleButton.text = if (isPlayer) "Игрок" else "Монстр"
        }
    }

    override fun onDestroyView() {
        saveEntityToDB()
        super.onDestroyView()
    }

    private fun saveEntityToDB() {
        val name = binding.nameEditText.text.toString()

        lifecycleScope.launch {
            try {
                if (isPlayer) {
                    val player = PlayerEntity(
                        name = name
                    )
                    DatabaseClient.playerDao(requireContext()).save(
                        PlayerEntityDB(
                            id = player.id,
                            name = player.name,
                            maxHealthPoints = player.maxHealthPoints,
                            healthPoints = player.healthPoints,
                            attackPoints = player.attackPoints,
                            defencePoints = player.defencePoints,
                            damageRangeStart = player.damageRange.first,
                            damageRangeEnd = player.damageRange.last,
                            healthPotionsAmount = 4
                        )
                    )
                }
                else {
                    val monster = MonsterEntity(
                        name = name
                    )
                    DatabaseClient.monsterDao(requireContext()).save(
                        MonsterEntityDB(
                            id = monster.id,
                            name = monster.name,
                            maxHealthPoints = monster.maxHealthPoints,
                            healthPoints = monster.healthPoints,
                            attackPoints = monster.attackPoints,
                            defencePoints = monster.defencePoints,
                            damageRangeStart = monster.damageRange.first,
                            damageRangeEnd = monster.damageRange.last,
                        )
                    )
                }
            } catch (e: Exception) {
                Log.d("CREATION_FRAGMENT", "On saving: ${e.localizedMessage}")
            }
        }
    }

    companion object {
        private const val KEY_ID = "KEY_ID"

        @JvmStatic
        fun newInstance() = EntityCreationFragment()
    }
}