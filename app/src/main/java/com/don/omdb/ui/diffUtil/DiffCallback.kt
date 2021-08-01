package com.don.omdb.ui.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.don.omdb.data.DiffModel


/**
 * Created by Gideon Steven Tobing on 01,August,2021.
 * https://www.cicil.co.id/
 * gideon@cicil.co.id
 */
open class MyDiffCallback(
    private val oldGalaxies: List<DiffModel>,
    private val newGalaxies: List<DiffModel>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldGalaxies.size
    }

    override fun getNewListSize(): Int {
        return newGalaxies.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // In the real world you need to compare something unique like id
        return oldGalaxies[oldItemPosition] == newGalaxies[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // This is called if areItemsTheSame() == true;
        return oldGalaxies[oldItemPosition] == newGalaxies[newItemPosition]
    }
}