package com.don.omdb.ui.main

import AppTheme
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.don.omdb.R
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.ui.detail.DetailActivity
import com.don.omdb.utils.GlideUtil
import com.don.omdb.utils.OnLoadMoreListener
import androidx.compose.ui.platform.ComposeView
import com.don.omdb.data.remote.MovieUi
import com.don.omdb.data.remote.toUi
import com.don.omdb.ui.components.MovieRow

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class MainAdapter(
    private val activity: Activity,
    private val onItemClick: (String)-> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listMovie = ArrayList<MdlMovieList>()

    private val TYPE_PROGRESS = 0
    private val TYPE_LINEAR = 1

    private var isLoading = false
    private var isMoreDataAvailable = true

    private var loadMoreListener: OnLoadMoreListener? = null

    fun setData(items: List<MdlMovieList>) {
        if (listMovie.isNotEmpty()) {
            listMovie.add(MdlMovieList("load"))
            this.notifyItemInserted(listMovie.size - 1)
            if (items.isNotEmpty()) {
                listMovie.removeAt(listMovie.size - 1)
                listMovie.addAll(items)
            } else {
                this.setMoreDataAvailable(false)
            }
        } else {
            listMovie.addAll(items)
        }
        notifyDataSetChanged()
        isLoading = false
    }

    fun clear() {
        listMovie.clear()
        notifyDataSetChanged()
        isLoading = false
        isMoreDataAvailable = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(activity)
        return if (viewType == TYPE_LINEAR) {
            ComposeMovieHolder(ComposeView(parent.context))
        } else {
            LoadHolder(inflater.inflate(R.layout.item_loadmore, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= itemCount - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true
            loadMoreListener!!.onLoadMore()
        }

        if (getItemViewType(position) == TYPE_LINEAR) {
            (holder as ComposeMovieHolder)
            holder.bind(listMovie[position].toUi(), onItemClick)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listMovie[position].loading == "load") {
            TYPE_PROGRESS
        } else {
            TYPE_LINEAR
        }
    }

    override fun getItemCount() = listMovie.size

    private class ComposeMovieHolder(
        private val composeView: ComposeView
    ) : RecyclerView.ViewHolder(composeView) {

        fun bind(movie: MovieUi, onClick: (String) -> Unit) {
            composeView.setContent {
                // Wrap with your app theme so typography/colors match
//                MovieAppTheme {
//                    MovieRow(movie = movie, onClick = onClick)
//                }
                AppTheme {
                    MovieRow(movie = movie, onClick = onClick)
                }
            }
        }
    }

    private class LoadHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    fun setMoreDataAvailable(moreDataAvailable: Boolean) {
        isMoreDataAvailable = moreDataAvailable
    }

    fun setLoadMoreListener(loadMoreListener: OnLoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }
}