package com.oop_task.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.oop_task.R
import com.oop_task.data.db.DatabaseClient
import com.oop_task.data.db.entities.MonsterEntityDB
import com.oop_task.data.db.entities.PlayerEntityDB
import com.oop_task.data.jackson.XmlIOMapper
import com.oop_task.data.jackson.entities.EntityListXML
import com.oop_task.data.jackson.entities.MonsterEntityXML
import com.oop_task.data.jackson.entities.PlayerEntityXML
import com.oop_task.databinding.FragmentEntityListBinding
import com.oop_task.engine.entities.CreatureEntity
import com.oop_task.engine.entities.MonsterEntity
import com.oop_task.engine.entities.PlayerEntity
import com.oop_task.values.NonNumericValues.XML_FILE_NAME
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.merge
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
        binding.addFab.setOnClickListener {
            navigationController?.navigate(EntityCreationFragment.newInstance())
        }
        binding.gameLogFab.setOnClickListener {
            navigationController?.navigate(GameLogFragment.newInstance())
        }
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.createXmlFromBDMenuItem -> {
                    createXMLfromDB()
                    true
                }
                R.id.addDBfromXmlMenuItem -> {
                    createDBfromXML()
                    true
                }
                else -> false
            }
        }
        binding.recyclerView.adapter = adapter.apply {
            entityLongClickListener = EntityLongClickListener { entity ->
                AlertDialog.Builder(requireContext()).apply {
                    setTitle(getString(R.string.entity_alertdialog_title))
                    setNegativeButton(
                        getString(R.string.entity_alertdialog_negativebutton)
                    ) { _, _ ->
                        lifecycleScope.launch {
                            if (entity is PlayerEntity)
                                DatabaseClient.playerDao(requireContext()).deleteByID(entity.id)
                            if (entity is MonsterEntity)
                                DatabaseClient.monsterDao(requireContext()).deleteByID(entity.id)
                        }
                    }
                    show()
                }
            }
            lifecycleScope.launch {
                merge(
                    DatabaseClient.playerDao(requireContext()).getPlayersFlow(),
                    DatabaseClient.monsterDao(requireContext()).getMonstersFlow()
                ).collect { creatures ->
                    mutableListOf<CreatureEntity>().apply {
                        creatures.filterIsInstance(PlayerEntityDB::class.java).forEach {
                            this.add(it.map())
                        }
                        creatures.filterIsInstance(MonsterEntityDB::class.java).forEach {
                            this.add(it.map())
                        }
                        Log.d("FRAGMENT_LIST", this.toString())
                        adapter.submitList(this)
                    }
                }
            }
        }
    }

    private fun createDBfromXML() {
        val creatures = XmlIOMapper.parseAs<EntityListXML>(requireContext(), XML_FILE_NAME)
        val players = creatures.players
        val monsters = creatures.monsters
        try{
            players?.forEach {
                lifecycleScope.launch {
                    DatabaseClient.playerDao(requireContext()).save(
                        PlayerEntityDB(
                            id = it.id ?: error("got null field on DB save"),
                            name = it.name ?: error("got null field on DB save"),
                            maxHealthPoints = it.maxHealthPoints
                                ?: error("got null field on DB save"),
                            healthPoints = it.healthPoints ?: error("got null field on DB save"),
                            attackPoints = it.attackPoints ?: error("got null field on DB save"),
                            defencePoints = it.defencePoints ?: error("got null field on DB save"),
                            damageRangeStart = it.damageRangeStart
                                ?: error("got null field on DB save"),
                            damageRangeEnd = it.damageRangeEnd
                                ?: error("got null field on DB save"),
                            healthPotionsAmount = it.healthPotionsAmount
                                ?: error("got null field on DB save")
                        )
                    )
                }
            }
            monsters?.forEach {
                lifecycleScope.launch {
                    DatabaseClient.monsterDao(requireContext()).save(
                        MonsterEntityDB(
                            id = it.id ?: error("got null field on DB save"),
                            name = it.name ?: error("got null field on DB save"),
                            maxHealthPoints = it.maxHealthPoints
                                ?: error("got null field on DB save"),
                            healthPoints = it.healthPoints ?: error("got null field on DB save"),
                            attackPoints = it.attackPoints ?: error("got null field on DB save"),
                            defencePoints = it.defencePoints ?: error("got null field on DB save"),
                            damageRangeStart = it.damageRangeStart
                                ?: error("got null field on DB save"),
                            damageRangeEnd = it.damageRangeEnd ?: error("got null field on DB save")
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Log.d("TAG_SAVE", e.toString())
        }
    }

    private fun createXMLfromDB() {
        val creatures = adapter.getList()
        val players = creatures.filterIsInstance<PlayerEntity>()
        val monsters = creatures.filterIsInstance<MonsterEntity>()
        val playersXml = mutableListOf<PlayerEntityXML>().apply {
            players.forEach {
                this.add(
                    PlayerEntityXML(
                        id = it.id,
                        name = it.name,
                        maxHealthPoints = it.maxHealthPoints,
                        healthPoints = it.healthPoints,
                        attackPoints = it.attackPoints,
                        defencePoints = it.defencePoints,
                        damageRangeStart = it.damageRange.first,
                        damageRangeEnd = it.damageRange.last,
                        healthPotionsAmount = it.healthPotionsAmount
                    )
                )
            }
        }
        val monstersXml = mutableListOf<MonsterEntityXML>().apply {
            monsters.forEach {
                this.add(
                    MonsterEntityXML(
                        id = it.id,
                        name = it.name,
                        maxHealthPoints = it.maxHealthPoints,
                        healthPoints = it.healthPoints,
                        attackPoints = it.attackPoints,
                        defencePoints = it.defencePoints,
                        damageRangeStart = it.damageRange.first,
                        damageRangeEnd = it.damageRange.last
                    )
                )
            }
        }
        XmlIOMapper.writeEntityList(
            requireContext(),
            XML_FILE_NAME,
            EntityListXML(
                players = playersXml,
                monsters = monstersXml
            )
        )
        navigationController?.navigate(XmlFragment.newInstance())
    }

    companion object {
        @JvmStatic
        fun newInstance() = EntityListFragment()
    }
}