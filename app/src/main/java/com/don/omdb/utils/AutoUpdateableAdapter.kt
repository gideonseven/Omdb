package com.don.omdb.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil


/**
 * Created by gideon on 8/11/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */

fun <T> itemCallback(compare: (T, T) -> Boolean): DiffUtil.ItemCallback<T> {
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