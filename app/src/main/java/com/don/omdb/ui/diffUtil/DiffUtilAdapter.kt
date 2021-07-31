package com.don.omdb.ui.diffUtil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.don.omdb.data.Diff
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.don.omdb.R
import com.don.omdb.databinding.ItemEndOfProductBinding
import com.don.omdb.databinding.ItemListDiffBinding
import com.don.omdb.databinding.ItemLoadingLoadMoreBinding


/**
 * Created by Gideon Steven Tobing on 31,July,2021.
 * https://www.cicil.co.id/
 * gideon@cicil.co.id
 */
class DiffUtilAdapter(val onClick: (Diff) -> Unit): RecyclerView.Adapter<ViewHolder>() {

    private var productList = listOf<Diff>()

    companion object {
        private const val PRODUCT_ITEM = 1
        private const val LOADING_ITEM = 0
        const val RETRY_ITEM = -1
        private const val END_OF_PRODUCT = -2
        const val GRID_COLUMN = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        return when (viewType){
            LOADING_ITEM -> LoadingHolder(ItemLoadingLoadMoreBinding.inflate(inflater, parent, false))
            END_OF_PRODUCT -> EndProductHolder(ItemEndOfProductBinding.inflate(inflater, parent, false))
            else -> ItemViewHolder(ItemListDiffBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            holder.bind(productList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (productList[position].id){
            LOADING_ITEM -> LOADING_ITEM
            END_OF_PRODUCT -> END_OF_PRODUCT
            else -> PRODUCT_ITEM
        }
    }

    override fun getItemCount() = productList.size

    inner class LoadingHolder(val binding: ItemLoadingLoadMoreBinding) : ViewHolder(binding.root)

    inner class EndProductHolder(val binding: ItemEndOfProductBinding) : ViewHolder(binding.root)

    inner class ItemViewHolder(val binding: ItemListDiffBinding): ViewHolder(binding.root){
        fun bind(diff: Diff){
            itemView.setOnClickListener { onClick(diff) }
            binding.tvTitle.text = binding.tvTitle.context.getString(R.string.item_number_x, diff.position.toString())
        }
    }

    fun addLoading(){
        removeLoadingRetry()

        this.productList = this.productList + Diff(id = LOADING_ITEM)
    }

    fun removeLoadingRetry(){
        val exclude = this.productList.find {
            it.id == LOADING_ITEM || it.id == RETRY_ITEM
        }

        exclude?.let {
            this.productList = this.productList - it
            notifyItemRemoved(this.productList.size)
        }
    }
}