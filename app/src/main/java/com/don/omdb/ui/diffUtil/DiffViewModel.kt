package com.don.omdb.ui.diffUtil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.don.omdb.data.DiffModel
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Created by Gideon Steven Tobing on 31,July,2021.
 * https://www.cicil.co.id/
 * gideon@cicil.co.id
 */
class DiffViewModel : ViewModel() {

    var currentPage: Int = 0
    var isLastPage = false

    private var dataSize = 50
    private var totalPage: Int = 0
    private var allList = arrayListOf<DiffModel>()
    var lessList = MutableLiveData<List<DiffModel>>()

    fun getDataForAdapter() {
        if (currentPage == 0) {
            for (i in 0 until dataSize) {
                allList.add(i, DiffModel(id = i + dataSize, position = i))
            }
            totalPage = allList.chunked(10).size
        }

        Timber.e("=== CURRENT PAGE $currentPage")
        Timber.e("=== TOTAL PAGE $totalPage")
        Timber.e("=== IS LAST PAGE $isLastPage")
        Timber.e("=== TOTAL DATA ADDED  ${allList.size}")

        currentPage++

        val tempValue = arrayListOf<DiffModel>()
        lessList.value?.let {
            tempValue.addAll(it)
        }

        lessList.postValue(tempValue + allList.chunked(10)[currentPage - 1])

        if (totalPage == currentPage) {
            isLastPage = true
        }

        Timber.e("=== CURRENT PAGE $currentPage")
        Timber.e("=== TOTAL PAGE $totalPage")
        Timber.e("=== IS LAST PAGE $isLastPage")
        Timber.e("=== TOTAL DATA ADDED  ${allList.size}")
        Timber.e("=== Current DATA ADDED  ${lessList.value?.size}")
    }
}