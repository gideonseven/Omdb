package com.don.omdb.ui.diffUtil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.don.omdb.R
import com.don.omdb.data.DiffModel
import com.don.omdb.databinding.ItemEndOfProductBinding
import com.don.omdb.databinding.ItemListDiffBinding
import com.don.omdb.databinding.ItemLoadingLoadMoreBinding
import com.don.omdb.utils.AutoUpdatableAdapter


/**
 * Created by Gideon Steven Tobing on 31,July,2021.
 * https://www.cicil.co.id/
 * gideon@cicil.co.id
 */
class DiffUtilAdapter constructor (val onClick: (DiffModel) -> Unit) : RecyclerView.Adapter<ViewHolder>(),
    AutoUpdatableAdapter {

    companion object {
        private const val PRODUCT_ITEM = 1
        private const val LOADING_ITEM = 0
    }

    //auto observe update list data non blocking main thread
     val items: AsyncListDiffer<DiffModel> by lazy {
         AsyncListDiffer(this, itemCallback<DiffModel> { old, new -> old.id == new.id })
     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            LOADING_ITEM -> LoadingHolder(ItemLoadingLoadMoreBinding.inflate(inflater, parent, false))
            else -> ItemViewHolder(ItemListDiffBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items.currentList[position])
        }
    }

    override fun getItemCount() = items.currentList.size

    override fun getItemViewType(position: Int): Int {
        return when (items.currentList[position].id) {
            LOADING_ITEM -> LOADING_ITEM
            else -> PRODUCT_ITEM
        }
    }

    inner class LoadingHolder(val binding: ItemLoadingLoadMoreBinding) : ViewHolder(binding.root)

    inner class EndProductHolder(val binding: ItemEndOfProductBinding) : ViewHolder(binding.root)

    inner class ItemViewHolder(val binding: ItemListDiffBinding) : ViewHolder(binding.root) {
        fun bind(diff: DiffModel) {
            binding.tvTitle.text =
                binding.tvTitle.context.getString(R.string.item_number_x, diff.position.toString())
            itemView.setOnClickListener { onClick(diff)}
        }
    }

    fun updateList(updatedList: List<DiffModel>) {
        items.submitList(updatedList)
    }

}