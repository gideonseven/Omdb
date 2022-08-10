package com.don.omdb.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.don.omdb.R
import com.don.omdb.databinding.FragmentMainBinding
import com.don.omdb.utils.AppFragment
import com.don.omdb.utils.State
import com.don.omdb.utils.handleResponseState
import com.don.omdb.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber


/**
 * Created by gideon on 8/11/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
@AndroidEntryPoint
class MainFragment : AppFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun getViewState() = binding.viewState

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)


        return binding {
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.observe(
            lifecycle = lifecycle,
            state = { handleState() },
            effect = { handleEffect() }
        )
    }

    private suspend fun handleState() {
        viewModel.uiState.collect { uiState ->
            nonNullContext.handleResponseState(uiState.responseState,
                getUiStateFlow(),
                onLoading = {
                    updateUIStateFlow(State.LOADING)
                },
                onSuccess = { _, data ->
                    data?.let {

                    }
                },
                onFailed = {_, model ->

                }
            )
        }
    }

    private suspend fun handleEffect() {
        viewModel.effect.collect{
            when(it){
                is MainContract.MainEffect.onDoSomethingHappened ->{
                    Timber.e("== SOMETHING HAPPENED")
                }
            }

        }
    }
}