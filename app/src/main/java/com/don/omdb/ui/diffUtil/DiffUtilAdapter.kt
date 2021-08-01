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
import com.don.omdb.ui.diffUtil.DiffUtilAdapter.ItemViewHolder
import com.don.omdb.utils.AutoUpdatableAdapter


/**
 * Created by Gideon Steven Tobing on 31,July,2021.
 * https://www.cicil.co.id/
 * gideon@cicil.co.id
 */
class DiffUtilAdapter: RecyclerView.Adapter<ItemViewHolder>(),
    AutoUpdatableAdapter {

    //auto observe update list data non blocking main thread
     val items: AsyncListDiffer<DiffModel> by lazy {
         AsyncListDiffer(this, itemCallback<DiffModel> { old, new -> old.id == new.id })
     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemListDiffBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items.currentList[position])
    }

    override fun getItemCount() = items.currentList.size

    inner class LoadingHolder(val binding: ItemLoadingLoadMoreBinding) : ViewHolder(binding.root)

    inner class EndProductHolder(val binding: ItemEndOfProductBinding) : ViewHolder(binding.root)

    class ItemViewHolder(val binding: ItemListDiffBinding) : ViewHolder(binding.root) {
      /*  interface Interaction {
            fun onItemSelected(position: Int, item: DiffModel)
        }*/

        fun bind(diff: DiffModel) {
//            itemView.setOnClickListener { onClick(diff) }
            binding.tvTitle.text =
                binding.tvTitle.context.getString(R.string.item_number_x, diff.position.toString())
        }
    }

    fun updateList(updatedList: List<DiffModel>) {
        items.submitList(updatedList)
    }

}