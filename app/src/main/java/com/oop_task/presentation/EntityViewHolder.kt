package com.oop_task.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.oop_task.R
import com.oop_task.databinding.ItemEntityBinding
import com.oop_task.engine.entities.CreatureEntity
import com.oop_task.engine.entities.PlayerEntity

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
        binding.nameTextView.text = entity.name
        root.setOnLongClickListener {
            entityLongClickListener.onLongClick(entity)
            true
        }
        if (entity is PlayerEntity)
            binding.entityImageView.setBackgroundResource(R.drawable.baseline_people_alt_24)
        else
            binding.entityImageView.setBackgroundResource(R.drawable.baseline_android_24)
    }
}