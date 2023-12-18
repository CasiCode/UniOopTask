package com.oop_task.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oop_task.engine.entities.CreatureEntity

class EntityAdapter : RecyclerView.Adapter<EntityViewHolder>() {
    private val entities = mutableListOf<CreatureEntity>()
    lateinit var entityLongClickListener: EntityLongClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        return EntityViewHolder(parent, entityLongClickListener)
    }

    override fun getItemCount(): Int {
        return entities.size
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.bind(entities[position])
    }

    fun getList(): List<CreatureEntity> {
        return entities
    }

    fun submitList(entities: List<CreatureEntity>) {
        this.entities.clear()
        this.entities.addAll(entities)
        notifyDataSetChanged()
    }
}