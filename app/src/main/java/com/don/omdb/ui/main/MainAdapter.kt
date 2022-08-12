package com.don.omdb.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.don.omdb.R
import com.don.omdb.data.remote.UnsplashItem
import com.don.omdb.databinding.ItemListMovieBinding
import com.don.omdb.databinding.ItemLoadingBinding
import com.don.omdb.utils.Constants
import com.skydoves.bindables.binding

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class MainAdapter constructor(val onClick: (UnsplashItem) -> Unit) :
    ListAdapter<UnsplashItem, RecyclerView.ViewHolder>(
        DIFF_CALLBACK
    ) {

    private val TYPE_PROGRESS = -1
    private val TYPE_LINEAR = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_LINEAR) {
            MovieViewHolder(parent.binding(R.layout.item_list_movie))
        } else {
            LoadHolder(parent.binding(R.layout.item_loading))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_LINEAR) {
            (holder as MovieViewHolder)
            holder.bind(getItem(position))
        } else (holder as LoadHolder)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).id == Constants.LOADING_ID) {
            TYPE_PROGRESS
        } else {
            TYPE_LINEAR
        }
    }

    inner class MovieViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: UnsplashItem) {
            binding.apply {
//                data = movie
                ll.setOnClickListener { onClick(movie) }
            }
        }
    }

    inner class LoadHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateList(list: List<UnsplashItem>){

        submitList(list)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<UnsplashItem> = object : DiffUtil.ItemCallback<UnsplashItem>() {
            override fun areItemsTheSame(oldItem: UnsplashItem, newItem: UnsplashItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UnsplashItem, newItem: UnsplashItem): Boolean {
                // check for contents
                return oldItem.id == newItem.id &&
                        oldItem.user == newItem.user
            }
        }
    }

/*    fun removeLoading(){
        currentList.remove(UnsplashItem(id = Constants.LOADING_ID))
    }*/
}
