package com.don.omdb.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.don.omdb.R
import com.don.omdb.databinding.FragmentMainBinding
import com.don.omdb.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*


/**
 * Created by gideon on 8/11/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
@AndroidEntryPoint
class MainFragment : AppFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    private val mAdapter: MainAdapter2 by lazy {
        MainAdapter2(onClick = {
            Timber.e("=== item clicked")
        })
    }

    override fun getViewState() = binding.viewState

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(nonNullContext)
            val gridLayoutManager = GridLayoutManager(nonNullContext, 2)
            with(rv) {
                adapter = mAdapter

                layoutManager = linearLayoutManager
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if ((rv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == mAdapter.itemCount - 1) {
                            Toast.makeText(nonNullContext, "Last", Toast.LENGTH_LONG).show()

                            mAdapter.addLoading()
                            lifecycleScope.launch {
                                delay(1000)
                                viewModel.currentPage++
                                viewModel.setEvent(MainContract.MainEvent.GetPhotos)
                            }
                        }
                    }
                })
            }

            fabChanger.setOnClickListener {
                viewModel.isLinear.value = rv.layoutManager == linearLayoutManager
                rv.layoutManager = if(viewModel.isLinear.value == true) gridLayoutManager else linearLayoutManager
                mAdapter.setSpanSizeLookUp(gridLayoutManager)
            }
        }
        viewLifecycleOwner.lifecycleScope.observe(
            lifecycle = lifecycle,
            state = { handleState() },
            effect = { handleEffect() }
        )
    }

    private suspend fun handleState() {
        viewModel.uiState.collect { uiState ->
            /* nonNullContext.handleResponseState(uiState.responseState,
                 getUiStateFlow(),
                 onLoading = {
                     // TODO ntr keknya di sini bisa dibikin untuk update item di recyclerview
                     if (viewModel.currentPage == Constants.ZERO) updateUIStateFlow(State.LOADING)
                     Timber.e("== onLoading FRAGMENT")
                 },
             )*/

            nonNullContext.handleResponseState(
                uiState.responseStateMovies,
                getUiStateFlow(),
                onLoading = {
                    // TODO ntr keknya di sini bisa dibikin untuk update item di recyclerview
                    if (viewModel.currentPage == Constants.ONE) updateUIStateFlow(State.LOADING)
                    Timber.e("== onLoading FRAGMENT")
                },
            )
        }
    }

    private suspend fun handleEffect() {
        viewModel.effect.collect {
            when (it) {
                is MainContract.MainEffect.AddNewList -> {
                    mAdapter.addData(it.list)
                }
            }
        }
    }
}