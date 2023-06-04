package com.twaun95.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.twaun95.domain.entity.Place
import com.twaun95.presentation.databinding.ItemPlaceBinding

class PlaceListAdapter(
    var onClick: ((item: Place) -> Unit) ?= null
) : ListAdapter<Place, PlaceListAdapter.PlaceViewHolder>(diffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickListener = onClick
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Place>() {
            override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class PlaceViewHolder(
        private val binding: ItemPlaceBinding,
        private val onClickListener: ((item: Place) -> Unit) ?= null
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: Place
        ) {
            binding.data = data
            binding.layoutItem.setOnClickListener {
                onClickListener?.invoke(data)
            }
        }
    }
}