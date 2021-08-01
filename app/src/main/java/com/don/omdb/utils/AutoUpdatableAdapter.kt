package com.don.omdb.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Gideon Steven Tobing on 01,August,2021.
 * https://www.cicil.co.id/
 * gideon@cicil.co.id
 */
/**
 * Created by Franky Wijanarko on 2/26/2021.
 * franky.wijanarko@cicil.co.id
 *
 * source : https://github.com/antoniolg/diffutil-recyclerview-kotlin/blob/master/app/src/main/java/com/antonioleiva/diffutilkotlin/AutoUpdatableAdapter.kt
 */
interface AutoUpdatableAdapter {
    fun <T> RecyclerView.Adapter<*>.itemCallback (compare: (T, T) -> Boolean): DiffUtil.ItemCallback<T> {
        return object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                return compare(oldItem, newItem)
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                return oldItem == newItem
            }
        }
    }


    fun <T> RecyclerView.Adapter<*>.autoNotify(old: List<T>, new: List<T>, compare: (T, T) -> Boolean) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compare(old[oldItemPosition], new[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition] == new[newItemPosition]
            }

            override fun getOldListSize() = old.size

            override fun getNewListSize() = new.size
        })

        diff.dispatchUpdatesTo(this)
    }
}