package com.don.omdb.ui.main

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

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class MainAdapter(private val activity: Activity) :
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

    fun clearList() {
        listMovie.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(activity)
        return if (viewType == TYPE_LINEAR) {
            MovieViewHolder(inflater.inflate(R.layout.item_list_movie, parent, false))
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
            (holder as MovieViewHolder)
            holder.bind(listMovie[position])
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

    class MovieViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.ivPoster)
        private val tvName: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvYear: TextView = itemView.findViewById(R.id.tvYear)
        private val ll: LinearLayout = itemView.findViewById(R.id.ll)


        fun bind(movie: MdlMovieList) {
            if (movie.Title != null) {
                tvName.text = movie.Title
                tvYear.text = movie.Year
                GlideUtil.glideOverrideSize(itemView.context, movie.Poster!!, imgPhoto)

                ll.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_IMDB, movie.imdbID)
                    itemView.context.startActivity(intent)
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