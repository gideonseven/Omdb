package com.don.omdb.ui.diffUtil

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.don.omdb.MovieApp
import com.don.omdb.databinding.ActivityDiffUtilBinding
import com.don.omdb.ui.BaseActivity
import com.don.omdb.utils.bindingDelegates
import timber.log.Timber


/**
 * Created by Gideon Steven Tobing on 31,July,2021.
 * https://www.cicil.co.id/
 * gideon@cicil.co.id
 */
class DiffUtilActivity: BaseActivity() {

    lateinit var mAdapter: DiffUtilAdapter

    private val binding by bindingDelegates (ActivityDiffUtilBinding::inflate)

    lateinit var diffViewModel: DiffViewModel
    lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mAdapter = DiffUtilAdapter()

        setupVM()

         diffViewModel.lessList.observe(this, {
             list -> mAdapter.updateList(list)
         } )

        with(binding.rvList){
            itemAnimator = null
            adapter = mAdapter
            mLinearLayoutManager = LinearLayoutManager(context)
            layoutManager = mLinearLayoutManager
            setHasFixedSize(true)
            diffViewModel.getDataForAdapter()
            addOnScrollListener(mInfiniteScrollListener)
        }
    }

    private fun setupVM() {
        (application as MovieApp).appComponent.inject(this)
        diffViewModel = ViewModelProviders.of(this).get(DiffViewModel::class.java)
    }

    private val mInfiniteScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            Timber.e("=== TOTAL ITEM IN ADAPTER ${mAdapter.itemCount - 1}")
            Timber.e("=== LAST COMPLETE ITEM POSITION ${mLinearLayoutManager.findLastCompletelyVisibleItemPosition()}")

            if(!diffViewModel.isLastPage){
                if (mLinearLayoutManager.findLastCompletelyVisibleItemPosition() == mAdapter.itemCount - 1) {
                    Timber.e("=== IM HERE")
                 /*   mAdapter.updateList(arrayListOf<DiffModel>().apply {
                        add(DiffModel(1000, 90909))
                        add(DiffModel(1001, 90910))
                    })*/
                    diffViewModel.getDataForAdapter()
                }
            }
        }
    }


}