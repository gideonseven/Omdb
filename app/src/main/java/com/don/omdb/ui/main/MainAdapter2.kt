package com.don.omdb.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.don.omdb.R
import com.don.omdb.data.remote.movies.ResultsItem
import com.don.omdb.databinding.ItemListMovieBinding
import com.don.omdb.databinding.ItemLoadmoreBinding
import com.skydoves.bindables.binding


/**
 * Created by gideon on 8/11/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
class MainAdapter2 constructor(val onClick: (ResultsItem) -> Unit) :
     RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_PROGRESS = -1
    private val TYPE_ITEM = 1

    private var listMovies = listOf<ResultsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            MovieViewHolder(parent.binding(R.layout.item_list_movie))
        } else {
            LoadHolder(parent.binding(R.layout.item_loadmore))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_ITEM) {
            (holder as MovieViewHolder)
            holder.bind(listMovies[position])
        } else (holder as LoadHolder)
    }

    override fun getItemViewType(position: Int): Int {
        return if (listMovies[position].id == TYPE_PROGRESS) {
            TYPE_PROGRESS
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount() = listMovies.size


    inner class MovieViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: ResultsItem) {
            binding.apply {
                data = movie
                ll.setOnClickListener { onClick(movie) }
            }
        }
    }

    inner class LoadHolder(binding: ItemLoadmoreBinding) : RecyclerView.ViewHolder(binding.root)

    fun addData(productList: List<ResultsItem>) {
        removeLoading()
        val index = this.listMovies.size
        this.listMovies  = this.listMovies + productList
        notifyItemRangeInserted(index, productList.size)
    }

    fun addLoading() {
        removeLoading()
        this.listMovies = this.listMovies + ResultsItem(id = TYPE_PROGRESS)
        notifyItemInserted(this.listMovies.size)
    }

    fun setSpanSizeLookUp(layoutManager: GridLayoutManager) {
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (getItemViewType(position)) {
                    TYPE_PROGRESS-> layoutManager.spanCount
                    TYPE_ITEM -> 1
                    else -> -1
                }
            }
        }
    }

    private fun removeLoading() {
        val exclude = this.listMovies.find {
            it.id == TYPE_PROGRESS
        }

        exclude?.let {
            this.listMovies = this.listMovies - it
            notifyItemRemoved(this.listMovies.size)
        }
    }
}