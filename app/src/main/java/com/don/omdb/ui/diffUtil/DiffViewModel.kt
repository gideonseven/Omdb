package com.don.omdb.ui.diffUtil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.don.omdb.data.DiffModel
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
    var lessList = MutableLiveData<List<DiffModel>>().apply {
        value = arrayListOf()
    }

    private val loadingItem = DiffModel(
        DiffUtilAdapter.LOADING_ITEM,
        -11
    )

    private val endOfItem = DiffModel(
        DiffUtilAdapter.END_OF_PRODUCT,
        -11
    )

    //CHUNKED NYA JGN DI PRESENTER (untuk project kita)
    //TARO DI DATA MANAGER
    fun getDataForAdapter() {

        lessList.value?.let {
            if(it.contains(loadingItem)){
              lessList.value = it.minus(loadingItem)
            }
        }

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

        //add value each called
        currentPage++

        //check if last page
        if (totalPage == currentPage) {
            isLastPage = true
        }

        //set temp value
        val tempValue = arrayListOf<DiffModel>()
        lessList.value?.let {
            tempValue.addAll(it)
        }

        //update value
        lessList.postValue(
            if (isLastPage) {
                tempValue + allList.chunked(10)[currentPage - 1] + endOfItem
            } else {
                tempValue + allList.chunked(10)[currentPage - 1] + loadingItem
            }
        )

        Timber.e("=== CURRENT PAGE $currentPage")
        Timber.e("=== TOTAL PAGE $totalPage")
        Timber.e("=== IS LAST PAGE $isLastPage")
        Timber.e("=== TOTAL DATA ADDED  ${allList.size}")
        Timber.e("=== Current DATA ADDED  ${lessList.value?.size}")
    }

    fun resetDataForAdapter(){
        lessList.value = arrayListOf()
    }
}