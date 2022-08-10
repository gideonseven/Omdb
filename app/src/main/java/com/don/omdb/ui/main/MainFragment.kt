package com.don.omdb.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.don.omdb.R
import com.don.omdb.databinding.FragmentMainBinding
import com.don.omdb.utils.*
import dagger.hilt.android.AndroidEntryPoint
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

        Timber.e("=== ${JniHelper.baseUrl()}")
        Timber.e("=== ${JniHelper.apiKey()}")
    }

    private suspend fun handleState() {
        viewModel.uiState.collect { uiState ->
            nonNullContext.handleResponseState(uiState.responseState,
                getUiStateFlow(),
                onLoading = {
                    updateUIStateFlow(State.LOADING)
                    Timber.e("== onLoading")
                },
                onSuccess = { _, data ->
                    data?.let {
                        Timber.e("== onSuccess")

                    }
                },
                onFailed = {_, model ->
                    Timber.e("== onFailed")
                }
            )
        }
    }

    private suspend fun handleEffect() {
        viewModel.effect.collect{
            when(it){
                is MainContract.MainEffect.DoSomethingHappened ->{
                    Timber.e("== SOMETHING HAPPENED")
                }
            }
        }
    }
}