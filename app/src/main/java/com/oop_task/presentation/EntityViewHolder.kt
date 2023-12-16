package com.oop_task.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.oop_task.R
import com.oop_task.databinding.ItemEntityBinding
import com.oop_task.engine.entities.CreatureEntity

class EntityViewHolder(
    parent: ViewGroup,
    private val entityLongClickListener: EntityLongClickListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_entity, parent, false)
) {
    private val binding by viewBinding(ItemEntityBinding::bind)

    fun bind(entity: CreatureEntity) = with(binding) {
        idTextView.text = entity.id
        root.setOnLongClickListener {
            entityLongClickListener.onLongClick(entity)
            true
        }
    }
}