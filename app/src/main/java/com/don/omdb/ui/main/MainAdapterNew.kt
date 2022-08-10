package com.don.omdb.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.databinding.ItemListMovieBinding
import com.don.omdb.databinding.ItemLoadmoreBinding


/**
 * Created by gideon on 8/9/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
class MainAdapterNew constructor(val onClick: (MdlMovieList) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_LINEAR = 1
    private val TYPE_PROGRESS = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_LINEAR)
            MovieHolder(ItemListMovieBinding.inflate(inflater))
        else
            LoadMoreHolder(ItemLoadmoreBinding.inflate(inflater))
    }

    override fun getItemViewType(position: Int): Int {
        return if (differ.currentList[position].loading == "load")
            TYPE_PROGRESS
        else
            TYPE_LINEAR
    }

    inner class LoadMoreHolder(binding: ItemLoadmoreBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class MovieHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick(differ.currentList[bindingAdapterPosition])
            }
        }

        fun bind(movie: MdlMovieList) {
            binding.apply {
                tvTitle.text = movie.Title
                tvYear.text = movie.Year
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieHolder -> {
                holder.bind(differ.currentList[position])
            }
            is LoadMoreHolder -> {}
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<MdlMovieList>) {
        differ.submitList(list)
    }

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MdlMovieList>() {
        override fun areItemsTheSame(oldItem: MdlMovieList, newItem: MdlMovieList): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: MdlMovieList, newItem: MdlMovieList): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)
}