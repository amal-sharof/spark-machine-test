package com.example.machinetestspark.dashboard.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetestspark.dashboard.domain.model.DashboardResponseModel
import com.example.machinetestspark.databinding.ItemShowBinding
import com.squareup.picasso.Picasso

class ImageAdapter() : ListAdapter<DashboardResponseModel, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemListViewHolder).bind(getItem(position))
    }

    inner class ItemListViewHolder(private val binding: ItemShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardResponseModel) {
            binding.apply {
                Picasso.get()
                    .load(item.imageLink)
                    .into(showImage);
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<DashboardResponseModel>() {
        override fun areItemsTheSame(
            oldItem: DashboardResponseModel,
            newItem: DashboardResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DashboardResponseModel,
            newItem: DashboardResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}