package com.oop_task.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.oop_task.R
import com.oop_task.data.db.DatabaseClient
import com.oop_task.databinding.FragmentEntityListBinding
import com.oop_task.engine.entities.CreatureEntity
import kotlinx.coroutines.launch

class EntityListFragment: Fragment() {
    private val binding by viewBinding(FragmentEntityListBinding::bind)
    private var navigationController: NavigationController? = null
    private val adapter = EntityAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entity_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationController =
            (parentFragment as? NavigationController) ?: (activity as? NavigationController)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter.apply {
            entityLongClickListener = EntityLongClickListener { entity ->
                AlertDialog.Builder(requireContext()).apply {
                    setTitle(getString(R.string.entity_alertdialog_title))
                    setNegativeButton(
                        getString(R.string.entity_alertdialog_negativebutton)
                    ) { _, _ ->
                        lifecycleScope.launch {
                            DatabaseClient.noteDao(requireContext()).delete(entity)
                        }
                    }
                    show()
                }
            }
            binding.addFab.setOnClickListener {
                navigationController?.navigate(EntityCreationFragment.newInstance())
            }
            binding.gameLogFab.setOnClickListener {
                navigationController?.navigate(GameLogFragment.newInstance())
            }
            lifecycleScope.launch {
                DatabaseClient.playerDao(requireContext())
                    .getPlayersFlow()
                    .collect { players ->
                        players.forEach {
                            entities.add(it.map() as CreatureEntity)
                        }
                    }
                DatabaseClient.monsterDao(requireContext())
                    .getMonstersFlow()
                    .collect() { monsters ->
                        monsters.forEach {
                            entities.add(it.map() as CreatureEntity)
                        }
                    }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = EntityListFragment()
    }
}